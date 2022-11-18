package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import lmseditor.backend.question.component.QuestionHeader;
import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlElement(name = "penalty")
    private double penalty = 0.0;

    @XmlPath(".")
    private QuestionHeader questionHeader;

    public Question() {
        this.questionHeader = new QuestionHeader();
    }

    public Question(QuestionHeader questionHeader) {
        this.questionHeader = questionHeader;
    }

    public QuestionHeader getQuestionHeader() {
        return questionHeader;
    }

    public QuestionType getType(){
        if (this instanceof QuestionChoice) return QuestionType.CHOICE;
        else if (this instanceof QuestionMatching) return QuestionType.MATCHING;
        else if (this instanceof QuestionShortAnswer) return QuestionType.SHORT_ANSWER;
        else if (this instanceof QuestionNumerical) return QuestionType.NUMERICAL;
        else return null;
    }

}
