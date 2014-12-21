package com.example.BasicGame;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by slava on 12/21/14.
 */
public class GameActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
