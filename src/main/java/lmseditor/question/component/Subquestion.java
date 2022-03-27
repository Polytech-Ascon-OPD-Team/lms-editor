package lmseditor.question.component;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subquestion {

    @XmlElement(name = "text")
    private String text;

    @XmlElement(name = "answer")
    private TextWrapper answer;

    public Subquestion() {
        this.text = "";
        this.answer = new TextWrapper();
    }

    public Subquestion(String text, String answerText) {
        this.text = text;
        this.answer = new TextWrapper(answerText);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswerText() {
        return this.answer.getText();
    }

    public void setAnswerText(String answerText) {
        this.answer.setText(answerText);
    }

}

