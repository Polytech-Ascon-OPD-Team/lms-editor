package lmseditor.backend.question;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorValue;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlDiscriminatorValue("category")
public class QuestionCategory extends QuestionXml {

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
