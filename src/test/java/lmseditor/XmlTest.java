package lmseditor;

import lmseditor.backend.image.ImageBase64;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import lmseditor.backend.question.*;
import lmseditor.backend.question.component.*;

public class XmlTest {

    @Test
    public void marshalAndUnmarshal() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Correct answer 1", 100));
        answers.add(new Answer("Incorrect answer 1", 0));
        answers.add(new Answer("Incorrect answer 2", 0));

        TextWithImages questionShortAnswerText = new TextWithImages("Question short answer 1 text");
        questionShortAnswerText.addImage(new ImageBase64("img-1.png", "/", 100, 100));
        questionShortAnswerText.getImage(0).setBase64("base64code-1");
        questionShortAnswerText.addImage(new ImageBase64("img-2.png", "/", 200, 200));
        questionShortAnswerText.getImage(1).setBase64("base64code-2");

        QuestionShortAnswer questionShortAnswer = new QuestionShortAnswer("questionShortAnswer-1",
                questionShortAnswerText, answers);

        QuestionChoice questionChoice = new QuestionChoice("questionChoice-1",
                new TextWithImages("Question choice 1 text"),false, answers);

        List<Subquestion> subquestions = new ArrayList<>();
        subquestions.add(new Subquestion("Subquestion 1 text", "Subquestion 1 answer"));
        subquestions.add(new Subquestion("Subquestion 2 text", "Subquestion 2 answer "));

        QuestionMatching questionMatching = new QuestionMatching("questionMatching-1",
                new TextWithImages("Question Matching 1 text"), subquestions);

        List<NumericalAnswer> numericalAnswers = new ArrayList<>();
        numericalAnswers.add(new NumericalAnswer(15.0, 100, 0.0));
        numericalAnswers.add(new NumericalAnswer(-3.0, 100, 0.0));

        QuestionNumerical questionNumerical = new QuestionNumerical("questionNumerical-1",
                new TextWithImages("Question Numerical 1 text"), numericalAnswers);

        QuestionCollection questions = new QuestionCollection();

        QuestionCategory category1 = new QuestionCategory("Category-1");

        questions.addCategory(category1);
        questions.addQuestionToCategory(category1, questionShortAnswer);
        questions.addQuestionToCategory(category1, questionChoice);
        questions.addQuestionToCategory(category1, questionMatching);
        questions.addQuestionToCategory(category1, questionNumerical);

        try {
            JAXBContext context = JAXBContextFactory.createContext(
                    new Class[] {QuestionCollection.class}, null);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter1 = new StringWriter();
            marshaller.marshal(questions, stringWriter1);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            QuestionCollection questionsUnmarshall = (QuestionCollection) unmarshaller.unmarshal(
                    new StringReader(stringWriter1.toString()));

            StringWriter stringWriter2 = new StringWriter();
            marshaller.marshal(questionsUnmarshall, stringWriter2);

            Assertions.assertEquals(stringWriter1.toString(), stringWriter2.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
