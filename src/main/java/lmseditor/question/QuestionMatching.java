package lmseditor.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.question.component.TextWithImages;
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

    public QuestionMatching(String name, TextWithImages textWithImages, List<Subquestion> subquestions) {
        super(name, textWithImages);
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
