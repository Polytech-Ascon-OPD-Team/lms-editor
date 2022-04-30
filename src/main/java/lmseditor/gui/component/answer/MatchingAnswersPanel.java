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
            this.setLayout(new FlowLayout(FlowLayout.LEFT));

            textFieldQuestion = new JTextField(TEXT_FIELD_COLUMNS);
            textFieldAnswer = new JTextField(TEXT_FIELD_COLUMNS);
            imageFlow = new ImageFlow(subquestion.getTextWithImages().getImageList());
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());
            textFieldQuestion.setText(subquestion.getTextWithImages().getText().getText());
            textFieldAnswer.setText(subquestion.getAnswerText());

            imageList = subquestion.getTextWithImages().getImageList();
            for(ImageBase64 imageBase64 : imageList.getImages()) {
                BufferedImage image = ImageBase64.decodeBase64ToImage(imageBase64.getBase64());
                imageFlow.addImageToMiniatures(image);
            }

            Box horizontalbox = Box.createHorizontalBox();
            horizontalbox.add(textFieldQuestion);
            horizontalbox.add(textFieldAnswer);
            horizontalbox.add(removeButton);

            Box box = Box.createVerticalBox();
            box.add(horizontalbox);
            box.add(imageFlow);
            box.add(new JSeparator(SwingConstants.HORIZONTAL));

            this.add(box);
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
        JPanel answersAlign = new JPanel();
        answersAlign.add(answers);
        answersScrollPane = new JScrollPane(answersAlign);
        answersScrollPane.setPreferredSize(new Dimension(0, 500));
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
