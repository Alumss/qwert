package com.company;

import org.opencv.core.*;
import javax.swing.*;

public class Main extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        //координата х появления окна
        int x = 0;
        //координата у появления окна
        int y = 100;
        //создание окна
        Windows windows = new Windows(x, y);
        //запуск проигрывания видео
        VideoPlay vPlay = new VideoPlay(windows, null);
        //проигрывание исходного видео
        vPlay.play();
    }
}
