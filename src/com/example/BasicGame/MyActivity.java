package com.example.BasicGame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    Button next;
    TextView tv;
    LinearLayout l;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        next = (Button)findViewById(R.id.button);
        tv = (TextView)findViewById(R.id.textView);
        tv.setTextColor(Color.BLACK);
        l = (LinearLayout)findViewById(R.id.a);
        l.setBackgroundColor(Color.CYAN);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);

                Intent myIntent = new Intent(MyActivity.this, GameActivity.class);
                MyActivity.this.startActivity(myIntent);
            }
        });
    }
}
