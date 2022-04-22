import customComponents.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(400, 800);
        frame.getContentPane().add(new LeftPanel(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}