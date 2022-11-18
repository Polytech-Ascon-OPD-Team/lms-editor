package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionMatching;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.answer.MatchingAnswersPanel;

import java.awt.*;

public class QuestionMatchingWorkspace extends Workspace {

    private Header header;
    private MatchingAnswersPanel answers;

    private QuestionMatching question;

    public QuestionMatchingWorkspace(QuestionMatching question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        header = new Header(question.getQuestionHeader());
        answers = new MatchingAnswersPanel(question.getSubquestions());

        this.add(header, BorderLayout.NORTH);
        this.add(answers, BorderLayout.CENTER);
    }

    @Override
    public void loadData() {
        header.loadData();
        // question.getQuestionHeader().getTextWithImages().generateFormattedText();
        answers.loadData();
    }

}
