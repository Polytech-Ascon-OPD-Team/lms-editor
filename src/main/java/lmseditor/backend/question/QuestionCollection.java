package lmseditor.backend.question;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionCollection {

    @XmlElement(name = "question")
    private List<QuestionXml> questions;

    public QuestionCollection() {
        this.questions = new ArrayList<>();
    }

    public void addCategory(QuestionCategory questionCategory) {
        questions.add(questionCategory);
    }

    public void addQuestionToCategory(QuestionCategory questionCategory, Question question) {
        int categoryIndex = questions.indexOf(questionCategory);
        questions.add(categoryIndex + 1, question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void removeCategory(QuestionCategory category) {
        int index = questions.indexOf(category);
        questions.remove(index);
        while (index < questions.size() && !(questions.get(index) instanceof QuestionCategory)) {
            questions.remove(index);
        }
    }

}
