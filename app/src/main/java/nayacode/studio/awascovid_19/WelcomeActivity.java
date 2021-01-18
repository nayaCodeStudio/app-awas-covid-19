package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnNext = findViewById(R.id.btn_next);

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });
    }
}