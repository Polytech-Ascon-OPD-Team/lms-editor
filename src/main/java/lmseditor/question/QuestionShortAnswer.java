package lmseditor.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.question.component.Answer;
import lmseditor.question.component.QuestionText;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionShortAnswer extends Question{

    @XmlAttribute(name = "type")
    public static final String TYPE = "shortanswer";

    @XmlElement(name = "answer")
    private List<Answer> answers;

    public QuestionShortAnswer() {
        super();
        this.answers = new ArrayList<>();
    }

    public QuestionShortAnswer(String name, QuestionText questionText, List<Answer> answers) {
        super(name, questionText);
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
