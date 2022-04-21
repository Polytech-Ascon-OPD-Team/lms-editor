package lmseditor.gui.panel;

import lmseditor.backend.question.QuestionNumerical;
import lmseditor.backend.question.QuestionShortAnswer;

import javax.swing.*;

public class MainPanel extends JPanel {

    public MainPanel() {
//        QuestionShortAnswer questionShortAnswer = new QuestionShortAnswer();
//        QuestionShortAnswerWorkspace matchingWorkspace = new QuestionShortAnswerWorkspace(questionShortAnswer);
//        this.add(matchingWorkspace);

        QuestionNumerical questionNumerical = new QuestionNumerical();
        QuestionNumericalAnswerWorkspace matchingWorkspace = new QuestionNumericalAnswerWorkspace(questionNumerical);
        this.add(matchingWorkspace);

    }

}
