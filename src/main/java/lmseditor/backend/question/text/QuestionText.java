package lmseditor.backend.question.text;

import lmseditor.backend.question.adapter.QuestionTextAdapter;
import lmseditor.backend.question.component.QuestionName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(QuestionTextAdapter.class)
public class QuestionText extends TextWithImages {

    @XmlTransient
    private QuestionName name;

    private QuestionText() {
        name = new QuestionName();
    }

    public QuestionText(QuestionName name) {
        this.name = name;
    }

    public void setName(QuestionName name) {
        this.name = name;
    }

    @Override
    public void generateFormattedText() {
        super.generateFormattedText();
        xmlText = name.getFullName() + "<br>" + xmlText;
    }

}
