package com.company;

import org.opencv.core.*;
import javax.swing.*;

public class Main extends JFrame {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        //создание окна
        Windows windows = new Windows();
        //запуск проигрывания видео
        VideoPlay vPlay = new VideoPlay(windows);
        //проигрывание исходного видео
        vPlay.play();
    }
}
