package nayacode.studio.awascovid_19;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import nayacode.studio.awascovid_19.adapter.PreventionAdapter;
import nayacode.studio.awascovid_19.model.PreventionModel;

/**
 * Written by nayaCodeStudio
 */

public class PreventionActivity extends AppCompatActivity {
    private RecyclerView rvPrevention;
    private final ArrayList<PreventionModel> listPrevention = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rvPrevention = findViewById(R.id.rvPrevent);
        rvPrevention.setHasFixedSize(true);

        listPrevention.addAll(getListPrevention());
        showList();
    }

    private void showList() {
        rvPrevention.setLayoutManager(new LinearLayoutManager(this));
        PreventionAdapter preventionAdapter = new PreventionAdapter(listPrevention);
        rvPrevention.setAdapter(preventionAdapter);
    }

    private ArrayList<PreventionModel> getListPrevention() {
        String[] dataDesc = getResources().getStringArray(R.array.data_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_foto);

        ArrayList<PreventionModel> listPrevention = new ArrayList<>();
        for (int i = 0; i < dataDesc.length; i++) {
            PreventionModel preventionModel = new PreventionModel();
            preventionModel.setDescription(dataDesc[i]);
            preventionModel.setPhoto(dataPhoto.getResourceId(i, 0));
            listPrevention.add(preventionModel);
        }
        dataPhoto.recycle();
        return listPrevention;
    }
}