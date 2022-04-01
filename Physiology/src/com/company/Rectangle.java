package com.company;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JComponent {

    int startX, startY, endX, endY;
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(255, 255, 255));
        g2.drawRect(startX, startY, endX - startX, endY - startY);
        repaint();
    }
}
