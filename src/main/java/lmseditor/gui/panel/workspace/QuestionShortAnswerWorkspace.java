package lmseditor.gui.panel.workspace;

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

public class QuestionShortAnswerWorkspace extends Workspace {

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

        Box box = Box.createVerticalBox();
        box.add(name);
        box.add(questionText);
        box.add(imageFlow);
        box.add(new JSeparator(SwingConstants.HORIZONTAL));
        box.add(answers);

        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(box);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void loadData() {
        questionText.loadData();
        question.getTextWithImages().generateFormattedText();
        answers.loadData();
    }

}
