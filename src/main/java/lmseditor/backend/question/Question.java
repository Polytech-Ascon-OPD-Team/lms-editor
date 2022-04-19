package lmseditor.backend.question;

import javax.xml.bind.annotation.*;

import lmseditor.backend.question.component.TextWithImages;
import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlPath("name/text/text()")
    private String name;

    @XmlElement(name = "questiontext")
    private TextWithImages textWithImages;

    public Question() {
        this.name = "";
        this.textWithImages = new TextWithImages();
    }

    public Question(String name, TextWithImages textWithImages) {
        this.name = name;
        this.textWithImages = textWithImages;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

}
