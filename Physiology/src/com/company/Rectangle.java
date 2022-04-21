package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class Rectangle extends JComponent implements MouseListener, MouseMotionListener {

    Rectangle(Windows windows) {
        this.windows = windows;
    }
    int startX, startY, endX, endY;
    Windows windows;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(255, 255, 255));
        g2.drawRect(startX, startY, endX - startX, endY - startY);
        repaint();
    }

    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {
        repaint();
        startX = endX = e.getX();
        startY = endY = e.getY();
        windows.window.add(this);
        windows.window.revalidate();
    }

    @Override
    public void mouseReleased (MouseEvent e) {
        System.out.println("Координата х левого верхнего угла " + startX + ", координата у левого верхнего угла " + startY);
        System.out.println("Координата х правого нижнего угла " + endX + ", координата у правого нижнего угла " + endY);
        }

        @Override
        public void mouseEntered (MouseEvent e) {
        }

        @Override
        public void mouseExited (MouseEvent e) {
        }

    @Override
    public void mouseDragged(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        windows.window.revalidate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

class MouseActionRemove extends MouseAdapter {

    MouseActionRemove(Windows windows, Rectangle rectangle) {
        this.windows = windows;
        this.rectangle = rectangle;
    }

    int num;
    Windows windows;
    Rectangle rectangle;

    @Override
    public void mouseReleased(MouseEvent e) {
        windows.screen.removeMouseListener(rectangle);
        windows.screen.removeMouseMotionListener(rectangle);
        windows.screen.removeMouseListener(this);
        // added new window with new params of size
        windows = new Windows(720, 100);
        VideoPlay vPlay = new VideoPlay(windows);
        vPlay.play(rectangle.startX, rectangle.endX, rectangle.startY, rectangle.endY, num);
    }
}