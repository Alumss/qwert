package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JComponent {

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(255, 255, 255));
        draw(g2);
        repaint();
    }

    public abstract void draw(Graphics2D g2);
}
