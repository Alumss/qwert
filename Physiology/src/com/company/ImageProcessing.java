package com.company;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class ImageProcessing {
    ImageProcessing(java.awt.Point[] array) {
        this.array = array;
    }

    java.awt.Point[] array;
    Mat image = new Mat();
    Mat result = new Mat();

    Mat make(Mat img) {
        //размер ядра обработки
        int size = 7;
        //создание ядра для обработки кадра, чтобы расширить темные области (убрать блик)
        Mat kernel = new Mat(size, size, CvType.CV_8UC1, new Scalar(1.0));
        //расширение темных областей (размывание для удаления шумов (блика))
        Imgproc.erode(img, image, kernel);
        int scale = 25;
        //обработка градиентов кадра
        Imgproc.pyrMeanShiftFiltering(image, image, scale, scale, 1);
        //перевод изображения в черно-белый формат
        //Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);

        Mat mask = new Mat(img.size(), CvType.CV_8UC1, new Scalar(0));
            for (int x = 0; x < array.length; x++) {
                Imgproc.line(mask, new Point(array[x].getX(), array[x].getY()), new Point(array[x].getX(), array[x].getY()), new Scalar(255), 5);
                System.out.println(array[x].getX() + " " + array[x].getY());
            }
            ArrayList<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(mask, contours, new Mat(), Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
            Mat markers = new Mat(img.size(), CvType.CV_32SC1, new Scalar(0));
            //с какого цвета выделять области
            int color = 30;
            for (int i = 0, j = contours.size(); i < j; i++) {
                Imgproc.drawContours(markers, contours, i, Scalar.all(color), 5);
                color += 50;
            }
            Imgproc.watershed(image, markers);
            markers.convertTo(result, CvType.CV_8U);
            return result;
    }
}
