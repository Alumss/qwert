package com.company;

import java.awt.*;

public class Rectangle extends Shape {

    int startX, startY, endX, endY;

    @Override
    public void draw(Graphics2D g2) {
        g2.drawRect(startX, startY, endX - startX, endY - startY);
    }
}
