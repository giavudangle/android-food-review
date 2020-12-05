package com.example.android_food_review;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChaoMungActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.welcome_activity);
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ChaoMungActivity.this,MainActivity.class);
                ChaoMungActivity.this.startActivity(mainIntent);
                ChaoMungActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
