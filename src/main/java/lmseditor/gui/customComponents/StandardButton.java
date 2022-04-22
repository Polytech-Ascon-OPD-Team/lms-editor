package lmseditor.gui.customComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandardButton extends JButton {

    protected Runnable act;

    public StandardButton(String text){
        super(text);
        setFocusPainted(false);
    }

    public StandardButton() {
        super();
        setFocusPainted(false);
    }

    public void setAction(Runnable action) {
        act = action;
        super.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                act.run();
            }
        } );
    }
}
