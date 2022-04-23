package lmseditor.gui.frame;

import lmseditor.Main;
import lmseditor.StaticMethods;
import lmseditor.backend.QuestionXmlParser;
import lmseditor.gui.customComponents.StandardButton;
import lmseditor.gui.panel.LeftPanel;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.Workspace;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private LeftPanel leftPanel;
    private Workspace workspace;
    private QuestionXmlParser parser;

    public MainFrame() {
        mainPanel = new JPanel();
        leftPanel = new LeftPanel();
        parser = new QuestionXmlParser();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(300, 0));

        workspace = new EmptyWorkspace();

        // parse test
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new GridLayout(1,3));
        StandardButton parseButton = new StandardButton("Parse...");
        parseButton.setAction(() -> {
            workspace.loadData();
            String xml = parser.marshallToString(leftPanel.getQuestionCollection());
            System.out.println(xml);
        });
        downPanel.add(new JPanel());
        downPanel.add(parseButton);
        downPanel.add(new JPanel());
        mainPanel.add(downPanel, BorderLayout.SOUTH);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
    }

    public void setWorkspace(Workspace workspace) {
        mainPanel.remove(this.workspace);
        this.workspace = workspace;
        mainPanel.add(workspace, BorderLayout.CENTER);
        mainPanel.updateUI();
    }

    public Workspace getWorkspace() {
        return workspace;
    }

}
