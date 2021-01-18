package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class DetailCasesCountryActivity extends AppCompatActivity {
    TextView tvCountry, tvCases, tvRecovered, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cases_country);
        Intent intent = getIntent();
        int positionCountry = intent.getIntExtra("position", 0);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCasesCountry);
        tvRecovered = findViewById(R.id.tvRecoveredCountry);
        tvActive = findViewById(R.id.tvActiveCountry);
        tvTodayCases = findViewById(R.id.tvTodayCasesCountry);
        tvTotalDeaths = findViewById(R.id.tvDeathsCountry);
        tvTodayDeaths = findViewById(R.id.tvTodayDeathsCountry);

        tvCountry.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getRecovered());
        tvActive.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getActive());
        tvTodayCases.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(CasesCountryActivity.countryModelsList.get(positionCountry).getTodayDeaths());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
