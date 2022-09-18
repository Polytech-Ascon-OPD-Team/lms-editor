package lmseditor.gui.component;

import lmseditor.backend.question.text.Util;

import javax.swing.*;
import java.awt.*;

public class QuestionTextPanel extends JPanel {
    private static final String QUESTION_TEXT_LABEL_TEXT = "Текст:";
    private static final int TEXT_AREA_ROWS = 5;

    private JScrollPane textScrollPane;
    private JLabel label;
    private JTextArea textArea;

    private StringBuilder text;

    public QuestionTextPanel(StringBuilder text) {
        this.setLayout(new GridBagLayout());
        this.text = text;

        label = new JLabel(QUESTION_TEXT_LABEL_TEXT);
        textArea = new JTextArea(text.toString(), TEXT_AREA_ROWS ,0);
        textScrollPane = new JScrollPane(textArea);

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0; gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(labelPanel, gbc);

        gbc.gridy = 1; gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1; gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(textScrollPane, gbc);

    }

    public void loadData() {
        String formattedText = Util.firstSymbolToUpperCase(textArea.getText());
        textArea.setText(formattedText);
        text.replace(0, text.length(), formattedText);

    }

}
