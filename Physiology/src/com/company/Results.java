package com.company;

import javax.swing.*;
import java.awt.*;

public class Results extends JComponent {

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(10, 450, 10, 10);
        g.drawLine(10, 450, 470, 450);
        g.fillArc(0, 0, 20, 20, 250, 40);
        g.fillArc(460, 440, 20, 20, 160, 40);

        repaint();
    }
}