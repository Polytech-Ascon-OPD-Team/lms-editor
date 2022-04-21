package lmseditor.backend.question.component.answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Answer {

    @XmlAttribute(name = "fraction")
    private int fraction;

    public Answer() {
        this.fraction = 0;
    }

    public Answer(int fraction) {
        this.fraction = fraction;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

}
