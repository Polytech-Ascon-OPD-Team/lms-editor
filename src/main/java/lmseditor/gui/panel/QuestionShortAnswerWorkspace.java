package lmseditor.gui.panel;

import lmseditor.gui.component.QuestionName;
import lmseditor.gui.component.TextWithImage;
import lmseditor.gui.component.answer.ShortAnswers;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionShortAnswerWorkspace extends JPanel {

    private QuestionName name;
    private TextWithImage questionText;
    private QuestionShortAnswerProperties properties;
    private ShortAnswers answers;

    public QuestionShortAnswerWorkspace() {
        this.setLayout(new BorderLayout());

        name = new QuestionName();
        questionText = new TextWithImage();
        properties = new QuestionShortAnswerProperties();
        answers = new ShortAnswers();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(name);
        centerPanel.add(questionText);
        centerPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        centerPanel.add(answers);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(properties, BorderLayout.EAST);

    }

}
