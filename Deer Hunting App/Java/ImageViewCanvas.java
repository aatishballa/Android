package com.example.mc9509dw.finalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mc9509dw on 4/15/2017.
 *
 */

public class ImageViewCanvas extends View {

    private MyPaths pathOb= new MyPaths();
    private Path mPath;
    private ArrayList xPath;
    private ArrayList yPath;
    private Paint linePaint;

    public ImageViewCanvas(Context context) {
        super(context);

        mPath = new Path();

        linePaint=new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeWidth(4f);

        xPath=new ArrayList();
        yPath=new ArrayList();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint pBackground = new Paint();
        pBackground.setColor(Color.LTGRAY);
        //canvas.drawRect(0, 0, 550, 550, pBackground);

        //test

//        mPath.moveTo(50, 20);
//        mPath.lineTo(100,200);

        //redraw points

        xPath=this.pathOb.getxPath();
        yPath=this.pathOb.getyPath();
        //Toast.makeText(getContext().getApplicationContext(), xPath.get(0).toString()+" "+ yPath.get(0).toString(), Toast.LENGTH_LONG).show();

        mPath.moveTo(((float) xPath.get(0)/2), ((float) yPath.get(0)/2));
        //mPath.lineTo(800,900);

        for (int i=1;i<xPath.size();i++){

            //Toast.makeText(getContext().getApplicationContext(), xPath.get(i).toString()+" "+ yPath.get(i).toString(), Toast.LENGTH_SHORT).show();
            mPath.lineTo(((float) xPath.get(i)/2), ((float) yPath.get(i)/2));
            canvas.drawPath(mPath,linePaint);

        }

        //canvas.drawPath(mPath,linePaint);

    }

    public void setPathOb(MyPaths p){
        this.pathOb=p;
    }
}
