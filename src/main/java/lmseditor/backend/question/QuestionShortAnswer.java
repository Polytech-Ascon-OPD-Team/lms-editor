package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import lmseditor.backend.question.component.answer.ShortAnswer;
import lmseditor.backend.question.text.TextWithImages;
import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionShortAnswer extends Question{

    @XmlAttribute(name = "type")
    public static final String TYPE = "shortanswer";

    @XmlPath("answer")
    private List<ShortAnswer> answers;

    public QuestionShortAnswer() {
        super();
        this.answers = new ArrayList<>();
        answers.add(new ShortAnswer("text1", 100));
        answers.add(new ShortAnswer("text2", 100));
        //TODO
    }

    public QuestionShortAnswer(String name, TextWithImages textWithImages, List<ShortAnswer> answers) {
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
