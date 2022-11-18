import lmseditor.backend.question.Question;
import lmseditor.backend.question.QuestionCategory;
import lmseditor.backend.question.QuestionCollection;
import lmseditor.backend.question.QuestionShortAnswer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionCollectionTest {

    @Test
    public void addCategory() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);

        Assertions.assertEquals(2, questionCollection.getCategoriesList().size());
        Assertions.assertEquals(category1, questionCollection.getCategoriesList().get(0));
        Assertions.assertEquals(category2, questionCollection.getCategoriesList().get(1));
    }

    @Test
    public void removeCategory() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);

        questionCollection.removeCategory(category1);

        Assertions.assertEquals(1, questionCollection.getCategoriesList().size());
        Assertions.assertEquals(category2, questionCollection.getCategoriesList().get(0));
    }

    @Test
    public void getQuestionsFromCategory() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);
        Question question1 = new QuestionShortAnswer();
        Question question2 = new QuestionShortAnswer();
        Question question3 = new QuestionShortAnswer();
        questionCollection.addQuestionToCategory(category1, question1);
        questionCollection.addQuestionToCategory(category2, question2);
        questionCollection.addQuestionToCategory(category1, question3);

        Assertions.assertEquals(2, questionCollection.getQuestionsFromCategory(category1).size());
        Assertions.assertEquals(question1, questionCollection.getQuestionsFromCategory(category1).get(0));
        Assertions.assertEquals(question3, questionCollection.getQuestionsFromCategory(category1).get(1));
        Assertions.assertEquals(1, questionCollection.getQuestionsFromCategory(category2).size());
        Assertions.assertEquals(question2, questionCollection.getQuestionsFromCategory(category2).get(0));
    }

    @Test
    public void removeCategoryWithQuestions() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);
        Question question1 = new QuestionShortAnswer();
        Question question2 = new QuestionShortAnswer();
        Question question3 = new QuestionShortAnswer();
        questionCollection.addQuestionToCategory(category1, question1);
        questionCollection.addQuestionToCategory(category2, question2);
        questionCollection.addQuestionToCategory(category1, question3);

        questionCollection.removeCategory(category1);

        Assertions.assertEquals(1, questionCollection.getCategoriesList().size());
        Assertions.assertEquals(category2, questionCollection.getCategoriesList().get(0));
        Assertions.assertEquals(1, questionCollection.getQuestionsFromCategory(category2).size());
        Assertions.assertEquals(question2, questionCollection.getQuestionsFromCategory(category2).get(0));
    }

    @Test
    public void addQuestionToCategory() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);
        Question question1 = new QuestionShortAnswer();
        Question question2 = new QuestionShortAnswer();
        Question question3 = new QuestionShortAnswer();

        questionCollection.addQuestionToCategory(category1, question1);
        questionCollection.addQuestionToCategory(category2, question2);
        questionCollection.addQuestionToCategory(category1, question3);

        Assertions.assertEquals(2, questionCollection.getCategoriesList().size());
        Assertions.assertEquals(category1, questionCollection.getCategoriesList().get(0));
        Assertions.assertEquals(category2, questionCollection.getCategoriesList().get(1));

        Assertions.assertEquals(2, questionCollection.getQuestionsFromCategory(category1).size());
        Assertions.assertEquals(question1, questionCollection.getQuestionsFromCategory(category1).get(0));
        Assertions.assertEquals(question3, questionCollection.getQuestionsFromCategory(category1).get(1));

        Assertions.assertEquals(1, questionCollection.getQuestionsFromCategory(category2).size());
        Assertions.assertEquals(question2, questionCollection.getQuestionsFromCategory(category2).get(0));
    }

    @Test
    public void removeQuestion() {
        QuestionCollection questionCollection = new QuestionCollection();
        QuestionCategory category1 = new QuestionCategory("category1");
        QuestionCategory category2 = new QuestionCategory("category2");
        questionCollection.addCategory(category1);
        questionCollection.addCategory(category2);
        Question question1 = new QuestionShortAnswer();
        Question question2 = new QuestionShortAnswer();
        Question question3 = new QuestionShortAnswer();
        questionCollection.addQuestionToCategory(category1, question1);
        questionCollection.addQuestionToCategory(category2, question2);
        questionCollection.addQuestionToCategory(category1, question3);

        questionCollection.removeQuestion(question2);
        questionCollection.removeQuestion(question3);

        Assertions.assertEquals(2, questionCollection.getCategoriesList().size());
        Assertions.assertEquals(category1, questionCollection.getCategoriesList().get(0));
        Assertions.assertEquals(category2, questionCollection.getCategoriesList().get(1));
        Assertions.assertEquals(1, questionCollection.getQuestionsFromCategory(category1).size());
        Assertions.assertEquals(question1, questionCollection.getQuestionsFromCategory(category1).get(0));
        Assertions.assertEquals(0, questionCollection.getQuestionsFromCategory(category2).size());
    }

}
