package nayacode.studio.awascovid_19;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nayacode.studio.awascovid_19.adapter.MyCustomAdapter;
import nayacode.studio.awascovid_19.model.CountryModel;

/**
 * Written by nayaCodeStudio
 */

public class CasesCountryActivity extends AppCompatActivity {
    EditText edtSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<CountryModel> countryModelsList = new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;
    LinearLayout errorInternet;
    Button btnRefresh, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases_country);
        Objects.requireNonNull(getSupportActionBar()).hide();

        edtSearch = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.loader);
        errorInternet = findViewById(R.id.error_internet);
        btnRefresh = findViewById(R.id.btn_refresh);
        btnBack = findViewById(R.id.btn_back);

//        Objects.requireNonNull(getSupportActionBar()).setTitle("List Negara Yang Terinfeksi");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchData();

        btnRefresh.setOnClickListener(v -> {
            Intent intentRefresh = new Intent(CasesCountryActivity.this, CasesCountryActivity.class);
            startActivity(intentRefresh);
            finish();
        });

        btnBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(CasesCountryActivity.this, MainMenuActivity.class);
            startActivity(intentBack);
            finish();
        });

        listView.setOnItemClickListener((parent, view, position, id) ->
                startActivity(new Intent(getApplicationContext(), DetailCasesCountryActivity.class).
                        putExtra("position", position)));

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/countries/";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        myCustomAdapter = new MyCustomAdapter(CasesCountryActivity.this,
                                countryModelsList);
                        listView.setAdapter(myCustomAdapter);
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String countryName = jsonObject.getString("country");
                            String cases = jsonObject.getString("cases");
                            String todayCases = jsonObject.getString("todayCases");
                            String deaths = jsonObject.getString("deaths");
                            String todayDeaths = jsonObject.getString("todayDeaths");
                            String recovered = jsonObject.getString("recovered");
                            String active = jsonObject.getString("active");
                            JSONObject object = jsonObject.getJSONObject("countryInfo");
                            String flagUrl = object.getString("flag");
                            countryModel = new CountryModel(flagUrl, countryName, cases, todayCases,
                                    deaths, todayDeaths, recovered, active);
                            countryModelsList.add(countryModel);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        simpleArcLoader.stop();
                        simpleArcLoader.setVisibility(View.GONE);
                    }
                },

                error -> {
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    errorInternet.setVisibility(View.VISIBLE);
//                    Toast.makeText(CasesCountryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(CasesCountryActivity.this, "Kendala koneksi!", Toast.LENGTH_SHORT).show();
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
