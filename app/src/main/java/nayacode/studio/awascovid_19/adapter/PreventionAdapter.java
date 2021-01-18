package nayacode.studio.awascovid_19.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import nayacode.studio.awascovid_19.R;
import nayacode.studio.awascovid_19.model.PreventionModel;

/**
 * Written by nayaCodeStudio
 */

public class PreventionAdapter extends RecyclerView.Adapter<PreventionAdapter.CardViewViewHolder> {
    private final ArrayList<PreventionModel> listPrevention;

    public PreventionAdapter(ArrayList<PreventionModel> listPrevention) {
        this.listPrevention = listPrevention;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_prevention,
                parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        PreventionModel preventionModel = listPrevention.get(position);
        Glide.with(holder.itemView.getContext())
                .load(preventionModel.getPhoto())
                .apply(new RequestOptions().override(50, 50))
                .into(holder.imgPhoto);

        holder.tvDesc.setText(preventionModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return listPrevention.size();
    }
    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPhoto;
        private final TextView tvDesc;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPhoto = itemView.findViewById(R.id.imagePrevention);
            this.tvDesc = itemView.findViewById(R.id.titlePrevention);
        }
    }
}
