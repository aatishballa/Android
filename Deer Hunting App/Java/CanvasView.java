package com.example.mc9509dw.finalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class CanvasView extends View {

	public int width;
	public int height;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	Context context;
	private Paint mPaint, mDot;
	private float mX, mY;
	private float radius;
	private static final float TOLERANCE = 5;
	private MyPaths line;



	public CanvasView(Context c, AttributeSet attrs) {
		super(c, attrs);

		context = c;

		// we set a new Path
		mPath = new Path();

		// and we set a new Paint with the desired attributes
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeWidth(4f);

		// person position
		mDot = new Paint();
		mDot.setColor(Color.RED);
		mDot.setStyle(Paint.Style.FILL);
		radius = 20;

		line= new MyPaths();

	}

	// override onSizeChanged
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		// your Canvas will draw onto the defined Bitmap
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
	}

	// override onDraw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the mPath with the mPaint on the canvas when onDraw
		canvas.drawPath(mPath, mPaint);

		Display disp = ((WindowManager)this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		canvas.drawCircle(disp.getWidth()/2, disp.getHeight()/2 , radius, mDot);
	}

	// when ACTION_DOWN start touch according to the x,y values
	private void startTouch(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	// when ACTION_MOVE move touch according to the x,y values
	private void moveTouch(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOLERANCE || dy >= TOLERANCE) {
			mPath.lineTo(mX, mY);
			//mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	public void clearCanvas() {
		mPath.reset();
		invalidate();
	}

    public boolean addCanvas(int fileName) {

		try {
			FileOutputStream fo;
			fo= getContext().getApplicationContext().openFileOutput(Integer.toString(fileName)+".txt",MODE_PRIVATE);
			//fo= getContext().getApplicationContext().openFileOutput("1.txt",MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(line);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


	// when ACTION_UP stop touch
	private void upTouch() {
		mPath.lineTo(mX, mY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startTouch(x, y);

			//saving vals to redraw later
			line.addCordinates(x,y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			moveTouch(x, y);

			//saving vals to redraw later
			line.addCordinates(x,y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			upTouch();
			invalidate();

			break;
		}
		return true;
	}
}