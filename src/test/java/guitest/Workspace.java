package guitest;

import lmseditor.backend.question.QuestionMatching;
import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.workspace.QuestionMatchingWorkspace;
import lmseditor.gui.panel.workspace.QuestionShortAnswerWorkspace;

import javax.swing.*;
import java.awt.*;

public class Workspace {
    public static void main(String[] args) {

        JFrame test = new JFrame("TEST");
        test.getContentPane().setLayout(new BorderLayout());
        test.getContentPane().add(new QuestionShortAnswerWorkspace(new QuestionShortAnswer()), BorderLayout.CENTER);
        test.setVisible(true);
    }
}
