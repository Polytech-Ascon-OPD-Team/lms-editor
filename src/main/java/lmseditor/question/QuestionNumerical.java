package lmseditor.question;

import lmseditor.question.component.Answer;
import lmseditor.question.component.NumericalAnswer;
import lmseditor.question.component.QuestionText;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionNumerical extends Question {

    @XmlAttribute(name = "type")
    private static final String TYPE = "numerical";

    @XmlElement(name = "answer")
    private List<NumericalAnswer> answers;

    public QuestionNumerical() {
        super();
        this.answers = new ArrayList<>();
    }

    public QuestionNumerical(String name, QuestionText questionText, List<NumericalAnswer> answers) {
        super(name, questionText);
        this.answers = answers;
    }

    public List<NumericalAnswer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<NumericalAnswer> answers) {
        this.answers = answers;
    }

}
