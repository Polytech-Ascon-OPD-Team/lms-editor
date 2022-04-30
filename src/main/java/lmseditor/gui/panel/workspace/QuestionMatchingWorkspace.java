package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionMatching;
import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.MatchingAnswersPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionMatchingWorkspace extends Workspace {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;
    private MatchingAnswersPanel answers;

    //private QuestionShortAnswerProperties properties;

    private QuestionMatching question;

    public QuestionMatchingWorkspace(QuestionMatching question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        name = new QuestionNamePanel(question.getName());
        questionText = new QuestionTextPanel(question.getQuestionText().getText());
        //properties = new QuestionShortAnswerProperties();
        answers = new MatchingAnswersPanel(question.getSubquestions());
        imageFlow = new ImageFlow(question.getQuestionText().getImageList());

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
        question.getQuestionText().generateFormattedText();
        answers.loadData();
    }

}
