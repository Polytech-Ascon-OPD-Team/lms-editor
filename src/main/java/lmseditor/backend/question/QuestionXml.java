package lmseditor.backend.question;

import org.eclipse.persistence.oxm.annotations.XmlDiscriminatorNode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({QuestionShortAnswer.class, QuestionChoice.class, QuestionMatching.class, QuestionNumerical.class, QuestionCategory.class})
@XmlDiscriminatorNode("@type")
public abstract class QuestionXml {

}
