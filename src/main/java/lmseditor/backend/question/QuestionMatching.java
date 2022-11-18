package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.component.Subquestion;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlDiscriminatorValue("matching")
public class QuestionMatching extends Question {

    @XmlElement(name = "subquestion")
    private List<Subquestion> subquestions;

    @XmlElement(name = "shuffleanswers")
    private boolean shuffleAnswers;

    public QuestionMatching() {
        super();
        this.subquestions = new ArrayList<>();
        this.shuffleAnswers = true;
    }

    public QuestionMatching(QuestionHeader questionHeader, List<Subquestion> subquestions) {
        super(questionHeader);
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
