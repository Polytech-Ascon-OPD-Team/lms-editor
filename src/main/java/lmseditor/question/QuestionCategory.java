package lmseditor.question;

import lmseditor.question.component.TextWrapper;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionCategory extends QuestionXml {

    @XmlAttribute(name = "type")
    private static final String TYPE = "category";

    @XmlElement(name = "category")
    private TextWrapper name;

    public QuestionCategory() {
        this.name = new TextWrapper();
    }

    public QuestionCategory(String name) {
        this.name = new TextWrapper(name);
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

}
