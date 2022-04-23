package lmseditor;

import lmseditor.backend.question.*;
import lmseditor.gui.customComponents.AdvancedLayouter;
import lmseditor.gui.customComponents.CPanel;
import lmseditor.gui.frame.MainFrame;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class StaticMethods {
    //TODO
    private static boolean mutex = false;

    public static Question makeQuestion() {
        JDialog dialog = new JDialog(Main.mainFrame);
        //dialog.setModal(true);
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(new JLabel("Выберете тип вопроса:"), BorderLayout.NORTH);
        JComboBox jComboBox = new JComboBox(new String[]{"C выбором", "Текстовый", "Числовой", "Сопоставление"});
        dialog.getContentPane().add(jComboBox, BorderLayout.CENTER);
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1, 3));
        JButton okButton = new JButton("ОК");
        okButton.addActionListener(e -> {
            mutex = true;
        });
        downPanel.add(new JPanel());
        downPanel.add(okButton);
        downPanel.add(new JPanel());
        dialog.getContentPane().add(downPanel, BorderLayout.SOUTH);
        dialog.setSize(400, 100);
        dialog.setModal(true);
        //dialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setUndecorated(true);
        dialog.setVisible(true);
        dialog.setAlwaysOnTop(true);
        while (!mutex) {
        }
        String str = (String) jComboBox.getSelectedItem();
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        switch (str) {
            case "C выбором":
                return new QuestionChoice();
            case "Текстовый":
                return new QuestionShortAnswer();
            case "Числовой":
                return new QuestionNumerical();
            case "Сопоставление":
                return new QuestionMatching();
        }
        return null;
    }

    public static String parse(QuestionCollection questionCollection) {
        try {
            JAXBContext context = JAXBContextFactory.createContext(
                    new Class[]{QuestionCollection.class}, null);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter1 = new StringWriter();
            marshaller.marshal(questionCollection, stringWriter1);
            return stringWriter1.toString();
        } catch (JAXBException e){
            e.printStackTrace();
        }
        return "ERROR!";
    }
}
