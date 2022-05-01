package guitest;

import lmseditor.backend.question.QuestionMatching;
import lmseditor.gui.panel.workspace.QuestionMatchingWorkspace;

import javax.swing.*;
import java.awt.*;

public class Workspace {
    public static void main(String[] args) {

        JFrame test = new JFrame("TEST");
        test.getContentPane().setLayout(new BorderLayout());
        test.getContentPane().add(new JTextField(), BorderLayout.CENTER);
        test.setVisible(true);
    }
}
