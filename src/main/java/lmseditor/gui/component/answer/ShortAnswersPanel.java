package lmseditor.gui.component.answer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShortAnswersPanel extends JPanel {

    private class ShortAnswer extends JPanel {
        private static final int TEXT_FIELD_COLUMNS = 60;

        private JTextField textField;
        private JButton removeButton;

        public ShortAnswer() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));

            textField = new JTextField(TEXT_FIELD_COLUMNS);
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());

            this.add(textField);
            this.add(removeButton);
        }

        private class RemoveButtonEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                answers.remove(ShortAnswer.this);
                answersPanel.remove(ShortAnswer.this);
                ShortAnswersPanel.this.updateUI();
            }
        }

    }

    private JLabel label;
    private JButton addButton;
    private List<ShortAnswer> answers;
    private JPanel answersPanel;
    private JScrollPane answersScrollPane;

    public ShortAnswersPanel() {
        this.setLayout(new BorderLayout());
        answers = new ArrayList<>();

        label = new JLabel("Enter correct answers");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonEvent());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addButton);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(labelPanel);
        header.add(buttonsPanel);

        answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));

        answersScrollPane = new JScrollPane(answersPanel);
        JPanel answersScrollPanePanel = new JPanel(new BorderLayout());
        answersScrollPanePanel.add(answersScrollPane, BorderLayout.CENTER);

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShortAnswer shortAnswer = new ShortAnswer();
            answers.add(shortAnswer);
            answersPanel.add(shortAnswer);
            ShortAnswersPanel.this.updateUI();
            if (answers.size() > 10) {
                answersScrollPane.setPreferredSize(answersScrollPane.getSize());
            }
        }
    }

}
