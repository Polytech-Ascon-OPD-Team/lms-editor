package lmseditor.backend.question;

import javax.xml.bind.annotation.*;

import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.TextWithImages;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlElement(name = "name")
    private QuestionName name;

    @XmlElement(name = "questiontext")
    private TextWithImages textWithImages;

    public Question() {
        this.name = new QuestionName();
        this.textWithImages = new TextWithImages();
    }

    public Question(QuestionName name, TextWithImages textWithImages) {
        this.name = name;
        this.textWithImages = textWithImages;
    }

    public QuestionName getName() {
        return name;
    }

    public void setName(QuestionName name) {
        this.name = name;
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

}
