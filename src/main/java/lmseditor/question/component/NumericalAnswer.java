package lmseditor.question.component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class NumericalAnswer {

    @XmlElement(name = "text")
    private double answer;

    @XmlAttribute(name = "fraction")
    private int fraction;

    @XmlElement(name = "tolerance")
    private double tolerance;

    public NumericalAnswer() {
        this.answer = 0.0;
        this.fraction = 0;
        this.tolerance = 0.0;
    }

    public NumericalAnswer(double answer, int fraction, double tolerance) {
        this.answer = answer;
        this.fraction = fraction;
        this.tolerance = tolerance;
    }

    public double getAnswer() {
        return this.answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public int getFraction() {
        return this.fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

}
