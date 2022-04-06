package com.company;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlay {

    VideoPlay(Windows windows, Point[] array) {
        this.windows = windows;
        this.array = array;
    }

    Point[] array;
    //переменная, содержащая номер кадра
    int num = 0;
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

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (cap.grab()) {
                    num++;
                    if (num > 50) {
                        cap.retrieve(img);
                        img = img.colRange(startX, endX).rowRange(startY, endY);
                        ImageProcessing imageProcessing = new ImageProcessing(array);
                        img = imageProcessing.make(img);
                        windows.show(img);
                        System.out.println(num);
                    }
                } else {
                    timer.cancel();
                    System.out.println("Task completed");
                }
            }
        };
        timer.schedule(task, 0, 10);
    }

        void play() {
            vD.setVideo(video);
            VideoCapture cap = vD.getVideo();
            //отрисовка прямоугольника
            Rectangle rect = new Rectangle();
            //обрезка кадра
            MouseAction mouseAction = new MouseAction(windows, rect);
            //добавление движений мыши
            windows.screen.addMouseListener(mouseAction);
            windows.screen.addMouseMotionListener(mouseAction);

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
                                windows.screen.removeMouseListener(mouseAction);
                                windows.screen.removeMouseMotionListener(mouseAction);
                            }
                        });
                        if (num > 50) {
                            cap.retrieve(img);
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
        timer.schedule(task, 0, 100);
    }
}