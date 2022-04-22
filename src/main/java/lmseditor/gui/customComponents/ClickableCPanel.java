package lmseditor.gui.customComponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickableCPanel extends CPanel {
    private Runnable act;
    public ClickableCPanel() {
        super();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    act.run();
                }
                super.mouseClicked(e);
            }
        });
    }

    public void setAction(Runnable act) {
        this.act = act;
    }
}
