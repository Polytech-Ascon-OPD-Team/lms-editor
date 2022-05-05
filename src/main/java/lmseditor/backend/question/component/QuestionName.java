package lmseditor.backend.question.component;

import lmseditor.backend.question.adapter.QuestionNameAdapter;
import lmseditor.backend.question.adapter.TextWithImagesAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(QuestionNameAdapter.class)
public class QuestionName {

    @XmlTransient
    private String id;

    @XmlTransient
    private String name;

    @XmlElement(name = "text")
    private String fullName;

    public QuestionName() {
        this.id = "";
        this.name = "";
        this.fullName = this.calculateFullName(this.id, this.name);
    }

    public QuestionName(String id, String name) {
        this.id = id;
        this.name = name;
        this.fullName = this.calculateFullName(this.id, this.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.fullName = this.calculateFullName(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.fullName = this.calculateFullName(this.id, this.name);
    }

    public String getFullName() {
        return fullName;
    }

    private String calculateFullName(String id, String name) {
        if (name.equals("")) {
            return id;
        }
        return id + " ~ " + name;
    }

}
