package lmseditor.gui.component;

import lmseditor.Main;
import lmseditor.backend.image.ImageBase64;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFlow extends JPanel {
    private static final int SCROLLBAR_WIDTH = 30;

    private Dimension miniatureSize;

    private List<JButton> imageMiniatures;
    private List<ImageBase64> images;

    private JPanel imagePanel;
    private JButton addImageButton;
    private JScrollPane scrollPane;

    public ImageFlow(List<ImageBase64> imageList, Dimension miniatureSize) {
        this.setLayout(new BorderLayout());

        this.miniatureSize = miniatureSize;
        this.images = imageList;
        imageMiniatures = new ArrayList<>();

        addImageButton = new JButton("+");
        addImageButton.addActionListener(new AddImageEvent());
        addImageButton.setPreferredSize(this.miniatureSize);

        imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        imagePanel.add(addImageButton);

        scrollPane = new JScrollPane(imagePanel);
        scrollPane.setPreferredSize(new Dimension(0, this.miniatureSize.height + SCROLLBAR_WIDTH));

        JPanel scrollPanePanel = new JPanel(new BorderLayout());
        scrollPanePanel.add(scrollPane, BorderLayout.CENTER);

        this.add(scrollPanePanel, BorderLayout.CENTER);

        // Костыль для удаления всех файлов, кроме картинок
        List<ImageBase64> forRemove = new ArrayList<>();

        for(ImageBase64 imageBase64 : imageList) {
            if (!imageBase64.getName().contains(".jpg") && !imageBase64.getName().contains(".JPG") &&
                    !imageBase64.getName().contains(".png") && !imageBase64.getName().contains(".PNG")) {
                forRemove.add(imageBase64);
                continue;
            }
            BufferedImage image = ImageBase64.decodeBase64ToImage(imageBase64.getBase64());
            addImageToMiniatures(image);
        }
        for (ImageBase64 imageBase64 : forRemove) {
            imageList.remove(imageBase64);
        }

    }

    public ImageFlow(List<ImageBase64> images) {
        this(images, new Dimension(120, 120));
    }

    public void addImageToMiniatures(BufferedImage image) {
        Image scaleImage = this.resizeImage(image, miniatureSize);
        ImageIcon icon = new ImageIcon(scaleImage);
        JButton imageButton = new JButton();
        imageButton.setIcon(icon);
        imageButton.setPreferredSize(miniatureSize);
        imageButton.addActionListener(new RemoveImageEvent(imageButton));
        imageMiniatures.add(imageButton);
        imagePanel.add(imageButton);
        this.updateUI();
    }

    private Image resizeImage(BufferedImage image, Dimension requiredSize) {
        Image scaleImage = null;
        if (image.getWidth() > image.getHeight()) {
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

    private class AddImageEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog fileDialog = new FileDialog(Main.mainFrame, "Выберите изображение", FileDialog.LOAD);
            fileDialog.setFile("*.jpg;*.png");
            fileDialog.setVisible(true);
            String fileName = fileDialog.getDirectory() + fileDialog.getFile();
            if (fileDialog.getFile() != null) {
                try {
                    File file = new File(fileName);
                    BufferedImage image = ImageIO.read(file);
                    addImageToMiniatures(image);
                    String base64 = ImageBase64.encodeFileToBase64(file);
                    images.add(new ImageBase64(file.getName(), "/",  base64));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class RemoveImageEvent implements ActionListener {
        private JButton imageButton;

        public RemoveImageEvent(JButton imageButton) {
            this.imageButton = imageButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = imageMiniatures.indexOf(imageButton);
            images.remove(index);
            imageMiniatures.remove(imageButton);
            imagePanel.remove(imageButton);
            ImageFlow.this.updateUI();
        }
    }

}
