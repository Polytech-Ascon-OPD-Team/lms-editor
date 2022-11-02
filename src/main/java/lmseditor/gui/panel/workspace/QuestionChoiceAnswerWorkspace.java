package lmseditor.gui.panel.workspace;

import lmseditor.backend.question.QuestionChoice;
import lmseditor.backend.question.QuestionMatching;
import lmseditor.backend.question.QuestionShortAnswer;
import lmseditor.gui.component.Header;
import lmseditor.gui.component.ImageFlow;
import lmseditor.gui.component.QuestionNamePanel;
import lmseditor.gui.component.QuestionTextPanel;
import lmseditor.gui.component.answer.ChoiceAnswersPanel;
import lmseditor.gui.component.answer.MatchingAnswersPanel;
import lmseditor.gui.component.answer.ShortAnswersPanel;
import javax.swing.*;
import java.awt.*;

public class QuestionChoiceAnswerWorkspace extends Workspace {

    private Header header;
    private ChoiceAnswersPanel answers;

    private QuestionChoice question;

    public QuestionChoiceAnswerWorkspace(QuestionChoice question) {
        this.setLayout(new BorderLayout());

        this.question = question;

        header = new Header(question.getQuestionHeader());
        answers = new ChoiceAnswersPanel(question.getAnswers());

        this.add(header, BorderLayout.NORTH);
        this.add(answers, BorderLayout.CENTER);
    }

    @Override
    public void loadData() {
        header.loadData();
        // question.getQuestionHeader().getTextWithImages().generateFormattedText();
        question.setSingleChoice(answers.getCorrectAnswersCount() == 1);
        answers.loadData();
    }

}
