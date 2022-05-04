package lmseditor.gui.frame;

import lmseditor.backend.QuestionXmlParser;
import lmseditor.gui.customComponents.StandardButton;
import lmseditor.gui.panel.LeftPanel;
import lmseditor.gui.panel.workspace.EmptyWorkspace;
import lmseditor.gui.panel.workspace.Workspace;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

    private JPanel mainPanel;
    private LeftPanel leftPanel;
    private Workspace workspace;
    private QuestionXmlParser parser;

    private JPanel workspaceContainer = new JPanel();

    public MainFrame() {
        mainPanel = new JPanel(new BorderLayout());
        leftPanel = new LeftPanel();
        leftPanel.setPreferredSize(new Dimension(300, 0));
        parser = new QuestionXmlParser();
        workspace = new EmptyWorkspace();

        mainPanel.add(leftPanel, BorderLayout.WEST);

        mainPanel.add(workspaceContainer, BorderLayout.CENTER);
        workspaceContainer.setLayout(new BorderLayout());
        this.setTitle("lms-editor");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setVisible(true);
    }

    public void setWorkspace(Workspace workspace) {

        this.workspace = workspace;
        workspaceContainer.removeAll();
        workspaceContainer.add(workspace, BorderLayout.CENTER);
        mainPanel.updateUI();

    }

    public void clearWorkspace() {
        setWorkspace(new EmptyWorkspace());
    }

    public Workspace getWorkspace() {
        return workspace;
    }

}
