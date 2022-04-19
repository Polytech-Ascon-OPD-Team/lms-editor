package lmseditor.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ImageFlow extends JPanel {
    private List<JLabel> images;

    private JPanel imagePanel;
    private JButton addImageButton;
    private JButton removeImageButton;

    public ImageFlow() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        images = new ArrayList<>();
        addImageButton = new JButton("Add image");
        addImageButton.addActionListener(new AddImageEvent());
        removeImageButton = new JButton("Remove image");
        removeImageButton.addActionListener(new RemoveImageEvent());
        imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(addImageButton);
        buttonsPanel.add(removeImageButton);

        this.add(buttonsPanel);
        this.add(imagePanel);

    }

    public void push(Icon image) {
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(image);
        images.add(imageLabel);
        imagePanel.add(imageLabel);
        this.updateUI();
    }

    public void pop() {
        if (images.size() == 0) {
            return;
        }
        JLabel removeLabel = images.get(images.size() - 1);
        images.remove(removeLabel);
        imagePanel.remove(removeLabel);
        this.updateUI();
    }

    private class AddImageEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class RemoveImageEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ImageFlow.this.pop();
        }
    }

}
