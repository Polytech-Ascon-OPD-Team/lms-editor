package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.component.answer.ChoiceAnswer;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlDiscriminatorValue("multichoice")
public class QuestionChoice extends Question {

    @XmlElement(name = "single")
    private boolean isSingleChoice;

    @XmlElement(name = "answer")
    private List<ChoiceAnswer> choiceAnswers;

    public QuestionChoice() {
        super();
        this.isSingleChoice = true;
        this.choiceAnswers = new ArrayList<>();
    }

    public QuestionChoice(QuestionHeader questionHeader, boolean isSingleChoice, List<ChoiceAnswer> choiceAnswers) {
        super(questionHeader);
        this.isSingleChoice = isSingleChoice;
        this.choiceAnswers = choiceAnswers;
    }

    public boolean isSingleChoice() {
        return this.isSingleChoice;
    }

    public void setSingleChoice(boolean singleChoice) {
        this.isSingleChoice = singleChoice;
    }

    public List<ChoiceAnswer> getAnswers() {
        return this.choiceAnswers;
    }

    public void setAnswers(List<ChoiceAnswer> choiceAnswers) {
        this.choiceAnswers = choiceAnswers;
    }

}
