package lmseditor.gui.component.answer;

import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.backend.question.component.answer.ShortAnswer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShortAnswersPanel extends JPanel {

    private class ShortAnswerPanel extends JPanel {
        private static final int TEXT_FIELD_COLUMNS = 60;

        private JTextField textField;
        private JButton removeButton;

        public ShortAnswerPanel(String text) {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));

            textField = new JTextField(TEXT_FIELD_COLUMNS);
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textField.setText(text);


            this.add(textField);
            this.add(removeButton);
        }

        private class RemoveButtonEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                answers.remove(ShortAnswerPanel.this);
                answersPanel.remove(ShortAnswerPanel.this);
                ShortAnswersPanel.this.updateUI();
            }
        }

        public String getAnswerText(){
            return textField.getText();
        }

    }

    private JLabel label;
    private JButton addButton;
    private List<ShortAnswerPanel> answers;
    private JPanel answersPanel;
    private JScrollPane answersScrollPane;

    List<ShortAnswer> answersList;

    public ShortAnswersPanel(List<ShortAnswer> answersList) {
        this.answersList = answersList;
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

        for (int i = 0; i < answersList.size(); i++){
            ShortAnswer shortAnswer = answersList.get(i);
            ShortAnswerPanel shortAnswerPanel = new ShortAnswerPanel(shortAnswer.getText());
            answers.add(shortAnswerPanel);
            answersPanel.add(shortAnswerPanel);
            ShortAnswersPanel.this.updateUI();
            if (answers.size() > 10) {
                answersScrollPane.setPreferredSize(answersScrollPane.getSize());
            }
        }

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

    }

    public void loadData() {
        answersList.clear();
        for(ShortAnswerPanel shortAnswerPanel : answers) {
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
            answersPanel.add(shortAnswerPanel);
            ShortAnswersPanel.this.updateUI();
            if (answers.size() > 10) {
                answersScrollPane.setPreferredSize(answersScrollPane.getSize());
            }
        }
    }

}
