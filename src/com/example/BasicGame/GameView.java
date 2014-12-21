package com.example.BasicGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Handler;
import android.os.Vibrator;
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

    boolean show = true;
    int lives = 3;
    int lvl = 1;
    int[] currentLvl;
    public int pom;
    public int i;
    String text;
    Vibrator vibrate;


    public GameView(Context context, Vibrator vib) {
        super(context);
        vibrate = vib;
        if(vibrate.hasVibrator()){
            Log.e("Has vibrator", "YES");
        }
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
        if(lives>0) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.rgb(51, 204, 255));
            canvas.drawPaint(paint);
            paintCircles(canvas, paint);
            paintLives(canvas, paint);
        }
        else{
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.rgb(0, 0, 0));
            paint.setColor(Color.RED);
            paint.setTextSize(120);
            canvas.drawText("GAME OVER!", getWidth() / 6, getHeight() / 2, paint);

        }

    }
    public void paintLives(Canvas canvas, Paint paint){
        for(int x = 0;x<lives;x++){
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.a);
            paint.setColor(Color.RED);
            canvas.drawBitmap(b, getWidth()/4-50+x*150, getHeight()/2+150, paint);
        }

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
            invalidate();


            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {

                    circField[currentLvl[i]].fill = false;
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
                circleAnim(currentLvl[i]);
                i++;
                if(i==lvl){
                    lvl++;
                    show = true;
                    i = 0;
                    text = "It is show time";
                    currentLvl = new int[lvl];
                    Handler c = new Handler();
                    c.postDelayed(new Runnable() {
                        public void run() {
                            showLvl();
                        }
                    }, 1000);
                }
            }
            else if(getObj(event.getX(), event.getY()) != -1 && lives>0){
                vibrate.vibrate(200);
                lives--;
                i = 0;
                if(lives<=0){
                   text = "You have lost";
                    invalidate();

                }
            }
            else{

            }

        }
        invalidate();
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
    public void circleAnim(int which){
        circField[which].fill = true;
        invalidate();

        pom = which;
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                circField[pom].fill = false;
                invalidate();
            }
        }, 200);

    }

}
