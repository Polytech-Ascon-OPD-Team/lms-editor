package lmseditor.gui.frame;

import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.panel.LeftPanel;
import lmseditor.gui.panel.QuestionShortAnswerWorkspace;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public JPanel workspacePanel;
    public MainFrame() {
        JPanel mainPanel = new JPanel();
        LeftPanel leftPanel = new LeftPanel();
        workspacePanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(workspacePanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(300, 0));

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(mainPanel);

        this.pack();
        this.setVisible(true);
    }

}
