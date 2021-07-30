package com.example.recyclermusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    ImageView logo,splashImg;
    TextView appName;

    LottieAnimationView lottieAnimationView;


    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.appName);
        lottieAnimationView = findViewById(R.id.lottie);


        //splashImg.animate().translationY(3000).setDuration(4500).setStartDelay(6000);
        logo.animate().translationY(3000).setDuration(4500).setStartDelay(6000);
        appName.animate().translationY(3000).setDuration(4500).setStartDelay(6000);
        lottieAnimationView.animate().translationY(3000).setDuration(4500).setStartDelay(6000);

        Thread myThered=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(5000);


                    Intent i=new Intent(SplashScreen.this,RegisterActivity.class);
                    startActivity(i);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        myThered.start();
    }


}