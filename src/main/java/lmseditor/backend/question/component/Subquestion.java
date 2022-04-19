package lmseditor.backend.question.component;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subquestion {

    @XmlPath(".")
    private TextWithImages textWithImages;

    @XmlPath("answer/text/text()")
    private String answerText;

    public Subquestion() {
        this.textWithImages = new TextWithImages();
        this.answerText = "";
    }

    public Subquestion(String text, String answerText) {
        this.textWithImages = new TextWithImages(text);
        this.answerText = answerText;
    }

    public String getText() {
        return this.textWithImages.getText();
    }

    public void setText(String text) {
        this.textWithImages.setText(text);
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}

