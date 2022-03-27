package lmseditor.question.component;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionText {

    @XmlElement(name = "text")
    private String text;

    public QuestionText() {
        this.text = "";
    }

    public QuestionText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
