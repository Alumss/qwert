package com.company;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Cut implements MouseListener, MouseMotionListener {

    Cut(Windows windows, Rectangle rect) {
        this.windows = windows;
        this.rect = rect;
    }
    int startX, startY, endX, endY;
    Rectangle rect;
    Windows windows;

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
            System.out.println("Координата х левого верхнего угла " + rect.startX + ", координата у левого верхнего угла " + rect.startY);
            System.out.println("Координата х правого нижнего угла " + rect.endX + ", координата у правого нижнего угла " + rect.endY);
            DotSetting dotSetting = new DotSetting();
            dotSetting.setCoordinates(startX, startY, endX, endY);
            windows.screen.addMouseListener(dotSetting);
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
}