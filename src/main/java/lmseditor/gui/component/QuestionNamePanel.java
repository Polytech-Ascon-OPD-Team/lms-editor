package lmseditor.gui.component;

import lmseditor.backend.question.component.QuestionName;

import javax.swing.*;
import java.awt.*;

public class QuestionNamePanel extends JPanel {
    private static final String NAME_LABEL = "Название вопроса: ";

    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextField nameTextField;

    private QuestionName name;

    public QuestionNamePanel(QuestionName name) {
        this.name = name;

        this.setLayout(new GridBagLayout());
        nameLabel = new JLabel(NAME_LABEL);
        idLabel = new JLabel(name.getId());
        nameTextField = new JTextField(name.getName());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0; gbc.gridx = 0;
        this.add(nameLabel, gbc);

        gbc.gridy = 0; gbc.gridx = 1;
        this.add(idLabel, gbc);

        gbc.gridy = 0; gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        this.add(nameTextField, gbc);

    }

    public void loadData() {
        name.setName(nameTextField.getText());
    }

}
