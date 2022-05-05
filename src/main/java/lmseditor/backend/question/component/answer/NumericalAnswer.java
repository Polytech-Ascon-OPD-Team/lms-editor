package lmseditor.backend.question.component.answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class NumericalAnswer extends Answer {

    @XmlElement(name = "text")
    private double answer;

    @XmlElement(name = "tolerance")
    private double tolerance;

    public NumericalAnswer() {
        super();
        this.answer = 0.0;
        this.tolerance = 0.0;
    }

    public NumericalAnswer(double answer, int fraction, double tolerance) {
        super(fraction);
        this.answer = answer;
        this.tolerance = tolerance;
    }

    public double getAnswer() {
        return this.answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

}
