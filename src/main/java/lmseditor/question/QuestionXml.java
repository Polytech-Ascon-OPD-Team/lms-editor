package lmseditor.question;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({QuestionShortAnswer.class, QuestionChoice.class, QuestionMatching.class, QuestionNumerical.class, QuestionCategory.class})
public abstract class QuestionXml {

}
