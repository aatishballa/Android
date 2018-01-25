package com.example.mc9509dw.finalapp;

/**
 * Created by mc9509dw.b on 4/25/2017.
 */

public class FileNameHandler {

    private static int fileName;

    public FileNameHandler(){
        fileName=1;
    }

    public static void incrementFile(){
        fileName++;
    }

    public static String getFileName(){
        return Integer.toString(fileName);
    }
}
