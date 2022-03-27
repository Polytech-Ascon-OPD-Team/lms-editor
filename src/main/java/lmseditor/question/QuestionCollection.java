package lmseditor.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionCollection {

    @XmlElement(name = "question")
    private List<Question> questions;

    public QuestionCollection() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

}
