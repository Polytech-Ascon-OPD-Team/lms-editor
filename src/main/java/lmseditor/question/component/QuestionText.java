package lmseditor.question.component;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionText {

    @XmlElement(name = "text")
    private String text;

    @XmlElement(name = "file")
    private List<ImageBase64> images;

    public QuestionText() {
        this.text = "";
        this.images = new ArrayList<>();
    }

    public QuestionText(String text) {
        this.text = text;
        this.images = new ArrayList<>();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ImageBase64> getImages() {
        return images;
    }

}
