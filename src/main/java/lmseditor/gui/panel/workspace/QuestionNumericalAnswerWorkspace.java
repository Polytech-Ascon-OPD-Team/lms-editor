package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionNumerical;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.answer.NumericalAnswersPanel;

import java.awt.*;

public class QuestionNumericalAnswerWorkspace extends Workspace {

    private Header header;
    private NumericalAnswersPanel answers;

    private QuestionNumerical question;

    public QuestionNumericalAnswerWorkspace(QuestionNumerical question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        header = new Header(question.getQuestionHeader());
        answers = new NumericalAnswersPanel(question.getAnswers());

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
