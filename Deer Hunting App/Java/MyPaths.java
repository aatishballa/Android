package com.example.mc9509dw.finalapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mc9509dw on 4/6/2017.
 */

public class MyPaths implements Serializable{

    private ArrayList xPath, yPath;

    public MyPaths(){
        xPath=new ArrayList();
        yPath=new ArrayList();
    }

    public void addCordinates(float x,float y){
        xPath.add(x);
        yPath.add(y);
    }

    public ArrayList getxPath() {
        return xPath;
    }

    public ArrayList getyPath() {
        return yPath;
    }

    public void setxPath(ArrayList xPath) {
        this.xPath = xPath;
    }

    public void setyPath(ArrayList yPath) {
        this.yPath = yPath;
    }
}
