package lmseditor.gui.component;

import lmseditor.backend.question.text.Text;

import javax.swing.*;
import java.awt.*;

public class QuestionTextPanel extends JPanel {
    private static final String QUESTION_TEXT_LABEL_TEXT = "Enter question text:";
    private static final int TEXT_AREA_ROWS = 5;
    private static final int TEXT_AREA_COLUMNS = 60;

    private JScrollPane textScrollPane;
    private JLabel label;
    private JTextArea textArea;

    private Text text;

    public QuestionTextPanel(Text text) {
        this.setLayout(new BorderLayout());
        this.text = text;
        label = new JLabel(QUESTION_TEXT_LABEL_TEXT);

        textArea = new JTextArea(text.getText(), TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        textScrollPane = new JScrollPane(textArea);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        JPanel textScrollPanePanel = new JPanel(new BorderLayout());
        textScrollPanePanel.add(textScrollPane, BorderLayout.CENTER);

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(textScrollPanePanel, BorderLayout.CENTER);
    }

    public void loadData() {
        text.setText(textArea.getText());
    }
}
