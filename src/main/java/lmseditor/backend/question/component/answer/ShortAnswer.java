package lmseditor.backend.question.component.answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShortAnswer extends Answer {

    @XmlElement(name = "text")
    private String text;

    public ShortAnswer() {
        super();
        this.text = "";
    }

    public ShortAnswer(String text, int fraction) {
        super(fraction);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
