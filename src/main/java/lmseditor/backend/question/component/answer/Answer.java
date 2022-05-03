package lmseditor.backend.question.component.answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Answer {

    @XmlAttribute(name = "fraction")
    private double fraction;

    public Answer() {
        this.fraction = 0;
    }

    public Answer(double fraction) {
        this.fraction = fraction;
    }

    public double getFraction() {
        return fraction;
    }

    public void setFraction(double fraction) {
        this.fraction = fraction;
    }

}
