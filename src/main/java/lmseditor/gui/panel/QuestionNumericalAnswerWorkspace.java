package lmseditor.gui.panel;

import lmseditor.backend.question.QuestionNumerical;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.NumericalAnswersPanel;
import lmseditor.gui.panel.properties.QuestionNumericalAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionNumericalAnswerWorkspace extends JPanel {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;
    private NumericalAnswersPanel answers;

    private QuestionNumericalAnswerProperties properties;

    private QuestionNumerical question;

    public QuestionNumericalAnswerWorkspace(QuestionNumerical question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        name = new QuestionNamePanel();
        questionText = new QuestionTextPanel();
        properties = new QuestionNumericalAnswerProperties();
        answers = new NumericalAnswersPanel();
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
