package com.example.applemart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        img = findViewById(R.id.img);
        txt = (TextView) findViewById(R.id.txt);
        Animation ani = AnimationUtils.loadAnimation(this,R.anim.tranform_splash);
        img.setAnimation(ani);
        txt.setAnimation(ani);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }
}