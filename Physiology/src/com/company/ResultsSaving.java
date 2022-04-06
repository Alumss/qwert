package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class ResultsSaving {
    //получение каких-то данных и их сохранение
    void save() {
        try {
            FileWriter results = new FileWriter("results.txt");

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
