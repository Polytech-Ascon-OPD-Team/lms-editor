package lmseditor.backend;

import lmseditor.backend.question.QuestionCollection;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class QuestionXmlParser {
    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public QuestionXmlParser() {
        try {
            jaxbContext = JAXBContextFactory.createContext(new Class[]{QuestionCollection.class}, null);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public String marshallToString(QuestionCollection questionCollection) {
        StringWriter stringWriter = new StringWriter();
        try {
            marshaller.marshal(questionCollection, stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    public void marshallToFile(QuestionCollection questionCollection, File file) {
        try {
            marshaller.marshal(questionCollection, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public QuestionCollection unmarshallFromFile(File file) {
        QuestionCollection questionCollection = null;
        try {
            questionCollection = (QuestionCollection) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return questionCollection;
    }

    public QuestionCollection unmarshallFromString(String string) {
        QuestionCollection questionCollection = null;
        try {
            questionCollection = (QuestionCollection) unmarshaller.unmarshal(new StringReader(string));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return questionCollection;
    }

}
