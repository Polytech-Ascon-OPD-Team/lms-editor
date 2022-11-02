package lmseditor.backend.question;

import lmseditor.backend.question.component.QuestionHeader;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.component.answer.NumericalAnswer;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlDiscriminatorValue("numerical")
public class QuestionNumerical extends Question {

    @XmlElement(name = "answer")
    private List<NumericalAnswer> answers;

    public QuestionNumerical() {
        super();
        this.answers = new ArrayList<>();
    }

    public QuestionNumerical(QuestionHeader questionHeader, List<NumericalAnswer> answers) {
        super(questionHeader);
        this.answers = answers;
    }

    public List<NumericalAnswer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<NumericalAnswer> answers) {
        this.answers = answers;
    }

}
