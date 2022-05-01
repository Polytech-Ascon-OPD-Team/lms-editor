package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.component.answer.ShortAnswer;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.backend.question.text.TextWithImages;
import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;
import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlDiscriminatorValue("shortanswer")
public class QuestionShortAnswer extends Question{

    @XmlPath("answer")
    private List<ShortAnswer> answers;

    public QuestionShortAnswer() {
        super();
        this.answers = new ArrayList<>();
    }

    public QuestionShortAnswer(QuestionName name, QuestionText textWithImages, List<ShortAnswer> answers) {
        super(name, textWithImages);
        this.answers = answers;
    }

    public List<ShortAnswer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<ShortAnswer> answers) {
        this.answers = answers;
    }

}
