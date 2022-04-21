package lmseditor.gui.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFlow extends JPanel {
    private static final Dimension IMAGE_BUTTON_SIZE = new Dimension(120, 120);
    private static final int SCROLLBAR_WIDTH = 30;

    private List<JButton> images;

    private JPanel imagePanel;
    private JButton addImageButton;
    private JScrollPane scrollPane;

    public ImageFlow() {

        this.setLayout(new BorderLayout());

        images = new ArrayList<>();
        addImageButton = new JButton("+");
        addImageButton.addActionListener(new AddImageEvent());
        addImageButton.setPreferredSize(IMAGE_BUTTON_SIZE);

        imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        imagePanel.add(addImageButton);

        scrollPane = new JScrollPane(imagePanel);
        scrollPane.setPreferredSize(new Dimension(0, IMAGE_BUTTON_SIZE.height + SCROLLBAR_WIDTH));

        JPanel scrollPanePanel = new JPanel(new BorderLayout());
        scrollPanePanel.add(scrollPane, BorderLayout.CENTER);

        this.add(scrollPanePanel, BorderLayout.CENTER);

    }

    public void addImage(Icon image) {
        JButton imageButton = new JButton();
        imageButton.setIcon(image);
        imageButton.setPreferredSize(IMAGE_BUTTON_SIZE);
        imageButton.addActionListener(new RemoveImageEvent(imageButton));
        images.add(imageButton);
        imagePanel.add(imageButton);
        this.updateUI();
    }

    private class AddImageEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose image");
            fileChooser.setPreferredSize(new Dimension(1000, 600));
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("png, jpg", "png", "jpg");
            fileChooser.addChoosableFileFilter(filter);
            int result = fileChooser.showOpenDialog(ImageFlow.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage image = ImageIO.read(new File(fileChooser.getSelectedFile().getPath()));
                    Image scaleImage = this.resizeImage(image, IMAGE_BUTTON_SIZE);
                    ImageIcon icon = new ImageIcon(scaleImage);
                    ImageFlow.this.addImage(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private Image resizeImage(BufferedImage image, Dimension requiredSize) {
            Image scaleImage = null;
            if (image.getWidth() > getHeight()) {
                double scaleRatio = ((double) requiredSize.width) / image.getWidth();
                int width = requiredSize.width;
                int height = (int) (scaleRatio * image.getHeight());
                scaleImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            } else {
                double scaleRatio = ((double) requiredSize.height) / image.getHeight();
                int height = requiredSize.height;
                int width = (int) (scaleRatio * image.getWidth());
                scaleImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            }
            return scaleImage;
        }
    }

    private class RemoveImageEvent implements ActionListener {
        private JButton imageButton;

        public RemoveImageEvent(JButton imageButton) {
            this.imageButton = imageButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ImageFlow.this.images.remove(imageButton);
            ImageFlow.this.imagePanel.remove(imageButton);
            ImageFlow.this.updateUI();
        }
    }

}
