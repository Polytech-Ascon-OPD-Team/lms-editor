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

public class QuestionTypeDialog extends JDialog {
    private static final String TITLE = "Выберите тип вопроса";
    private static final String[] TYPES = {"C выбором", "Текстовый", "Числовой", "Сопоставление"};

    private JComboBox<String> comboBox;

    private Question question;
    private Workspace workspace;

    public QuestionTypeDialog() {
        super(Main.mainFrame, TITLE, true);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());

        comboBox = new JComboBox<>(TYPES);

        JButton okButton = new JButton("Ок");
        okButton.addActionListener(this::okButtonEvent);

        contentPane.add(comboBox);
        contentPane.add(okButton);

        this.setLocationRelativeTo(Main.mainFrame);
        this.setUndecorated(true);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public Question getQuestion() {
        return question;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    private void okButtonEvent(ActionEvent actionEvent) {
        switch (comboBox.getSelectedIndex()) {
            case 0: {
                question = new QuestionChoice();
                workspace = new EmptyWorkspace();
                break;
            }
            case 1: {
                question = new QuestionShortAnswer();
                workspace = new QuestionShortAnswerWorkspace((QuestionShortAnswer) question);
                break;
            }
            case 2: {
                question = new QuestionNumerical();
                workspace = new EmptyWorkspace();
                break;
            }
            case 3: {
                question = new QuestionMatching();
                workspace = new QuestionMatchingWorkspace((QuestionMatching) question);
                break;
            }
        }
        this.setVisible(false);
    }



}
