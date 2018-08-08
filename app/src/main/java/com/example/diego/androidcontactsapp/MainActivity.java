package com.example.diego.androidcontactsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.cunoraz.gifview.library.GifView;
import com.example.diego.androidcontactsapp.Models.UserDataModel;

public class MainActivity extends AppCompatActivity {

    GifView ivSplash;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = findViewById(R.id.rl);
        ivSplash = findViewById(R.id.iv_splash);

        rl.setBackgroundColor(ContextCompat.getColor(this,R.color.lighblue));
    }

    @Override
    protected void onStart() {
        super.onStart();
        ivSplash.play();

        CountDownTimer myTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ivSplash.pause();
                finish();
                Intent intent = new Intent(MainActivity.this,ContactListActivity.class);
                startActivity(intent);
            }
        };
        myTimer.start();
    }
}
