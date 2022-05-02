package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionNumerical;
import lmseditor.backend.question.text.Text;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.NumericalAnswersPanel;
import lmseditor.gui.panel.properties.QuestionNumericalAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionNumericalAnswerWorkspace extends Workspace {

    private Header header;
    private NumericalAnswersPanel answers;

    private QuestionNumerical question;

    public QuestionNumericalAnswerWorkspace(QuestionNumerical question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        header = new Header(question);
        answers = new NumericalAnswersPanel(question.getAnswers());

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
