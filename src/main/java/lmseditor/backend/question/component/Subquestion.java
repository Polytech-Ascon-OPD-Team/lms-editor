package lmseditor.backend.question.component;

import lmseditor.backend.question.adapter.TextWithImagesAdapter;
import lmseditor.backend.question.text.TextWithImages;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Subquestion {

    @XmlPath(".")
    @XmlJavaTypeAdapter(TextWithImagesAdapter.class)
    private TextWithImages textWithImages;

    @XmlPath("answer/text/text()")
    private String answerText;

    public Subquestion() {
        this.textWithImages = new TextWithImages();
        this.answerText = "";
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

