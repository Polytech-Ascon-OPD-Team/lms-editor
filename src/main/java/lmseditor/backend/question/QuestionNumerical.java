package lmseditor.backend.question;

import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.component.answer.NumericalAnswer;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.backend.question.text.TextWithImages;

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

    public QuestionNumerical(QuestionName name, QuestionText textWithImages, List<NumericalAnswer> answers) {
        super(name, textWithImages);
        this.answers = answers;
    }

    public List<NumericalAnswer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<NumericalAnswer> answers) {
        this.answers = answers;
    }

}
