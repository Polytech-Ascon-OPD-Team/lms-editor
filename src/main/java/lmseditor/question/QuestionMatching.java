package lmseditor.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.question.component.QuestionText;
import lmseditor.question.component.Subquestion;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionMatching extends Question {

    @XmlAttribute(name = "type")
    private static final String TYPE = "matching";

    @XmlElement(name = "subquestion")
    private List<Subquestion> subquestions;

    @XmlElement(name = "shuffleanswers")
    private boolean shuffleAnswers;

    public QuestionMatching() {
        super();
        this.subquestions = new ArrayList<>();
        this.shuffleAnswers = true;
    }

    public QuestionMatching(String name, QuestionText questionText, List<Subquestion> subquestions) {
        super(name, questionText);
        this.subquestions = subquestions;
        this.shuffleAnswers = true;
    }

    public List<Subquestion> getSubquestions() {
        return this.subquestions;
    }

    public void setSubquestions(List<Subquestion> subquestions) {
        this.subquestions = subquestions;
    }

    public boolean isShuffleAnswers() {
        return this.shuffleAnswers;
    }

    public void setShuffleAnswers(boolean shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

}
