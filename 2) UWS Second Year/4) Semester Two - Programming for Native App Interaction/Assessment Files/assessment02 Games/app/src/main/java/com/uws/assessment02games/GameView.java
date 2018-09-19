package com.uws.assessment02games;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Yoshi on 02/05/2016.
 */
public class GameView extends SurfaceView implements Runnable {
    // variables needed to implement runnable
    Thread thread = null;
    volatile boolean running = false;
    private SurfaceHolder holder;
    private long lastClick; // onTouchEvent
    static final long FPS = 10;

    int score;
    int lives;
    int time;

    // Balloon sprites
    private Sprite balloonOne;
    private Bitmap balloon1;

    private Sprite balloonTwo;
    private Bitmap balloon2;

    private Sprite balloonThree;
    private Bitmap balloon3;

    private Sprite balloonFour;
    private Bitmap balloon4;

    private Sprite balloonFive;
    private Bitmap balloon5;

    private Sprite balloonSix;
    private Sprite balloonSeven;
    private Sprite balloonEight;
    private Sprite balloonNine;
    private Sprite balloonTen;
    private Sprite balloonEleven;
    private Sprite balloonTwelve;
    private Sprite balloonThirteen;
    private Sprite balloonFourteen;
    private Sprite balloonFifteen;
    private Bitmap balloon6;
    private Bitmap balloon7;
    private Bitmap balloon8;
    private Bitmap balloon9;
    private Bitmap balloon10;
    private Bitmap balloon11;
    private Bitmap balloon12;
    private Bitmap balloon13;
    private Bitmap balloon14;
    private Bitmap balloon15;

    public GameView(Context context) {
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

        balloon1 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon1);
        balloonOne = new Sprite(this, balloon1);

        balloon2 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon2);
        balloonTwo = new Sprite(this, balloon2);

        balloon3 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon3);
        balloonThree= new Sprite(this, balloon3);

        balloon4 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon4);
        balloonFour = new Sprite(this, balloon4);

        balloon5 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon5);
        balloonFive= new Sprite(this, balloon5);

        balloon6 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon1);
        balloonSix = new Sprite(this, balloon6);

        balloon7 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon2);
        balloonSeven = new Sprite(this, balloon7);

        balloon8 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon3);
        balloonEight = new Sprite(this, balloon8);

        balloon9 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon4);
        balloonNine = new Sprite(this, balloon9);

        balloon10 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon5);
        balloonTen = new Sprite(this, balloon10);

        balloon11 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon1);
        balloonEleven = new Sprite(this, balloon11);

        balloon12 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon2);
        balloonTwelve = new Sprite(this, balloon12);

        balloon13 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon3);
        balloonThirteen = new Sprite(this, balloon13);

        balloon14 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon4);
        balloonFourteen = new Sprite(this, balloon14);

        balloon15 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon5);
        balloonFifteen = new Sprite(this, balloon15);
    }

    @Override
    public void run() {

        score = 0;
        lives = 3;
        time = 0;

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

    public void finish() {
        finish();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);
        balloonOne.onDraw(canvas);
        balloonTwo.onDraw(canvas);
        balloonThree.onDraw(canvas);
        balloonFour.onDraw(canvas);
        balloonFive.onDraw(canvas);
        balloonSix.onDraw(canvas);
        balloonSeven.onDraw(canvas);
        balloonEight.onDraw(canvas);
        balloonNine.onDraw(canvas);
        balloonTen.onDraw(canvas);
        balloonEleven.onDraw(canvas);
        balloonTwelve.onDraw(canvas);
        balloonThirteen.onDraw(canvas);
        balloonFourteen.onDraw(canvas);
        balloonFifteen.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        canvas.drawText("Lives: " + lives, 0, 60, paint);

        if (score > 12) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(60);
            canvas.drawText("You have won!", 0, 240, paint);
            time ++;
            if (time == 30) {
                finish();
            }
        }
        if (lives < 0) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(60);
            canvas.drawText("You have lost!", 0, 240, paint);
            time ++;
            if (time == 30) {
                finish();
            }
        }
    }

    private void update()   {
        balloonOne.update();
        balloonTwo.update();
        balloonThree.update();
        balloonFour.update();
        balloonFive.update();
        balloonSix.update();
        balloonSeven.update();
        balloonEight.update();
        balloonNine.update();
        balloonTen.update();
        balloonEleven.update();
        balloonTwelve.update();
        balloonThirteen.update();
        balloonFourteen.update();
        balloonFifteen.update();
    }

    // balloons onTouch destroyed
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();

            // sound doesn't work.
            // wanted to make it so when appropriate balloon is destroyed, sound matching it plays
            synchronized (getHolder()) {
                if (balloonOne.isHit(event.getX(), event.getY())) {
                    balloonOne.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audioone);
                    //player.start();
                    score = score + 1;
                }
                if (balloonTwo.isHit(event.getX(), event.getY())) {
                    balloonTwo.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiotwo);
                    //player.start();
                    score = score + 1;
                }
                if (balloonThree.isHit(event.getX(), event.getY())) {
                    balloonThree.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiothree);
                    //player.start();
                    score = score + 1;
                }
                if (balloonFour.isHit(event.getX(), event.getY())) {
                    balloonFour.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofour);
                    //player.start();
                    score = score + 1;
                }
                if (balloonFive.isHit(event.getX(), event.getY())) {
                    balloonFive.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofive);
                    //player.start();
                    lives = lives - 1;
                }
                if (balloonSix.isHit(event.getX(), event.getY())) {
                    balloonSix.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audioone);
                    //player.start();
                    score = score + 1;
                }
                if (balloonSeven.isHit(event.getX(), event.getY())) {
                    balloonSeven.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiotwo);
                    //player.start();
                    score = score + 1;
                }
                if (balloonEight.isHit(event.getX(), event.getY())) {
                    balloonEight.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiothree);
                    //player.start();
                    score = score + 1;
                }
                if (balloonNine.isHit(event.getX(), event.getY())) {
                    balloonNine.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofour);
                    //player.start();
                    score = score + 1;
                }
                if (balloonTen.isHit(event.getX(), event.getY())) {
                    balloonTen.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofive);
                    //player.start();
                    lives = lives - 1;
                }
                if (balloonEleven.isHit(event.getX(), event.getY())) {
                    balloonEleven.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audioone);
                    //player.start();
                    score = score + 1;
                }
                if (balloonTwelve.isHit(event.getX(), event.getY())) {
                    balloonTwelve.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiotwo);
                    //player.start();
                    score = score + 1;
                }
                if (balloonThirteen.isHit(event.getX(), event.getY())) {
                    balloonThirteen.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiothree);
                    //player.start();
                    score = score + 1;
                }
                if (balloonFourteen.isHit(event.getX(), event.getY())) {
                    balloonFourteen.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofour);
                    //player.start();
                    score = score + 1;
                }
                if (balloonFifteen.isHit(event.getX(), event.getY())) {
                    balloonFifteen.setVisible(false);
                    //player = MediaPlayer.create(this, R.raw.audiofive);
                    //player.start();
                    lives = lives - 1;
                }
            }
        }
        return super.onTouchEvent(event);
    }
}

