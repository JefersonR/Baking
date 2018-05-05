package br.udacity.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import br.udacity.R;
import br.udacity.utils.Constants;


public class SplashScreenActivity extends AppCompatActivity implements Constants {
    private ImageView logo;
    private ProgressBar progressBar;
    private final int animation_time = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setLayout();
        startProperties();

    }

    private void startProperties() {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        animation.setDuration(animation_time);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }, TIME_DELAY);
            }
        });

        logo.setAnimation(animation);
    }

    private void setLayout() {
        logo = (ImageView) findViewById(R.id.logo);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}
