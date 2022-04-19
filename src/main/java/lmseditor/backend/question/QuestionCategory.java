package lmseditor.backend.question;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionCategory extends QuestionXml {

    @XmlAttribute(name = "type")
    private static final String TYPE = "category";

    @XmlPath("category/text/text()")
    private String name;

    public QuestionCategory() {
        this.name = "";
    }

    public QuestionCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
