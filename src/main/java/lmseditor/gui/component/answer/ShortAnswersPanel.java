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
                ShortAnswersPanel.this.remove(ShortAnswer.this);
                ShortAnswersPanel.this.updateUI();
            }
        }

    }

    private JLabel label;
    private JButton addButton;
    private List<ShortAnswer> answers;

    public ShortAnswersPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        answers = new ArrayList<>();

        label = new JLabel("Enter correct answers");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        addButton = new JButton("Add answer");
        addButton.addActionListener(new AddButtonEvent());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addButton);

        this.add(labelPanel);
        this.add(buttonsPanel);

    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShortAnswer shortAnswer = new ShortAnswer();
            answers.add(shortAnswer);
            ShortAnswersPanel.this.add(shortAnswer);
            ShortAnswersPanel.this.updateUI();
        }
    }

}
