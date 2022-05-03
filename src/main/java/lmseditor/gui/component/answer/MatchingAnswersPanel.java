package lmseditor.gui.component.answer;

import lmseditor.backend.image.ImageList;
import lmseditor.backend.question.component.Subquestion;
import lmseditor.backend.question.text.TextWithImages;
import lmseditor.backend.question.text.Util;
import lmseditor.gui.component.ImageFlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class MatchingAnswersPanel extends JPanel {

    private class MatchingAnswerPanel extends JPanel {
        private static final int TEXT_FIELD_COLUMNS = 30;

        private JTextField textFieldQuestion;
        private JTextField textFieldAnswer;
        private JButton removeButton;
        private ImageFlow imageFlow;
        private ImageList imageList;

        private Subquestion subquestion;

        public MatchingAnswerPanel(Subquestion subquestion) {
            this.setLayout(new GridBagLayout());
            this.subquestion = subquestion;

            textFieldQuestion = new JTextField();
            textFieldAnswer = new JTextField();
            imageFlow = new ImageFlow(subquestion.getTextWithImages().getImageList(), new Dimension(60, 60));
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textFieldQuestion.setText(subquestion.getTextWithImages().getText().getText());
            textFieldAnswer.setText(subquestion.getAnswerText());

            imageList = subquestion.getTextWithImages().getImageList();

            JPanel textFieldsGrid = new JPanel(new GridLayout(1, 2));
            textFieldsGrid.add(textFieldQuestion);
            textFieldsGrid.add(textFieldAnswer);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridy = 0; gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            this.add(textFieldsGrid, gbc);

            gbc.gridy = 0; gbc.gridx = 1;
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
                answers.remove(MatchingAnswersPanel.MatchingAnswerPanel.this);
                MatchingAnswersPanel.this.updateUI();
            }
        }

        public TextWithImages getQuestionText(){
            TextWithImages textWithImages = subquestion.getTextWithImages();
            String formattedText = Util.formatAnswerText(textFieldQuestion.getText());
            textWithImages.getText().setText(formattedText);
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
    }

    public void loadData() {
        answersList.clear();
        for(int i = 0; i < answers.getComponentCount(); i++) {
            MatchingAnswerPanel matchingAnswerPanel = (MatchingAnswerPanel) answers.getComponent(i);
            Subquestion subquestion = new Subquestion();
            subquestion.setTextWithImages(matchingAnswerPanel.getQuestionText());
            subquestion.setAnswerText(matchingAnswerPanel.getAnswerText());
            subquestion.getTextWithImages().generateFormattedText();
            answersList.add(subquestion);
        }
    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
