package lmseditor.gui.component.answer;

import lmseditor.backend.question.component.answer.ChoiceAnswer;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.Util;
import lmseditor.gui.component.ImageFlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChoiceAnswersPanel extends JPanel {

    private class ChoiceAnswerPanel extends JPanel {

        private JTextField textField;
        private JButton removeButton;
        private ImageFlow imageFlow;

        private ChoiceAnswer choiceAns;

        private JCheckBox checkbox;

        public ChoiceAnswerPanel(ChoiceAnswer choiceAnswer) {
            this.setLayout(new GridBagLayout());

            textField = new JTextField();
            imageFlow = new ImageFlow(choiceAnswer.getTextWithImages().getImageList(), new Dimension(60, 60));
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent(this));
            textField.setText(choiceAnswer.getTextWithImages().getText());
            choiceAns = choiceAnswer;
            checkbox = new JCheckBox();

            if (choiceAnswer.getFraction() > 0) {
                checkbox.setSelected(true);
            }

            checkbox.addActionListener(new CheckBoxEvent(this));

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridy = 0; gbc.gridx = 0;
            gbc.fill = GridBagConstraints.NONE;
            this.add(checkbox, gbc);

            gbc.gridy = 0; gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.add(textField, gbc);

            gbc.gridy = 0; gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(removeButton, gbc);

            gbc.gridy = 1; gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            gbc.gridwidth = 2;
            this.add(imageFlow, gbc);

        }

        private boolean isSelected() {
            return checkbox.isSelected();
        }

        private class CheckBoxEvent implements ActionListener {

            private ChoiceAnswerPanel choiceAnswerPanel;

            public CheckBoxEvent(ChoiceAnswerPanel choiceAnswerPanel) {
                this.choiceAnswerPanel = choiceAnswerPanel;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkbox.isSelected()) {
                    if (correctAnswersCount + 1 == answersCount) {
                        choiceAnswerPanel.checkbox.setSelected(false);
                    } else {
                        correctAnswersCount++;
                    }
                } else {
                    if (correctAnswersCount > 1) {
                        correctAnswersCount--;
                    } else {
                        choiceAnswerPanel.checkbox.setSelected(true);
                    }
                }
            }
        }

        private class RemoveButtonEvent implements ActionListener {

            private ChoiceAnswerPanel choiceAnswerPanel;

            public RemoveButtonEvent(ChoiceAnswerPanel choiceAnswerPanel) {
                this.choiceAnswerPanel = choiceAnswerPanel;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (answersCount > 4) {
                    boolean isSelected = choiceAnswerPanel.checkbox.isSelected();
                    if ((correctAnswersCount + 1 == answersCount) && !isSelected) {
                        return;
                    }
                    if ((correctAnswersCount == 1) && isSelected) {
                        return;
                    }
                    answers.remove(ChoiceAnswersPanel.ChoiceAnswerPanel.this);
                    ChoiceAnswersPanel.this.updateUI();
                    answersCount--;
                    if (isSelected) {
                        correctAnswersCount--;
                    }
                }
            }
        }

        private ChoiceAnswer getChoiceAns() {
            if (checkbox.isSelected()) {
                choiceAns.setFraction(100);
            }
            choiceAns.getTextWithImages().setText(getAnswerText());
            return choiceAns;
        }

        public String getAnswerText(){
            String formattedText = Util.formatAnswerText(textField.getText());
            textField.setText(formattedText);
            return formattedText;
        }

    }

    private JLabel label;
    private JButton addButton;
    private Box answers;
    private JScrollPane answersScrollPane;

    private List<ChoiceAnswer> answersList;

    private int answersCount;
    private int correctAnswersCount;

    public ChoiceAnswersPanel(List<ChoiceAnswer> answersList) {
        this.answersList = answersList;
        this.setLayout(new BorderLayout());

        answersCount = 0;
        correctAnswersCount = 0;

        label = new JLabel("Ответы:");

        addButton = new JButton("+");
        addButton.addActionListener(new AddButtonEvent());

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.add(label);
        header.add(addButton);

        answers = new Box(BoxLayout.Y_AXIS);
        JPanel northAlignPanel = new JPanel(new BorderLayout());
        northAlignPanel.add(answers, BorderLayout.NORTH);
        answersScrollPane = new JScrollPane(northAlignPanel);

        JPanel answersScrollPanePanel = new JPanel(new BorderLayout());
        answersScrollPanePanel.add(answersScrollPane, BorderLayout.CENTER);

        for (int i = 0; i < answersList.size(); i++) {
            answersCount++;
            ChoiceAnswer choiceAnswer = answersList.get(i);
            if (choiceAnswer.getFraction() > 0) {
                correctAnswersCount++;
            }
            ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
            answers.add(choiceAnswerPanel);
            ChoiceAnswersPanel.this.updateUI();
        }

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

        if (answersList.size() == 0) {
            answersCount = 4; correctAnswersCount = 1;
            for (int i = 0; i < 4; i++) {
                ChoiceAnswer choiceAnswer = new ChoiceAnswer();
                if (i == 0) {
                    choiceAnswer.setFraction(100);
                }
                ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
                answers.add(choiceAnswerPanel);
            }
            ChoiceAnswersPanel.this.updateUI();
        }
    }

    public void loadData() {
        answersList.clear();
        double points = 100.0 / correctAnswersCount;
        for(int i = 0; i < answers.getComponentCount(); i++) {
            ChoiceAnswerPanel choiceAnswerPanel = (ChoiceAnswerPanel) answers.getComponent(i);
            ChoiceAnswer choiceAnswer = choiceAnswerPanel.getChoiceAns();
            if (choiceAnswerPanel.isSelected()) {
                choiceAnswer.setFraction(points);
            } else if (correctAnswersCount != 1) {
                choiceAnswer.setFraction(-points);
            } else {
                choiceAnswer.setFraction(0);
            }
            // choiceAnswer.getTextWithImages().generateFormattedText();
            answersList.add(choiceAnswer);
        }
    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (answersCount < 6) {
                ChoiceAnswer choiceAnswer = new ChoiceAnswer();
                TextWithImages textWithImages = new TextWithImages();
                choiceAnswer.setTextWithImages(textWithImages);
                ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
                answers.add(choiceAnswerPanel);
                ChoiceAnswersPanel.this.updateUI();
                answersCount++;
            }
        }
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

}
