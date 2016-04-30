package com.example.percy.tutorial;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int a = 0, b = 0;
    boolean running = false;
    TextView timer;
    private long startTime = 0L;
    long milliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private Handler handle=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView)findViewById(R.id.timer);
    }

    public void plus1A(View view) {
        a++;
        displayA();
    }

    public void plus2A(View view) {
        a += 2;
        displayA();
    }

    public void plus3A(View view) {
        a += 3;
        displayA();
    }

    public void plus1B(View view) {
        b++;
        displayB();
    }

    public void plus2B(View view) {
        b += 2;
        displayB();
    }

    public void plus3B(View view) {
        b += 3;
        displayB();
    }

    public void displayA() {
        TextView t = (TextView) findViewById(R.id.scoreA);
        t.setText(String.valueOf(a));
    }

    public void displayB() {
        TextView t = (TextView) findViewById(R.id.scoreB);
        t.setText(String.valueOf(b));
    }

    public void resetScores(View view) {
        a = 0;
        b = 0;
        displayA();
        displayB();
        timer.setText(R.string.zero);
        startTime=updatedTime=timeSwapBuff=0;
    }

    public void toggleTimer(View view) {
        if (running) {
            //running
            timeSwapBuff+=milliseconds;
            handle.removeCallbacks(updateTimerThread);
            running=false;
        } else {
            //not running
            startTime=SystemClock.uptimeMillis();
            handle.postDelayed(updateTimerThread,100);
            running=true;
        }
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            milliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + milliseconds;
            int seconds = (int) (updatedTime/1000);
            int minutes=seconds/60;
            seconds=seconds%60;
            timer.setText("" + minutes+":"+String.format("%02d",seconds));
            handle.postDelayed(this,100);
        }
    };
}
