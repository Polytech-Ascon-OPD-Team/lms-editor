package lmseditor.backend.question.text;

import lmseditor.backend.image.ImageList;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import lmseditor.backend.question.text.format.Formatter;
import lmseditor.backend.question.text.format.HtmlFormatter;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextWithImages {

    @XmlAttribute
    private String format;

    @XmlElement(name = "text")
    @XmlCDATA
    private String xmlText;

    @XmlPath(".")
    private ImageList imageList;

    @XmlTransient
    private QuestionText questionText;

    @XmlTransient
    private Formatter formatter;

    public TextWithImages() {
        this.formatter = new HtmlFormatter();
        this.xmlText = "";
        this.imageList = new ImageList();
        this.questionText = new QuestionText();
        this.format = formatter.getFormatOption();
    }

    public ImageList getImageList() {
        return imageList;
    }

    public void setImageList(ImageList imageList) {
        this.imageList = imageList;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public void setQuestionText(QuestionText questionText) {
        this.questionText = questionText;
    }

    public void generateFormattedText() {
        formatter.setText(questionText.getText());
        formatter.setImageList(imageList);
        xmlText = formatter.getFormattedString();
    }

}
