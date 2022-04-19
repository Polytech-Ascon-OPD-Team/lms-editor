package lmseditor.gui.panel;

import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.TextWithImagesPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionShortAnswerWorkspace extends JPanel {

    private QuestionNamePanel name;
    private TextWithImagesPanel questionText;
    private QuestionShortAnswerProperties properties;
    private ShortAnswersPanel answers;

    public QuestionShortAnswerWorkspace() {
        this.setLayout(new BorderLayout());

        name = new QuestionNamePanel();
        questionText = new TextWithImagesPanel();
        properties = new QuestionShortAnswerProperties();
        answers = new ShortAnswersPanel();

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
