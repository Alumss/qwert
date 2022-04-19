package com.company;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class MouseAction implements MouseListener, MouseMotionListener {

    MouseAction(Windows windows) {
        this.windows = windows;
    }
    int startX, startY, endX, endY;
    Rectangle rect = new Rectangle();
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
        rect.endX = e.getX();
        rect.endY = e.getY();
        windows.window.revalidate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

class MouseActionRemove extends MouseAdapter {

    MouseActionRemove(Windows windows, MouseAction mouseAction) {
        this.windows = windows;
        this.mouseAction = mouseAction;
    }

    int num;
    Windows windows;
    MouseAction mouseAction;

    @Override
    public void mouseReleased(MouseEvent e) {
        windows.screen.removeMouseListener(mouseAction);
        windows.screen.removeMouseMotionListener(mouseAction);
        windows.screen.removeMouseListener(this);
        // added new window with new params of size
        windows = new Windows(720, 100);
        VideoPlay vPlay = new VideoPlay(windows);
        vPlay.play(mouseAction.startX, mouseAction.endX, mouseAction.startY, mouseAction.endY, num);
    }
}