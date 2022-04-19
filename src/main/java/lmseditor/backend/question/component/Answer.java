package lmseditor.backend.question.component;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Answer {

    @XmlPath(".")
    private TextWithImages textWithImages;

    @XmlAttribute(name = "fraction")
    private int fraction;

    public Answer() {
        this.textWithImages = new TextWithImages();
        this.fraction = 0;
    }

    public Answer(String text, int fraction) {
        this.textWithImages = new TextWithImages(text);
        this.fraction = fraction;
    }

    public String getText() {
        return this.textWithImages.getText();
    }

    public void setText(String text) {
        //this.text = text;
        this.textWithImages.setText(text);
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

    public int getFraction() {
        return this.fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
