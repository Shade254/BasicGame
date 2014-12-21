package com.example.BasicGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

/**
 * Created by slava on 12/21/14.
 */
public class GameView  extends View implements View.OnTouchListener {
    public OneCircle[] circField;
    boolean def = false;
    boolean runCh = false;
    boolean runShow = false;
    boolean show = true;
    int lives = 3;
    int lvl = 1;
    int[] currentLvl;
    public int pom;
    public int i;
    String text;


    public GameView(Context context) {
        super(context);
        setOnTouchListener(this);
        init();
        i = 0;
        text = "It is show time";
        currentLvl = new int[lvl];
        showLvl();
    }

    public void onDraw(Canvas canvas) {
        if (!def) {
            init();
        }
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paintCircles(canvas, paint);


    }

    public void paintCircles(Canvas canvas, Paint paint) {

        paint.setStrokeWidth(10);
        int radius = 150;
        for (int i = 0; i < circField.length; i++) {
            if (circField[i].fill) {
                paint.setStyle(Paint.Style.FILL);
            } else {
                paint.setStyle(Paint.Style.STROKE);
            }
            paint.setColor(circField[i].c);
            if (i == 0) {
                canvas.drawCircle(radius, radius, radius, paint);
            } else if (i == 1) {
                canvas.drawCircle(getWidth() - radius, radius, radius, paint);
            } else if (i == 2) {
                canvas.drawCircle(radius, getHeight() - radius, radius, paint);
            } else if (i == 3) {
                canvas.drawCircle(getWidth() - radius, getHeight() - radius, radius, paint);
            }
        }
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        canvas.drawText(text, getWidth() / 4, (getHeight() / 2) - 120, paint);
        canvas.drawText("In lvl:" + String.valueOf(lvl), getWidth() / 4, getHeight() / 2, paint);
    }

    public void init() {
        circField = new OneCircle[4];
        for (int i = 0; i < circField.length; i++) {

            if (i == 0) {
                circField[i] = new OneCircle(Color.BLUE);
            } else if (i == 1) {
                circField[i] = new OneCircle(Color.RED);
            } else if (i == 2) {
                circField[i] = new OneCircle(Color.GREEN);
            } else if (i == 3) {
                circField[i] = new OneCircle(Color.YELLOW);
            }
        }
        def = true;
    }

    public void showLvl(){
        Random rand = new Random();

            currentLvl[i] = rand.nextInt(3);
            circField[currentLvl[i]].fill = true;
            pom = i;
            invalidate();
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {

                    circField[currentLvl[pom]].fill = false;
                    invalidate();
                    i++;


                    if (i < lvl) {
                        Handler c = new Handler();
                        c.postDelayed(new Runnable() {
                            public void run() {
                                showLvl();
                            }
                        }, 500);
                    }


                    else{
                        i = 0;
                        text = "Now is yours turn";
                        show = false;
                    }
                }
            }, 500);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!show){
            if(getObj(event.getX(), event.getY()) == currentLvl[i]){
                i++;
                Log.e("Tag", "Got it " + i + "   " + lvl);
                if(i==lvl){
                    lvl++;
                    show = true;
                    i = 0;
                    text = "It is show time";
                    currentLvl = new int[lvl];
                    showLvl();
                }
            }
            else if(getObj(event.getX(), event.getY()) != -1){
                lives--;
                Log.e("Tag", "Miss " + lives);
                if(lives<=0){
                   text = "You have lost";
                    invalidate();

                }
            }
            else{
                Log.e("Tag", "You missed");
            }

        }
        return false;
    }
    public int getObj(float x, float y){
        if(x>0&&x<2*150&&y>0&&y<2*150){
            return 0;
        }
        if(x>getWidth()-2*150&&x<getWidth()&&y>0&&y<2*150){
            return 1;
        }
        if(x>0&&x<2*150&&y>getHeight()-2*150&&y<getHeight()){
            return 2;
        }
        if(x>getWidth()-2*150&&x<getWidth()&&y>getHeight()-2*150&&y<getHeight()){
            return 3;
        }
        else{
            return -1;
        }

    }
}
