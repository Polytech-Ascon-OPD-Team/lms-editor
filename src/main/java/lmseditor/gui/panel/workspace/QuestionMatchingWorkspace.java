package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionMatching;
import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.MatchingAnswersPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionMatchingWorkspace extends Workspace {

    private Header header;
    private MatchingAnswersPanel answers;

    private QuestionMatching question;

    public QuestionMatchingWorkspace(QuestionMatching question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        header = new Header(question);
        answers = new MatchingAnswersPanel(question.getSubquestions());

        this.add(header, BorderLayout.NORTH);
        this.add(answers, BorderLayout.CENTER);
    }

    @Override
    public void loadData() {
        header.loadData();
        question.getQuestionText().generateFormattedText();
        answers.loadData();
    }

}
