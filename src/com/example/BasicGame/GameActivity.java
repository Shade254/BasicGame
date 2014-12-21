package com.example.BasicGame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;

/**
 * Created by slava on 12/21/14.
 */
public class GameActivity extends Activity{
    Vibrator vibrate;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrate = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(new GameView(this, vibrate));
    }
}
