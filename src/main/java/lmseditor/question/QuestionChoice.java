package lmseditor.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.question.component.Answer;
import lmseditor.question.component.QuestionText;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionChoice extends Question {

    @XmlAttribute(name = "type")
    private static final String TYPE = "multichoice";

    @XmlElement(name = "single")
    private boolean isSingleChoice;

    @XmlElement(name = "answer")
    private List<Answer> answers;

    public QuestionChoice() {
        super();
        this.isSingleChoice = true;
        this.answers = new ArrayList<>();
    }

    public QuestionChoice(String name, QuestionText questionText, boolean isSingleChoice, List<Answer> answers) {
        super(name, questionText);
        this.isSingleChoice = isSingleChoice;
        this.answers = answers;
    }

    public boolean isSingleChoice() {
        return this.isSingleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        this.isSingleChoice = singleChoice;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
