package com.company;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import javax.swing.*;

public class Windows {
    JFrame window = new JFrame();
    JLabel screen = new JLabel();
    MatOfByte buf = new MatOfByte();

    void show(Mat img) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        Imgcodecs.imencode(".jpg", img, buf);
        ImageIcon ic = new ImageIcon(buf.toArray());
        screen.setIcon(ic);
        window.getContentPane().add(screen);
        window.pack();
    }
}
