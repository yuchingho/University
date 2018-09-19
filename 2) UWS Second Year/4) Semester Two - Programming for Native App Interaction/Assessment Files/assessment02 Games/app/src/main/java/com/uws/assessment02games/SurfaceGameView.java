package com.uws.assessment02games;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class SurfaceGameView extends SurfaceView implements Runnable {
    // variables needed to implement runnable
    private SurfaceHolder holder;
    Thread thread = null;
    volatile boolean running = false;
    static final long FPS = 10;
    private long lastClick;
    String move;

    private boolean visible = true;

    // declaring whiteBall
    private Bitmap whiteBall;
    Random rnd = new Random();  // making it appear randomly on the screen
    private int whiteBallX = rnd.nextInt(800); // numbers work on my phone dimensions.
    private int whiteBallY = rnd.nextInt(1600); // numbers work on my phone dimensions.

    // declaring rest of balls
    private Bitmap blackBall;
    private Bitmap blueBall;
    private Bitmap brownBall;
    private Bitmap greenBall;
    private Bitmap pinkBall;
    private Bitmap redBall;
    private Bitmap yellowball;

    // Sprites
    private SnookerSprite white;
    private SnookerSprite black;
    private SnookerSprite blue;
    private SnookerSprite brown;
    private SnookerSprite green;
    private SnookerSprite pink;
    private SnookerSprite red;
    private SnookerSprite yellow;

    // done, don't touch
    public SurfaceGameView(Context context) {
        super(context);
        thread = new Thread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                running = false;
                while (retry) {
                    try {
                        thread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                running = true;
                thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

        });

        whiteBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerwhite);
        white = new SnookerSprite(this, whiteBall);

        blackBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerblack);
        blueBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerblue);
        brownBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerbrown);
        greenBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookergreen);
        pinkBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerpink);
        redBall = BitmapFactory.decodeResource(getResources(), R.drawable.snookerred);
        yellowball = BitmapFactory.decodeResource(getResources(), R.drawable.snookeryellow);

        black = new SnookerSprite(this, blackBall);
        blue = new SnookerSprite(this, blueBall);
        brown = new SnookerSprite(this, brownBall);
        green = new SnookerSprite(this, greenBall);
        pink = new SnookerSprite(this, pinkBall);
        red = new SnookerSprite(this, redBall);
        yellow = new SnookerSprite(this, yellowball);
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (running) {
            Canvas c = null;
            startTime = System.currentTimeMillis();
            try {
                c = getHolder().lockCanvas();
                synchronized (getHolder()) {
                    update();
                    onDraw(c);
                }
            } finally {
                if (c != null) {
                    getHolder().unlockCanvasAndPost(c);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    thread.sleep(sleepTime);
                else
                    thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (eventaction) {
            case MotionEvent.ACTION_MOVE:
                whiteBallX = X;
                whiteBallY = Y;
                break;
        }
        invalidate();
        return true;
/*
        for (int i = SnookerSprite.size()-1; i >= 0; i--) {
            SnookerSprite sprite = SnookerSprite.get(i);
            if (sprite.isCollision(event.getX(), event.getY())) {
                SnookerSprite.remove(sprite);
            }
        }
        return super.onTouchEvent(event); */
/*
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();

            synchronized (getHolder()) {
                if (whiteBall.isHit(event.getX(), event.getY())) {
                    whiteBall.setVisible(false);
                }
            }
        }
        return super.onTouchEvent(event); */

        // Tried to make the whiteBall sprite collide with the rest of the sprites, but I had a lot of unsuccessful attempts
    }

    private void update() {
        white.update();
        black.update();
        blue.update();
        brown.update();
        green.update();
        pink.update();
        red.update();
        yellow.update();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.drawBitmap(whiteBall, whiteBallX - 50, whiteBallY - 50, null);

        black.onDraw(canvas);
        blue.onDraw(canvas);
        brown.onDraw(canvas);
        green.onDraw(canvas);
        pink.onDraw(canvas);
        red.onDraw(canvas);
        yellow.onDraw(canvas);
    }
}

