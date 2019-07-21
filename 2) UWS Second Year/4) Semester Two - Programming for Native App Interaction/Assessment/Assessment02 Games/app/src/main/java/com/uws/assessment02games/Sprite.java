package com.uws.assessment02games;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;

/**
 * Created by Yoshi on 02/05/2016.
 */
public class Sprite {
    private GameView gameView;
    private Bitmap bmp;

    private int x = 0;
    private int y = 0;
    private int xSpeed, ySpeed;
    private int width;
    private int height;
    private boolean visible = true;

    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        Random rnd = new Random();
        xSpeed = ((rnd.nextInt(10) + 1) * 6);
        ySpeed = ((rnd.nextInt(10) + 1) * 6);
    }

    public void update() {
        // sprites bouncing off the screen
        if (x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
    }

    public void onDraw(Canvas canvas) {
        if(visible)
            canvas.drawBitmap(bmp, x , y, null);
    }

    public boolean isHit(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }
}
