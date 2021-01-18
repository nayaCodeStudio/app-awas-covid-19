package nayacode.studio.awascovid_19;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}