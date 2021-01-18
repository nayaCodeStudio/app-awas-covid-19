package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnGlobal = findViewById(R.id.btn_global);
        Button btnCountry = findViewById(R.id.btn_country);
        Button btnPrevention = findViewById(R.id.btn_prevention);
        Button btnAbout = findViewById(R.id.btn_about);
        LottieAnimationView lottieGlobal = findViewById(R.id.lottie_global);
        LottieAnimationView lottieCountry = findViewById(R.id.lottie_country);
        LottieAnimationView lottieAbout = findViewById(R.id.lottie_about);
        LottieAnimationView lottiePrevention = findViewById(R.id.lottie_prevention);

        lottieGlobal.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CasesGlobalActivity.class);
            startActivity(intent);
        });

        btnGlobal.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CasesGlobalActivity.class);
            startActivity(intent);
        });

        lottieCountry.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CasesCountryActivity.class);
            startActivity(intent);
        });

        btnCountry.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, CasesCountryActivity.class);
            startActivity(intent);
        });

        lottiePrevention.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, PreventionActivity.class);
            startActivity(intent);
        });

        btnPrevention.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, PreventionActivity.class);
            startActivity(intent);
        });

        lottieAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, AboutAppActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, AboutAppActivity.class);
            startActivity(intent);
        });
    }
}