package com.uws.assessment02games;

/**
 * Created by Yoshi on 03/05/2016.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;


public class SnookerSprite {
    private SurfaceGameView gameView;
    private Bitmap bmp;

    private int x = 0;
    private int y = 0;
    private boolean visible = true;
    private int width;
    private int height;

    public SnookerSprite(SurfaceGameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        Random rnd = new Random();
        x = rnd.nextInt(800);
        y = rnd.nextInt(1600);
    }

    public void update() {
      /*  if (x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed; */
    }

    public void onDraw(Canvas canvas) {
        if(visible)
            canvas.drawBitmap(bmp, x , y, null);
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public boolean isHit(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

    public boolean isCollision (float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }
}

