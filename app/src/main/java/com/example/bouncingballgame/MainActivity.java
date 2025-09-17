package com.example.bouncingballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    class GameView extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder holder;
        volatile boolean playing;
        Paint paint;
        float ballX, ballY, ballSpeedY;
        final float gravity = 0.5f;

        public GameView(Context context) {
            super(context);
            holder = getHolder();
            paint = new Paint();
            ballX = 300;
            ballY = 300;
            ballSpeedY = 0;
        }

        @Override
        public void run() {
            while (playing) {
                update();
                draw();
                control();
            }
        }

        public void update() {
            ballSpeedY += gravity;
            ballY += ballSpeedY;

            if (ballY > getHeight() - 100) { // الأرض
                ballY = getHeight() - 100;
                ballSpeedY = 0;
            }
        }

        public void draw() {
            if (holder.getSurface().isValid()) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                paint.setColor(Color.BLUE);
                canvas.drawCircle(ballX, ballY, 50, paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void control() {
            try { Thread.sleep(17); } catch (InterruptedException e) {}
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ballSpeedY = -15; // اقفز
            }
            return true;
        }

        public void pause() {
            playing = false;
            try { gameThread.join(); } catch (InterruptedException e) {}
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((GameView) getWindow().getDecorView()).pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((GameView) getWindow().getDecorView()).resume();
    }
}
