package lmseditor.gui.customComponents;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class CLabel extends CPanel {
    public JLabel label;
    protected Color color = new Color(97, 95, 95);

    public void setKoef(double koef) {
        this.koef = koef;
    }

    private double koef = 1;

    public CLabel(String text) {
        super();
        this.setBackground(color);
        label = new JLabel(text, JLabel.CENTER);
        label.setForeground(new Color(238, 230, 230));
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
    }

    public CLabel(String text, Supplier<Rectangle> boundsSetter) {
        this(text);
        super.boundsSetter = boundsSetter;
    }

    public CLabel(String text, Supplier<Rectangle> boundsSetter, Color background) {
        this(text, boundsSetter);
        color = background;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void advancedLayout() {
        Rectangle rect = boundsSetter.get();
        this.setBounds(rect);
        label.setFont(new Font("", Font.BOLD, Math.min(this.getHeight(), ((int)((double) this.getWidth() * koef)) / label.getText().length())));

    }
}