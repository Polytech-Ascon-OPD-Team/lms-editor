package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.answer.ShortAnswersPanel;

import java.awt.*;

public class QuestionShortAnswerWorkspace extends Workspace {

    private Header header;
    private ShortAnswersPanel answers;

    private QuestionShortAnswer question;

    public QuestionShortAnswerWorkspace(QuestionShortAnswer question) {
        this.setLayout(new BorderLayout());
        this.question = question;

        answers = new ShortAnswersPanel(question.getAnswers());
        header = new Header(question.getQuestionHeader());

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
