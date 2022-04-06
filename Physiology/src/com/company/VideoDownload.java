package com.company;

import org.opencv.videoio.VideoCapture;

public class VideoDownload {
    //написать коллекцию из видео-файлов
    String name0 = "src/2021-03-01_153237_678.avi";
    String name1 = "src/2021-03-01_151423_931.mp4";
    String name2 = "src/2021-03-16_162607_367.avi";

    VideoCapture video;

    public void setVideo(VideoCapture video) {
        this.video = video;
    }

    public VideoCapture getVideo() {
        return video;
    }
}
