package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Written by nayaCodeStudio
 */

public class CasesGlobalActivity extends AppCompatActivity {
    TextView tvCases, tvRecovered, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    LinearLayout linearLayout, errorInternet;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_global);

        tvCases = findViewById(R.id.tvCasesGlobal);
        tvRecovered = findViewById(R.id.tvRecoveredGlobal);
        tvActive = findViewById(R.id.tvActiveGlobal);
        tvTodayCases = findViewById(R.id.tvTodayCasesGlobal);
        tvTotalDeaths = findViewById(R.id.tvTotalDeathsGlobal);
        tvTodayDeaths = findViewById(R.id.tvTodayDeathsGlobal);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountriesGlobal);
        simpleArcLoader = findViewById(R.id.loader);
        linearLayout = findViewById(R.id.dataCovidGlobal);
        errorInternet = findViewById(R.id.error_internet);
        btnRefresh = findViewById(R.id.btn_refresh);

        Objects.requireNonNull(getSupportActionBar()).hide();
        fetchData();

        btnRefresh.setOnClickListener(v -> {
            Intent intent = new Intent(CasesGlobalActivity.this, CasesGlobalActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        tvCases.setText(jsonObject.getString("cases"));
                        tvRecovered.setText(jsonObject.getString("recovered"));
                        tvActive.setText(jsonObject.getString("active"));
                        tvTodayCases.setText(jsonObject.getString("todayCases"));
                        tvTotalDeaths.setText(jsonObject.getString("deaths"));
                        tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                        tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                },

                error -> {
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);
                    errorInternet.setVisibility(View.VISIBLE);
//                    Toast.makeText(CasesGlobalActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(CasesGlobalActivity.this, "Kendala koneksi!", Toast.LENGTH_SHORT).show();
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
