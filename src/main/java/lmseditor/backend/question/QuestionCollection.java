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
        int index = questions.indexOf(questionCategory) + 1;
        while ((index < questions.size()) && (questions.get(index) instanceof Question)) {
            index++;
        }
        questions.add(index, question);
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

    public List<QuestionCategory> getCategoriesList() {
        List<QuestionCategory> categoryList = new ArrayList<>();
        for (QuestionXml questionXml : questions) {
            if (questionXml instanceof QuestionCategory) {
                categoryList.add((QuestionCategory) questionXml);
            }
        }
        return categoryList;
    }

    public List<Question> getQuestionsFromCategory(QuestionCategory category) {
        List<Question> questionList = new ArrayList<>();
        int index = questions.indexOf(category);
        if (index == -1) {
            return null;
        }
        index++;
        while ((index < questions.size()) && (questions.get(index) instanceof Question)) {
            questionList.add((Question) questions.get(index));
            index++;
        }
        return questionList;
    }

}
