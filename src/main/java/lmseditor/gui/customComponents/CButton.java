package lmseditor.gui.customComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public class CButton extends CLabel {
    Color activeColor = new Color(94, 90, 90);
    boolean inActive = false;
    protected Runnable act;

    public void performAsPressedAction() {
        act.run();
    }

    public CButton(String text) {
        super(text);
        init();
    }

    public CButton(String text, Supplier<Rectangle> boundsSetter) {
        super(text, boundsSetter);
        init();
    }

    private void init() {
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!inActive) {
                    inActive = true;
                    setBackground(activeColor);
                    repaint();
                }
                super.mouseMoved(e);
            }

        });
        color = new Color(75, 75, 75);
        activeColor = new Color(94, 90, 90);
        this.setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    act.run();
                }
                super.mouseClicked(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                inActive = false;
                setBackground(color);
                repaint();
            }
        });
    }

    public CButton(String text, Supplier<Rectangle> boundsSetter, Color background, Color active) {
        this(text, boundsSetter);
        color = background;
        this.setBackground(color);
        activeColor = active;
    }

    public void setAction(Runnable action) {
        act = action;
    }
}