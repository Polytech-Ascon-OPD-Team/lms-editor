package lmseditor.question;

import javax.xml.bind.annotation.*;

import lmseditor.question.component.TextWithImages;
import lmseditor.question.component.TextWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlElement(name = "name")
    private TextWrapper name;

    @XmlElement(name = "questiontext")
    private TextWithImages textWithImages;

    public Question() {
        this.name = new TextWrapper();
        this.textWithImages = new TextWithImages();
    }

    public Question(String name, TextWithImages textWithImages) {
        this.name = new TextWrapper(name);
        this.textWithImages = textWithImages;
    }

    public String getName() {
        return this.name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

}
