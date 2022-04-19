package lmseditor.question.component;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subquestion {

    @XmlPath(".")
    private TextWithImages textWithImages;

    @XmlElement(name = "answer")
    private TextWrapper answer;

    public Subquestion() {
        this.textWithImages = new TextWithImages();
        this.answer = new TextWrapper();
    }

    public Subquestion(String text, String answerText) {
        this.textWithImages = new TextWithImages(text);
        this.answer = new TextWrapper(answerText);
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
        return this.answer.getText();
    }

    public void setAnswerText(String answerText) {
        this.answer.setText(answerText);
    }

}

