package lmseditor.backend.question.text;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.adapter.TextWithImagesAdapter;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import lmseditor.backend.question.text.format.Formatter;
import lmseditor.backend.question.text.format.HtmlFormatter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlJavaTypeAdapter(TextWithImagesAdapter.class)
public class TextWithImages {

    @XmlAttribute
    private String format;

    @XmlElement(name = "text")
    @XmlCDATA
    private String xmlText;

    @XmlElement(name = "file")
    private List<ImageBase64> images;

    @XmlTransient
    private StringBuilder text;

    @XmlTransient
    private Formatter formatter;

    public TextWithImages() {
        this.formatter = new HtmlFormatter();
        this.xmlText = "";
        this.images = new ArrayList<ImageBase64>();
        this.text = new StringBuilder();
        this.format = formatter.getFormatOption();
    }

    public List<ImageBase64> getImageList() {
        return images;
    }

    public void setImageList(List<ImageBase64> images) {
        this.images = images;
    }

    public String getText() {
        return text.toString();
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }

    public StringBuilder getTextStringBuilder() {
        return text;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public String getXmlText() {
        return xmlText;
    }

    public void generateFormattedText() {
        xmlText = formatter.format(this);
    }

}
