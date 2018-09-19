package com.uws.pnai.customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {


    // declare variables needed for View
    private Paint redPaint;
    private Bitmap ball1;
    private int ballX, ballY;
    private String output = "output will appear here";


    public GameView(Context context) {
        super(context);
        ball1 = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

        redPaint = new Paint();
        redPaint.setColor(Color.RED);
        redPaint.setTextSize(48);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(output, 10, 100, redPaint);
        canvas.drawBitmap(ball1, 10, 10, null);
        canvas.drawCircle(50, 50, 10, redPaint);
        canvas.drawBitmap(ball1, ballX, ballY, null);
        canvas.drawBitmap(ball1, ballX - ball1.getWidth()/2, ballY - ball1.getHeight()/2, null);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (eventaction ) {
            case MotionEvent.ACTION_DOWN:
                output = "down";
                ballX = X;
                ballY = Y;

                break;
            case MotionEvent.ACTION_MOVE:
                output = "move";
                break;
            case MotionEvent.ACTION_UP:
                output = "up";
                break;
        }
        invalidate();
        return true;
    }

}