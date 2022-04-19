package lmseditor.question.component;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import lmseditor.question.component.format.Formatter;
import lmseditor.question.component.format.HtmlFormatter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextWithImages {

    @XmlAttribute
    private String format;

    @XmlElement(name = "text")
    @XmlCDATA
    private String xmlText;

    @XmlElement(name = "file")
    private List<ImageBase64> images;

    @XmlTransient
    private String text;

    @XmlTransient
    private Formatter formatter;

    public TextWithImages() {
        this.formatter = new HtmlFormatter();
        this.xmlText = "";
        this.text = "";
        this.images = new ArrayList<>();
        this.format = formatter.getFormatOption();
    }

    public TextWithImages(String text) {
        this();
        this.text = text;
        this.formatter.setText(text);
        this.xmlText = formatter.getFormattedString();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        formatter.setText(text);
        xmlText = formatter.getFormattedString();
    }

    public void addImage(ImageBase64 image) {
        images.add(image);
        formatter.addImage(image);
        xmlText = formatter.getFormattedString();
    }

    public ImageBase64 getImage(int index) {
        return images.get(index);
    }

    public ImageBase64 removeImage(int index) {
        formatter.removeImageCode(index);
        xmlText = formatter.getFormattedString();
        return images.remove(index);
    }

}
