package lmseditor.backend.question.text;

import lmseditor.backend.question.component.QuestionName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionText extends TextWithImages {

    @XmlTransient
    private final QuestionName name;

    private QuestionText() {
        name = new QuestionName();
    }

    public QuestionText(QuestionName name) {
        this.name = name;
    }

    @Override
    public void generateFormattedText() {
        super.generateFormattedText();
        xmlText = name.getFullName() + "<br/>" + xmlText;
    }

}
