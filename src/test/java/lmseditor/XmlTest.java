package lmseditor;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.image.ImageList;
import lmseditor.backend.question.component.answer.ChoiceAnswer;
import lmseditor.backend.question.component.answer.NumericalAnswer;
import lmseditor.backend.question.component.answer.ShortAnswer;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.backend.question.text.TextWithImages;
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
        TextWithImages questionShortAnswerText = new TextWithImages();
        questionShortAnswerText.setQuestionText(new QuestionText("Question short answer 1 text"));
        ImageList imageList = new ImageList();
        imageList.getImages().add(new ImageBase64("img-1.png", "/", 100, 100, "base64code-1"));
        imageList.getImages().add(new ImageBase64("img-2.png", "/", 200, 200, "base64code-2"));
        questionShortAnswerText.setImageList(imageList);
        questionShortAnswerText.generateFormattedText();
        List<ShortAnswer> shortAnswers = new ArrayList<>();
        shortAnswers.add(new ShortAnswer("Correct answer 1", 100));
        shortAnswers.add(new ShortAnswer("Incorrect answer 1", 0));
        shortAnswers.add(new ShortAnswer("Incorrect answer 2", 0));

        QuestionShortAnswer questionShortAnswer = new QuestionShortAnswer("questionShortAnswer-1",
                questionShortAnswerText, shortAnswers);

        TextWithImages questionChoiceText = new TextWithImages();
        questionChoiceText.setQuestionText(new QuestionText("Question choice 1 text"));
        questionChoiceText.generateFormattedText();
        List<ChoiceAnswer> choiceAnswers = new ArrayList<>();
        ChoiceAnswer choiceAnswer1 = new ChoiceAnswer();
        choiceAnswer1.getTextWithImages().setQuestionText(new QuestionText("Correct answer 1"));
        choiceAnswer1.setFraction(100);
        choiceAnswer1.getTextWithImages().generateFormattedText();
        choiceAnswers.add(choiceAnswer1);
        ChoiceAnswer choiceAnswer2 = new ChoiceAnswer();
        choiceAnswer2.getTextWithImages().setQuestionText(new QuestionText("Incorrect answer 1"));
        choiceAnswer2.setFraction(0);
        choiceAnswer2.getTextWithImages().generateFormattedText();
        choiceAnswers.add(choiceAnswer2);
        ChoiceAnswer choiceAnswer3 = new ChoiceAnswer();
        choiceAnswer3.getTextWithImages().setQuestionText(new QuestionText("Incorrect answer 2"));
        choiceAnswer3.setFraction(0);
        choiceAnswer3.getTextWithImages().generateFormattedText();
        choiceAnswers.add(choiceAnswer3);
        QuestionChoice questionChoice = new QuestionChoice("questionChoice-1",
                questionChoiceText,false, choiceAnswers);

        List<Subquestion> subquestions = new ArrayList<>();
        Subquestion subquestion1 = new Subquestion();
        subquestion1.getTextWithImages().setQuestionText(new QuestionText("Subquestion 1 text"));
        subquestion1.setAnswerText("Subquestion 1 answer");
        subquestion1.getTextWithImages().generateFormattedText();
        subquestions.add(subquestion1);
        Subquestion subquestion2 = new Subquestion();
        subquestion2.getTextWithImages().setQuestionText(new QuestionText("Subquestion 2 text"));
        subquestion2.setAnswerText("Subquestion 2 answer");
        subquestion2.getTextWithImages().generateFormattedText();
        subquestions.add(subquestion2);
        TextWithImages questionMatchingText = new TextWithImages();
        questionMatchingText.setQuestionText(new QuestionText("Question Matching 1 text"));
        questionMatchingText.generateFormattedText();
        QuestionMatching questionMatching = new QuestionMatching("questionMatching-1",
                questionMatchingText, subquestions);

        List<NumericalAnswer> numericalAnswers = new ArrayList<>();
        numericalAnswers.add(new NumericalAnswer(15.0, 100, 0.0));
        numericalAnswers.add(new NumericalAnswer(-3.0, 100, 0.0));
        TextWithImages questionNumericalText = new TextWithImages();
        questionNumericalText.setQuestionText(new QuestionText("Question Numerical 1 text"));
        questionNumericalText.generateFormattedText();
        QuestionNumerical questionNumerical = new QuestionNumerical("questionNumerical-1",
                questionNumericalText, numericalAnswers);

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
