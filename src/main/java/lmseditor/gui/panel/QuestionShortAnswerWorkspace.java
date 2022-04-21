package lmseditor.gui.panel;

import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionShortAnswerWorkspace extends JPanel {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;
    private ShortAnswersPanel answers;

    private QuestionShortAnswerProperties properties;

    public QuestionShortAnswerWorkspace() {
        this.setLayout(new BorderLayout());

        name = new QuestionNamePanel();
        questionText = new QuestionTextPanel();
        properties = new QuestionShortAnswerProperties();
        answers = new ShortAnswersPanel();
        imageFlow = new ImageFlow();

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
