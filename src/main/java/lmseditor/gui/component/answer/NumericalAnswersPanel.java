package lmseditor.gui.component.answer;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NumericalAnswersPanel extends JPanel {

    private class NumericalAnswer extends JPanel {
        private static final int TEXT_FIELD_COLUMNS = 60;

        private JTextField textField;
        private JButton removeButton;

        public NumericalAnswer() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));

            textField = new JTextField(TEXT_FIELD_COLUMNS);
            removeButton = new JButton("-");
            removeButton.addActionListener(new RemoveButtonEvent());

            this.add(textField);
            this.add(removeButton);

            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new MyDocumentFilter());
        }

        private class RemoveButtonEvent implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                answers.remove(NumericalAnswer.this);
                answersPanel.remove(NumericalAnswer.this);
                NumericalAnswersPanel.this.updateUI();
            }
        }

    }

    private JLabel label;
    private JButton addButton;
    private List<NumericalAnswer> answers;
    private JPanel answersPanel;
    private JScrollPane answersScrollPane;

    public NumericalAnswersPanel() {
        this.setLayout(new BorderLayout());
        answers = new ArrayList<>();

        label = new JLabel("Enter correct answers");
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(label);

        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonEvent());
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addButton);

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(labelPanel);
        header.add(buttonsPanel);

        answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));

        answersScrollPane = new JScrollPane(answersPanel);
        JPanel answersScrollPanePanel = new JPanel(new BorderLayout());
        answersScrollPanePanel.add(answersScrollPane, BorderLayout.CENTER);

        this.add(header, BorderLayout.NORTH);
        this.add(answersScrollPanePanel, BorderLayout.CENTER);

    }

    private class AddButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NumericalAnswer numericalAnswer = new NumericalAnswer();
            answers.add(numericalAnswer);
            answersPanel.add(numericalAnswer);
            NumericalAnswersPanel.this.updateUI();
            if (answers.size() > 10) {
                answersScrollPane.setPreferredSize(answersScrollPane.getSize());
            }
        }
    }

    class MyDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            string = string.replaceAll("[^\\d\\.]", "");
            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            text = text.replaceAll("[^\\d\\.]", "");
            super.replace(fb, offset, length, text, attrs);
        }
    }

}
