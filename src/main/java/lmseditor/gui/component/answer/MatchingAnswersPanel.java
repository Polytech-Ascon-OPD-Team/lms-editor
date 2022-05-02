package lmseditor.gui.component.answer;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.image.ImageList;
import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.component.Subquestion;
//import lmseditor.backend.question.component.answer.MatchingAnswer;
import lmseditor.backend.question.component.answer.ShortAnswer;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.backend.question.text.Text;
import lmseditor.backend.question.text.TextWithImages;
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

        public MatchingAnswerPanel(Subquestion subquestion) {
            this.setLayout(new GridBagLayout());

            textFieldQuestion = new JTextField();
            textFieldAnswer = new JTextField();
            imageFlow = new ImageFlow(subquestion.getTextWithImages().getImageList(), new Dimension(60, 60));
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textFieldQuestion.setText(subquestion.getTextWithImages().getText().getText());
            textFieldAnswer.setText(subquestion.getAnswerText());

            imageList = subquestion.getTextWithImages().getImageList();
            for(ImageBase64 imageBase64 : imageList.getImages()) {
                BufferedImage image = ImageBase64.decodeBase64ToImage(imageBase64.getBase64());
                imageFlow.addImageToMiniatures(image);
            }

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
                answers.remove(MatchingAnswersPanel.MatchingAnswerPanel.this);
                MatchingAnswersPanel.this.updateUI();
            }
        }

        public TextWithImages getQuestionText(){
            TextWithImages textWithImages = new TextWithImages();
            QuestionName name = new QuestionName();
            QuestionText questionText = new QuestionText(name);
            Text text = new Text();
            text.setText(textFieldQuestion.getText());
            questionText.setText(text);
            textWithImages.setText(text);
            textWithImages.setImageList(imageList);
            return textWithImages;
        }

        public String getAnswerText(){
            return textFieldAnswer.getText();
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

        label = new JLabel("Enter correct answers");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonEvent());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addButton);

        Box header = new Box(BoxLayout.Y_AXIS);
        header.add(labelPanel);
        header.add(buttonsPanel);

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
