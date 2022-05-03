package lmseditor.backend.question;

import javax.xml.bind.annotation.*;

import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.backend.question.text.TextWithImages;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question extends QuestionXml {

    @XmlElement(name = "name")
    private QuestionName name;

    @XmlElement(name = "questiontext")
    private QuestionText questionText;

    public Question() {
        this.name = new QuestionName();
        this.questionText = new QuestionText(name);
    }

    public Question(QuestionName name, QuestionText questionText) {
        this.name = name;
        this.questionText = questionText;
    }

    public QuestionName getName() {
        return name;
    }

    public void setName(QuestionName name) {
        this.name = name;
    }

    public QuestionText getQuestionText() {
        return questionText;
    }

    public QuestionType getType(){
        if (this instanceof QuestionChoice) return QuestionType.CHOICE;
        else if (this instanceof QuestionMatching) return QuestionType.MATCHING;
        else if (this instanceof QuestionShortAnswer) return QuestionType.SHORT_ANSWER;
        else if (this instanceof QuestionNumerical) return QuestionType.NUMERICAL;
        else return null;
    }
}
