package lmseditor.gui.component.answer;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.question.component.Subquestion;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.Util;
import lmseditor.gui.component.ImageFlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MatchingAnswersPanel extends JPanel {

    private class MatchingAnswerPanel extends JPanel {
        private int TEXT_FIELD_MIN_COLUMNS = 30;

        private JTextField textFieldQuestion;
        private JTextField textFieldAnswer;
        private JButton removeButton;
        private ImageFlow imageFlow;
        private List<ImageBase64> images;

        private Subquestion subquestion;

        public MatchingAnswerPanel(Subquestion subquestion) {
            this.setLayout(new GridBagLayout());
            this.subquestion = subquestion;

            imageFlow = new ImageFlow(subquestion.getTextWithImages().getImageList(), new Dimension(60, 60));
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textFieldQuestion = new JTextField(subquestion.getTextWithImages().getText(), TEXT_FIELD_MIN_COLUMNS);
            textFieldAnswer = new JTextField(subquestion.getAnswerText(), TEXT_FIELD_MIN_COLUMNS);

            images = subquestion.getTextWithImages().getImageList();

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridy = 0; gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.add(textFieldQuestion, gbc);

            gbc.gridy = 0; gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.add(textFieldAnswer, gbc);

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
                    answers.remove(MatchingAnswersPanel.MatchingAnswerPanel.this);
                    MatchingAnswersPanel.this.updateUI();
                }
            }
        }

        public TextWithImages getQuestionText(){
            TextWithImages textWithImages = subquestion.getTextWithImages();
            String formattedText = Util.formatAnswerText(textFieldQuestion.getText());
            textWithImages.setText(formattedText);
            textFieldQuestion.setText(formattedText);
            return textWithImages;
        }

        public String getAnswerText(){
            String formattedText = Util.formatAnswerText(textFieldAnswer.getText());
            textFieldAnswer.setText(formattedText);
            return formattedText;
        }

    }

    private JLabel label;
    private JButton addButton;
    private Box answers;
    private JScrollPane answersScrollPane;

    List<Subquestion> answersList;

    public MatchingAnswersPanel(List<Subquestion> answersList) {
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
            Subquestion subquestion = answersList.get(i);
            MatchingAnswerPanel matchingAnswerPanel = new MatchingAnswerPanel(subquestion);
            answers.add(matchingAnswerPanel);
            MatchingAnswersPanel.this.updateUI();
        }

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

        if (answersList.size() == 0) {
            for (int i = 0; i < 4; i++) {
                Subquestion subquestion = new Subquestion();
                MatchingAnswersPanel.MatchingAnswerPanel matchingAnswerPanel = new MatchingAnswersPanel.MatchingAnswerPanel(subquestion);
                answers.add(matchingAnswerPanel);
            }
            MatchingAnswersPanel.this.updateUI();
        }
    }

    public void loadData() {
        answersList.clear();
        for(int i = 0; i < answers.getComponentCount(); i++) {
            MatchingAnswerPanel matchingAnswerPanel = (MatchingAnswerPanel) answers.getComponent(i);
            Subquestion subquestion = new Subquestion();
            subquestion.setTextWithImages(matchingAnswerPanel.getQuestionText());
            subquestion.setAnswerText(matchingAnswerPanel.getAnswerText());
            // subquestion.getTextWithImages().generateFormattedText();
            answersList.add(subquestion);
        }
    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(answers.getComponentCount() < 6) {
                Subquestion subquestion = new Subquestion();
                TextWithImages textWithImages = new TextWithImages();
                subquestion.setAnswerText("");
                subquestion.setTextWithImages(textWithImages);
                MatchingAnswerPanel matchingAnswerPanel = new MatchingAnswerPanel(subquestion);
                answers.add(matchingAnswerPanel);
                MatchingAnswersPanel.this.updateUI();
            }
        }
    }
}
