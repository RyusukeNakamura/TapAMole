package com.lifeistech.android.tapamole;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView scoreText,timeText;
    int[]imageResources={
            R.id.imageView,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,
            R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10,R.id.imageView11
    };
    Mole[] moles;

    int time,score;

    Timer timer;
    TimerTask timerTask;
    Handler h;

    Random r=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText=(TextView)findViewById(R.id.scoreText);
        timeText=(TextView)findViewById(R.id.timeText);

        moles=new Mole[12];
        for(int i=0;i<moles.length;i++){
            ImageView imageView=(ImageView)findViewById(imageResources[i]);
            moles[i]=new Mole(imageView);
        }
        h=new Handler();

    }
    public void start(View v){
        time=60;score=0;
        timeText.setText(String.valueOf(time));
        scoreText.setText(String .valueOf(score));
        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        int r1=r.nextInt(12);
                        moles[r1].start();

                        time--;
                        timeText.setText(String.valueOf(time));

                        if(time<=0){
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,0,1000);
    }
    public void tapMole(View v){
        String tag_str=(String)v.getTag();
        int tag_int=Integer.parseInt(tag_str);

        score+=moles[tag_int].tapMole();

        scoreText.setText(String.valueOf(score));
    }
}
