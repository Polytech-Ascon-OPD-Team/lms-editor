package lmseditor.gui.panel;

import lmseditor.backend.question.QuestionShortAnswer;

import javax.swing.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        QuestionShortAnswer questionShortAnswer = new QuestionShortAnswer();
        QuestionShortAnswerWorkspace matchingWorkspace = new QuestionShortAnswerWorkspace(questionShortAnswer);
        this.add(matchingWorkspace);

    }

}
