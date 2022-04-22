package lmseditor.gui.panel;

import lmseditor.backend.image.ImageBase64;
import lmseditor.backend.image.ImageList;
import lmseditor.backend.question.QuestionNumerical;
import lmseditor.backend.question.QuestionShortAnswer;

import javax.swing.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        QuestionShortAnswer questionShortAnswer = new QuestionShortAnswer();
        QuestionShortAnswerWorkspace shortAnswerWorkspace = new QuestionShortAnswerWorkspace(questionShortAnswer);
        this.add(shortAnswerWorkspace);

//        QuestionNumerical questionNumerical = new QuestionNumerical();
//        QuestionNumericalAnswerWorkspace numericalAnswerWorkspace = new QuestionNumericalAnswerWorkspace(questionNumerical);
//        this.add(numericalAnswerWorkspace);

    }

}
