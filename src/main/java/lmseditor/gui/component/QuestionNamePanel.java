package lmseditor.gui.component;

import javax.swing.*;
import java.awt.*;

public class QuestionNamePanel extends JPanel {
    private static final String NAME_LABEL = "Enter question name:";
    private static final int NAME_TEXT_FIELD_COLUMNS = 60;

    private JLabel nameLabel;
    private JTextField nameTextField;

    public QuestionNamePanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel = new JLabel(NAME_LABEL);
        nameTextField = new JTextField(NAME_TEXT_FIELD_COLUMNS);

        this.add(nameLabel);
        this.add(nameTextField);

    }

}
