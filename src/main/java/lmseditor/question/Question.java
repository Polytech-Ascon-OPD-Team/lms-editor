package lmseditor.question;

import javax.xml.bind.annotation.*;

import lmseditor.question.component.QuestionText;
import lmseditor.question.component.TextWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlElement(name = "name")
    private TextWrapper name;

    @XmlElement(name = "questiontext")
    private QuestionText questionText;

    public Question() {
        this.name = new TextWrapper();
        this.questionText = new QuestionText();
    }

    public Question(String name, QuestionText questionText) {
        this.name = new TextWrapper(name);
        this.questionText = questionText;
    }

    public String getName() {
        return this.name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public QuestionText getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

}
