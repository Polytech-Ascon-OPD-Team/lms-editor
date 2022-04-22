package lmseditor.gui.customComponents;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class CPanel extends JPanel implements Layoutable {
    public Supplier<Rectangle> boundsSetter;

    CPanel alsoToInit(AdvRun<CPanel> task) {
        task.run(this);
        return this;
    }

    public CPanel() {
        super();
        //this.setBackground(new Color(63, 63, 78));
    }

    public CPanel(Supplier<Rectangle> bounds) {
        super();
        boundsSetter = bounds;
        this.setBackground(new Color(63, 63, 78));
    }

    @Override
    public void advancedLayout() {
        this.setBounds(boundsSetter.get());
        this.doLayout();
    }

    public void addLayoutable(Component comp) {
        super.add(comp, " ");
    }

    @Override
    protected void paintComponent(Graphics g) {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
    }

    void changeContent(Component comp) {
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(comp, BorderLayout.CENTER);

    }

    @Override
    public Dimension getPreferredSize() {
        if (this.getLayout().getClass().isInstance(new AdvancedLayouter())) {
            this.doLayout();
            int w = 0;
            int h = 0;
            for (Component component : this.getComponents()) {
                if (component.getY() + component.getHeight() > h) {
                    h = component.getY() + component.getHeight();
                }
                if (component.getX() + component.getWidth() > w) {
                    w = component.getX() + component.getWidth();
                }
            }

            return new Dimension(w, h);
        }
        else return super.getPreferredSize();
    }
}