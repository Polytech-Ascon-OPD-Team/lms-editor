package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import lmseditor.gui.panel.properties.QuestionShortAnswerProperties;

import javax.swing.*;
import java.awt.*;

public class QuestionShortAnswerWorkspace extends Workspace {

    private Header header;
    private ShortAnswersPanel answers;

    private QuestionShortAnswer question;

    public QuestionShortAnswerWorkspace(QuestionShortAnswer question) {
        this.setLayout(new BorderLayout());
        this.question = question;

        answers = new ShortAnswersPanel(question.getAnswers());
        header = new Header(question);

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
