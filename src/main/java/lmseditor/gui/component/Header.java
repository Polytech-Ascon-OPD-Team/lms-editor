package lmseditor.gui.component;

import lmseditor.backend.question.Question;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;

    public Header(Question question) {
        this.setLayout(new BorderLayout());

        name = new QuestionNamePanel(question.getName());
        questionText = new QuestionTextPanel(question.getQuestionText().getTextStringBuilder());
        imageFlow = new ImageFlow(question.getQuestionText().getImageList());

        this.add(name, BorderLayout.NORTH);
        this.add(questionText, BorderLayout.CENTER);
        this.add(imageFlow, BorderLayout.SOUTH);
    }

    public void loadData() {
        name.loadData();
        questionText.loadData();
    }

}
