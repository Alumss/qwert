package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseAction implements MouseListener, MouseMotionListener {

    MouseAction(Windows windows, Rectangle rect) {
        this.windows = windows;
        this.rect = rect;
    }
    int startX, startY, endX, endY;
    Rectangle rect;
    Windows windows;

    Point[] array = new Point[5];
    //для перечисления точек, пока их 5
    int x = 0;

    @Override
    public void mouseClicked (MouseEvent e) {
    }

    @Override
    public void mousePressed (MouseEvent e) {
        rect.repaint();
        rect.startX = rect.endX = e.getX();
        rect.startY = rect.endY = e.getY();
        windows.window.add(rect);
        windows.window.revalidate();
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        startX = rect.startX;
        startY = rect.startY;
        endX = rect.endX;
        endY = rect.endY;
        System.out.println("Координата х левого верхнего угла " + startX + ", координата у левого верхнего угла " + startY);
        System.out.println("Координата х правого нижнего угла " + endX + ", координата у правого нижнего угла " + endY);
        Point equals = new Point(startX, startY);
        windows.screen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                while (x < array.length) {
                    array[x] = e.getPoint();
                    array[x] = conversion(array[x], equals);
                    for (Point y : array) System.out.println(y);
                    x++;
                    if (!(array[4] == null)) {
                        //координата х появления окна
                        int x = 800;
                        //координата у появления окна
                        int y = 100;
                        VideoPlay vPlay = new VideoPlay(new Windows(x, y), array);
                        vPlay.play(startX, endX, startY, endY);
                    }
                    break;
                }
            }
        });
        windows.window.revalidate();
        }

        @Override
        public void mouseEntered (MouseEvent e) {
        }

        @Override
        public void mouseExited (MouseEvent e) {
        }

    @Override
    public void mouseDragged(MouseEvent e) {
        //от правого верхнего угла
        if (rect.startX > e.getX() & rect.endY < e.getY()) {
            rect.startX = e.getX();
            rect.endY = e.getY();
        }
        //от правого нижнего угла
        if (rect.startX > e.getX() & rect.startY > e.getY()) {
            rect.startX = e.getX();
            rect.startY = e.getY();
        }
        //от левого нижнего угла
        if (rect.endX < e.getX() & rect.startY > e.getY()) {
            rect.endX = e.getX();
            rect.startY = e.getY();
        }
        //от левого верхнего угла
        if (rect.endX < e.getX() & rect.endY < e.getY()) {
            rect.endX = e.getX();
            rect.endY = e.getY();
        }
        windows.window.revalidate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public static Point conversion(Point array, Point equals) {
        double x = equals.getX();
        double y = equals.getY();
        array.setLocation(array.getX() - x, array.getY() - y);
        return array;
    }
}