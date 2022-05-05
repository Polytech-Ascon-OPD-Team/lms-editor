package lmseditor.gui.dialog;

import lmseditor.Main;
import lmseditor.backend.question.*;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.QuestionMatchingWorkspace;
import lmseditor.gui.panel.workspace.QuestionShortAnswerWorkspace;
import lmseditor.gui.panel.workspace.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;

public class QuestionTypeDialog extends JDialog {
    private static final String TITLE = "Выберите тип вопроса";
    private static final String[] TYPES = {"C выбором", "Текстовый", "Числовой", "Сопоставление"};

    private JComboBox<String> comboBox;

    public QuestionTypeDialog() {
        super(Main.mainFrame, TITLE, true);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());

        comboBox = new JComboBox<>(TYPES);

        JButton okButton = new JButton("Ок");
        okButton.addActionListener(this::okButtonEvent);

        contentPane.add(comboBox);
        contentPane.add(okButton);
        this.setModal(true);
        this.setLocationRelativeTo(Main.mainFrame);
        this.setUndecorated(true);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public QuestionType getSelectedType() {
        switch (comboBox.getSelectedIndex()) {
            case 0: return QuestionType.CHOICE;
            case 1: return QuestionType.SHORT_ANSWER;
            case 2: return QuestionType.NUMERICAL;
            case 3: return QuestionType.MATCHING;
            default: return null;
        }
    }

    private void okButtonEvent(ActionEvent actionEvent) {
        this.setVisible(false);
    }



}
