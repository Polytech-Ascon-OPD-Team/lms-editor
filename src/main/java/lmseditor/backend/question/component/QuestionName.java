package lmseditor.backend.question.component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
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
        this.fullName = this.getFullName(this.id, this.name);
    }

    public QuestionName(String id, String name) {
        this.id = id;
        this.name = name;
        this.fullName = this.getFullName(this.id, this.name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.fullName = this.getFullName(this.id, this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.fullName = this.getFullName(this.id, this.name);
    }

    public String getFullName() {
        return fullName;
    }

    private String getFullName(String id, String name) {
        return id + " " + name;
    }

}
