package com.company;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlay {

    VideoPlay(int x, int y, Windows windows, Point[] array) {
        this.x = x;
        this.y = y;
        this.windows = windows;
        windows.window.setLocation(x, y);
        this.array = array;
    }

    Point[] array;
    int num = 0;
    int x, y;
    //создаие окна
    Windows windows;
    VideoDownload vD = new VideoDownload();
    //загрузка видео
    VideoCapture video = new VideoCapture(vD.name0);
    Mat img = new Mat();
    Timer timer = new Timer();

    void play(int startX, int endX, int startY, int endY) {
        vD.setVideo(video);
        VideoCapture cap = vD.getVideo();
        //отрисовка прямоугольника
        Rectangle rect = new Rectangle();
        //обрезка кадра
        Cut cut = new Cut(windows, rect);
        //добавление движений мыши
        windows.screen.addMouseListener(cut);
        windows.screen.addMouseMotionListener(cut);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (cap.grab()) {
                    num++;
                    windows.screen.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            timer.cancel();
                        }
                    });
                    windows.screen.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            windows.screen.removeMouseListener(cut);
                            windows.screen.removeMouseMotionListener(cut);
                        }
                    });
                    if (num > 50) {
                        cap.retrieve(img);
                        ImageProcessing imageProcessing = new ImageProcessing(array);
                        img = img.colRange(startX, endX).rowRange(startY, endY);
                        img = imageProcessing.make(img);
                        windows.show(img);
                        System.out.println(num);
                    }
                }
                else {
                    timer.cancel();
                    System.out.println("Task completed");
                }
            }
        };
        timer.schedule(task, 0, 10);
    }
}