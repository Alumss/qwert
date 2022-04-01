package com.company;

import org.opencv.core.*;
import javax.swing.*;

public class Main extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        //ширина изначально загружаемого кадра видео
        int width = 720;
        //высота изначально загружаемого кадра видео
        int height = 480;
        //координата х появления окна
        int x = 0;
        //координата у появления окна
        int y = 100;
        //создание окна
        Windows windows = new Windows();
        //запуск проигрывания видео
        VideoPlay vPlay = new VideoPlay(x, y, windows, null);
        //обрезка кадра исходного видео (не нужна)
        vPlay.play(0, width, 0, height);
    }
}
