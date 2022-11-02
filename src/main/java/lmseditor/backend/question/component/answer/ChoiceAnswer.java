package lmseditor.backend.question.component.answer;

import lmseditor.backend.question.adapter.TextWithImagesAdapter;
import lmseditor.backend.question.text.TextWithImages;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class ChoiceAnswer extends Answer {

    @XmlPath(".")
    @XmlJavaTypeAdapter(TextWithImagesAdapter.class)
    private TextWithImages textWithImages;

    public ChoiceAnswer() {
        super();
        this.textWithImages = new TextWithImages();
    }

    public TextWithImages getTextWithImages() {
        return textWithImages;
    }

    public void setTextWithImages(TextWithImages textWithImages) {
        this.textWithImages = textWithImages;
    }

}
