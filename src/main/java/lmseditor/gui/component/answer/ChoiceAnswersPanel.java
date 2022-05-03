package lmseditor.gui.component.answer;

import lmseditor.backend.image.ImageList;
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
        private static final int TEXT_FIELD_COLUMNS = 30;

        private JTextField textField;
        private JButton removeButton;
        private ImageFlow imageFlow;
        private ImageList imageList;

        public ChoiceAnswer choiceAns;

        private JCheckBox checkbox = new JCheckBox();

        public ChoiceAnswerPanel(ChoiceAnswer choiceAnswer) {
            this.setLayout(new GridBagLayout());

            textField = new JTextField();
            imageFlow = new ImageFlow(choiceAnswer.getTextWithImages().getImageList(), new Dimension(60, 60));
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textField.setText(choiceAnswer.getTextWithImages().getText().getText());
            choiceAns = choiceAnswer;

            if (choiceAnswer.getFraction() > 0) {
                checkbox.setSelected(true);
            }

            imageList = choiceAnswer.getTextWithImages().getImageList();

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

        private class RemoveButtonEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answers.getComponentCount() > 4) {
                    answers.remove(ChoiceAnswersPanel.ChoiceAnswerPanel.this);
                    ChoiceAnswersPanel.this.updateUI();
                }
            }
        }

        private ChoiceAnswer getChoiceAns() {
            if (checkbox.isSelected()) {
                choiceAns.setFraction(100);
            }
            choiceAns.getTextWithImages().getText().setText(getAnswerText());
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

    List<ChoiceAnswer> answersList;

    public ChoiceAnswersPanel(List<ChoiceAnswer> answersList) {
        this.answersList = answersList;
        this.setLayout(new BorderLayout());

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

        for (int i = 0; i < answersList.size(); i++){
            ChoiceAnswer choiceAnswer = answersList.get(i);
            ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
            answers.add(choiceAnswerPanel);
            ChoiceAnswersPanel.this.updateUI();
        }

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

        if (answersList.size() == 0) {
            for (int i = 0; i < 4; i++) {
                ChoiceAnswer choiceAnswer = new ChoiceAnswer();
                TextWithImages textWithImages = new TextWithImages();
                choiceAnswer.setTextWithImages(textWithImages);
                ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
                answers.add(choiceAnswerPanel);
            }
            ChoiceAnswersPanel.this.updateUI();
        }
    }

    public int getCountOfRightAnswers() {
        int count = 0;
        for(int i = 0; i < answers.getComponentCount(); i++) {
            ChoiceAnswerPanel choiceAnswerPanel = (ChoiceAnswerPanel) answers.getComponent(i);
            ChoiceAnswer choiceAnswer = choiceAnswerPanel.getChoiceAns();
            if (choiceAnswer.getFraction() > 0) {
                count++;
            }
        }
        return count;
    }

    public void loadData() {
        answersList.clear();
        int countOfAnswers = getCountOfRightAnswers();
        double points = (double) 100 / countOfAnswers;
        for(int i = 0; i < answers.getComponentCount(); i++) {
            ChoiceAnswerPanel choiceAnswerPanel = (ChoiceAnswerPanel) answers.getComponent(i);
            ChoiceAnswer choiceAnswer = choiceAnswerPanel.getChoiceAns();
            if (choiceAnswer.getFraction() > 0) {
                choiceAnswer.setFraction(points);
            } else {
                choiceAnswer.setFraction(-points);
            }
            choiceAnswer.getTextWithImages().generateFormattedText();
            answersList.add(choiceAnswer);
        }
    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (answers.getComponentCount() < 6) {
                ChoiceAnswer choiceAnswer = new ChoiceAnswer();
                TextWithImages textWithImages = new TextWithImages();
                choiceAnswer.setTextWithImages(textWithImages);
                ChoiceAnswerPanel choiceAnswerPanel = new ChoiceAnswerPanel(choiceAnswer);
                answers.add(choiceAnswerPanel);
                ChoiceAnswersPanel.this.updateUI();
            }
        }
    }
}
