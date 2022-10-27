package lmseditor.gui.component;

import lmseditor.backend.question.Question;
import lmseditor.backend.question.component.QuestionHeader;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {

    private QuestionNamePanel name;
    private QuestionTextPanel questionText;
    private ImageFlow imageFlow;

    public Header(QuestionHeader questionHeader) {
        this.setLayout(new BorderLayout());

        name = new QuestionNamePanel(questionHeader.getName());
        questionText = new QuestionTextPanel(questionHeader.getTextWithImages().getTextStringBuilder());
        imageFlow = new ImageFlow(questionHeader.getTextWithImages().getImageList());

        this.add(name, BorderLayout.NORTH);
        this.add(questionText, BorderLayout.CENTER);
        this.add(imageFlow, BorderLayout.SOUTH);
    }

    public void loadData() {
        name.loadData();
        questionText.loadData();
    }

}
