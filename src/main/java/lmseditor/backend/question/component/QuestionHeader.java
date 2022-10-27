package lmseditor.backend.question.component;

import lmseditor.backend.question.adapter.QuestionHeaderAdapter;
import lmseditor.backend.question.text.TextWithImages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(QuestionHeaderAdapter.class)
public class QuestionHeader {

    @XmlElement(name = "name")
    private QuestionName name;

    @XmlElement(name = "questiontext")
    private TextWithImages textWithImages;

    public QuestionHeader() {
        this.name = new QuestionName();
        this.textWithImages = new TextWithImages();
    }

    public QuestionHeader(QuestionName name) {
        this.name = name;
        this.textWithImages = new TextWithImages();
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
