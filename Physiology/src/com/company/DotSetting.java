package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DotSetting implements MouseListener {
    Point[] array = new Point[5];
    int x = 0;
    private int startX, startY, endX, endY;
    Point equals = new Point();

    public void setCoordinates(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        equals.setLocation(startX, startY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        while (x < array.length) {
            array[x] = e.getPoint();
            array[x] = minus(array[x], equals);
            for (Point y : array) System.out.println(y);
            x++;
            if (!(array[4] == null)) {
                //координата х появления окна
                int x = 800;
                //координата у появления окна
                int y = 100;
                VideoPlay vPlay = new VideoPlay(x, y, new Windows(), array);
                vPlay.play(startX, endX, startY, endY);
            }
            break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static Point minus(Point array, Point equals) {
        double x = equals.getX();
        double y = equals.getY();
        array.setLocation(array.getX() - x, array.getY() - y);
        return array;
    }
}
