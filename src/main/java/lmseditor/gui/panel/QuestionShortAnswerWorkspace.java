package lmseditor.gui.panel;

import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.backend.question.text.QuestionText;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionShortAnswerWorkspace extends JPanel {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;
    private ShortAnswersPanel answers;

    private QuestionShortAnswerProperties properties;

    private QuestionShortAnswer question;

    public QuestionShortAnswerWorkspace(QuestionShortAnswer question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        name = new QuestionNamePanel();
        questionText = new QuestionTextPanel(question.getTextWithImages().getQuestionText());
        properties = new QuestionShortAnswerProperties();
        answers = new ShortAnswersPanel(question.getAnswers());
        imageFlow = new ImageFlow(question.getTextWithImages().getImageList());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(name);
        centerPanel.add(questionText);
        centerPanel.add(imageFlow);
        centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        centerPanel.add(answers);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(properties, BorderLayout.EAST);

    }

}
