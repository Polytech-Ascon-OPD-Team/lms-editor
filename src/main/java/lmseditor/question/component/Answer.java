package lmseditor.question.component;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Answer {

    @XmlElement(name = "text")
    private String text;

    @XmlAttribute(name = "fraction")
    private int fraction;

    public Answer() {
        this.text = "";
        this.fraction = 0;
    }

    public Answer(String text, int fraction) {
        this.text = text;
        this.fraction = fraction;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFraction() {
        return this.fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
