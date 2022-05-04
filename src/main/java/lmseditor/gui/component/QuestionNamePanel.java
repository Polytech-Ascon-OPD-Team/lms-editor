package lmseditor.gui.component;

import lmseditor.backend.question.component.QuestionName;
import lmseditor.backend.question.text.Util;

import javax.swing.*;
import java.awt.*;

public class QuestionNamePanel extends JPanel {
    private static final String NAME_LABEL = "Название: ";
    private static final int TEXT_FIELD_COLUMNS = 20;

    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextField nameTextField;

    private QuestionName name;

    public QuestionNamePanel(QuestionName name) {
        this.name = name;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel = new JLabel(NAME_LABEL);
        idLabel = new JLabel(name.getId());
        nameTextField = new JTextField(name.getName(), TEXT_FIELD_COLUMNS);

        this.add(nameLabel);
        this.add(idLabel);
        this.add(nameTextField);

    }

    public void loadData() {
        String formattedText = Util.formatAnswerText(nameTextField.getText());
        nameTextField.setText(formattedText);
        name.setName(formattedText);
    }

}
