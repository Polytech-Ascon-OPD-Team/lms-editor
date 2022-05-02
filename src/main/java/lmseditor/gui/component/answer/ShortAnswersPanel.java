package lmseditor.gui.component.answer;

import lmseditor.backend.question.component.answer.ShortAnswer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShortAnswersPanel extends JPanel {

    private class ShortAnswerPanel extends JPanel {
        private static final int TEXT_FIELD_COLUMNS = 60;

        private JTextField textField;
        private JButton removeButton;

        public ShortAnswerPanel(String text) {
            this.setLayout(new GridBagLayout());

            textField = new JTextField();
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textField.setText(text);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridy = 0; gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.add(textField, gbc);

            gbc.gridy = 0; gbc.gridx = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(removeButton);
        }

        private class RemoveButtonEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                answers.remove(ShortAnswerPanel.this);
                ShortAnswersPanel.this.updateUI();
            }
        }

        public String getAnswerText(){
            return textField.getText();
        }

    }

    private JLabel label;
    private JButton addButton;
    private Box answers;
    private JScrollPane answersScrollPane;

    private List<ShortAnswer> answersList;

    public ShortAnswersPanel(List<ShortAnswer> answersList) {
        this.answersList = answersList;
        this.setLayout(new BorderLayout());

        label = new JLabel("Правильные ответы:");

        addButton = new JButton("+");
        addButton.addActionListener(new AddButtonEvent());

        answers = Box.createVerticalBox();

        JPanel northAlignPanel = new JPanel(new BorderLayout());
        northAlignPanel.add(answers, BorderLayout.NORTH);
        answersScrollPane = new JScrollPane(northAlignPanel);

        for (int i = 0; i < answersList.size(); i++){
            ShortAnswer shortAnswer = answersList.get(i);
            ShortAnswerPanel shortAnswerPanel = new ShortAnswerPanel(shortAnswer.getText());
            answers.add(shortAnswerPanel);
            ShortAnswersPanel.this.updateUI();
        }

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.add(label);
        header.add(addButton);

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPane, BorderLayout.CENTER);

    }

    public void loadData() {
        answersList.clear();
        for(int i = 0; i < answers.getComponentCount(); i++) {
            ShortAnswerPanel shortAnswerPanel = (ShortAnswerPanel) answers.getComponent(i);
            ShortAnswer shortAnswer = new ShortAnswer();
            shortAnswer.setText(shortAnswerPanel.getAnswerText());
            answersList.add(shortAnswer);
        }
    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShortAnswerPanel shortAnswerPanel = new ShortAnswerPanel("");
            answers.add(shortAnswerPanel);
            ShortAnswersPanel.this.updateUI();
        }
    }

}
