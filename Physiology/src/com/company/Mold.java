package com.company;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mold extends MouseAdapter {

    Mold(Windows windows, Mat img) {
        this.windows = windows;
        this.img = img;
        //радиус окружности
        radius = 10;
        //для отслеживания относительного изменения размера
        x = 0;
        c = 0;
        engelIris = 15;
        engelSclera = 5;
        DIAMETERSCLERA = 90;
        DIAMETERIRIS = 70;
    }

    int x, radius, c, engelIris, engelSclera, diameterIris, diameterSclera, startX, endX, startY, endY;
    final int DIAMETERSCLERA;
    final int DIAMETERIRIS;
    Windows windows;
    Mat img, imgPreview;
    //color install
    Scalar color = new Scalar(255, 255, 255);
    Point center = new Point();
    double[] xY = new double[2];
    Size sizeIris, sizeSclera;

    void accept(int startX, int endX, int startY, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(Mat imgPreview) {
        sizeIris = new Size(diameterIris, diameterIris);
        sizeSclera = new Size(diameterSclera, diameterSclera);
        //модель глаза
        Imgproc.circle(imgPreview, center, radius, color, -1);
        Imgproc.ellipse(imgPreview, center, sizeIris, c, -engelIris, engelIris, color);
        Imgproc.ellipse(imgPreview, center, sizeSclera, c, -engelSclera, engelSclera, color);
        Imgproc.ellipse(imgPreview, center, sizeIris, c, 180 - engelIris, 180 + engelIris, color);
        Imgproc.ellipse(imgPreview, center, sizeSclera, c, 180 - engelSclera, 180 + engelSclera, color);
        windows.window.revalidate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();

        xY[0] = e.getX();
        xY[1] = e.getY();
        center.set(xY);

        draw(imgPreview);
        windows.show(imgPreview);
    }

    public void repaint() {
        imgPreview = img.clone();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        x += e.getWheelRotation();
        if (x <= 0) x = 0;
        if (x >= 100) x = 100;
        if (x > 0 && x < 100) {
            repaint();
            System.out.println(x);

            diameterIris = DIAMETERIRIS * x / 100;
            diameterSclera = DIAMETERSCLERA * x / 100;

            draw(imgPreview);
            windows.show(imgPreview);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (c == -180 || c == 180) c = 0;
        if (e.getButton() == MouseEvent.BUTTON3) {
            repaint();
            c += 2;
            draw(imgPreview);
            windows.show(imgPreview);
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            repaint();
            c -= 2;
            draw(imgPreview);
            windows.show(imgPreview);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            windows.screen.removeMouseListener(this);
            windows.screen.removeMouseMotionListener(this);
            windows.screen.removeMouseWheelListener(this);
            Mat mask = new Mat(img.size(), CvType.CV_8UC1, new Scalar(0));
            draw(mask);
            VideoPlay vPlay = new VideoPlay(new Windows(720 + endX - startX, 100));
            vPlay.play(startX, endX, startY, endY, mask);
        }
    }
}
