package lmseditor.gui.component;


import javax.swing.*;
import java.awt.*;


public class TextWithImage extends JPanel {
    private static final int TEXT_AREA_ROWS = 5;
    private static final int TEXT_AREA_COLUMNS = 60;

    private JScrollPane textScrollPane;
    private ImageFlow imageFlow;

    private JLabel label;
    private JTextArea textArea;


    public TextWithImage(String labelText) {
        this.setLayout(new BorderLayout());
        label = new JLabel(labelText);

        textArea = new JTextArea("", TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        textScrollPane = new JScrollPane(textArea);

        imageFlow = new ImageFlow();

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        JPanel scrollPanePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scrollPanePanel.add(textScrollPane);

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(scrollPanePanel, BorderLayout.CENTER);
        this.add(imageFlow, BorderLayout.SOUTH);

    }

}
