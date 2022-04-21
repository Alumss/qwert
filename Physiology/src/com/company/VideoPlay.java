package com.company;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlay {

    VideoPlay(Windows windows) {
        this.windows = windows;
    }

    //переменная, содержащая номер кадра
    int num = 0;
    //создаие окна
    Windows windows;
    VideoDownload vD = new VideoDownload();
    //загрузка видео
    VideoCapture video = new VideoCapture(vD.getPathVideo());
    Mat img = new Mat();
    Timer timer = new Timer();

    void play(int startX, int endX, int startY, int endY, Mat mask) {
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
                        ImageProcessing imageProcessing = new ImageProcessing(mask);
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
            //добавление движений мыши
            Rectangle rectangle = new Rectangle(windows);
            windows.screen.addMouseListener(rectangle);
            windows.screen.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    timer.cancel();
                }
            });
            windows.screen.addMouseMotionListener(rectangle);
            MouseActionRemove mouseActionRemove = new MouseActionRemove(windows, rectangle);
            windows.screen.addMouseListener(mouseActionRemove);

            TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            if (cap.grab()) {
                                num++;
                                mouseActionRemove.num = num;
                                if (num > 50) {
                                    cap.retrieve(img);
                                    windows.show(img);
                                    System.out.println(num);
                                }
                            } else {
                                timer.cancel();
                                System.out.println("Task completed");
                            }
                        }
                    };
        timer.schedule(task, 0, 50);
    }

    void play(int startX, int endX, int startY, int endY, int numPlay) {
        vD.setVideo(video);
        VideoCapture cap = vD.getVideo();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (cap.grab()) {
                    num++;
                    if (num == numPlay) {
                        cap.retrieve(img);
                        img = img.colRange(startX, endX).rowRange(startY, endY);
                        windows.show(img);
                        //1 cadre => 1 object (mold)
                        Mold mold = new Mold(windows, img);
                        mold.accept(startX, endX, startY , endY);
                        windows.screen.addMouseMotionListener(mold);
                        windows.screen.addMouseWheelListener(mold);
                        windows.screen.addMouseListener(mold);
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
}