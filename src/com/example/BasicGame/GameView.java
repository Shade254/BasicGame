package com.example.BasicGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.util.Random;

/**
 * Created by slava on 12/21/14.
 */
public class GameView  extends View{
    public OneCircle[] circField;
    boolean def = false;
    public GameView(Context context) {
        super(context);
        init();
    }
    public void onDraw(Canvas canvas){
        if(!def){
            init();
        }
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paintCircles(canvas, paint);


    }
    public void paintCircles(Canvas canvas, Paint paint){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        int radius = 150;
        for(int i = 0;i<circField.length;i++){
            paint.setColor(circField[i].c);
            if(i == 0){
                canvas.drawCircle(radius, radius, radius, paint);
            }
           else if(i == 1){
                canvas.drawCircle(getWidth()-radius, radius, radius, paint);
            }
           else  if(i == 2){
                canvas.drawCircle(radius, getHeight()-radius, radius, paint);
            }
            else if(i == 3){
                canvas.drawCircle(getWidth()-radius, getHeight()-radius, radius, paint);
            }
        }
    }
    public void init(){
       circField = new OneCircle[4];
        for(int i = 0;i<circField.length;i++){

            if(i == 0){
                circField[i] = new OneCircle(Color.BLUE);
            }
            else if(i == 1){
                circField[i] = new OneCircle(Color.RED);
            }
            else  if(i == 2){
                circField[i] = new OneCircle(Color.GREEN);
            }
            else if(i == 3){
                circField[i] = new OneCircle(Color.YELLOW);
            }
        }
        def = true;
    }
}
