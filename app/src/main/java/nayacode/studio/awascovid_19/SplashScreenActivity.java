package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        int SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
    }
}