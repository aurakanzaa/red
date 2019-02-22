package project.com.simalab.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.com.simalab.Models.JamBuka;
import project.com.simalab.R;

public class JamBukaAdapter extends RecyclerView.Adapter<JamBukaAdapter.ViewHolder> {
    private Context context;
    private List<JamBuka> jamBukas;

    public JamBukaAdapter(Context context, List<JamBuka> jamBukas) {
        this.context = context;
        this.jamBukas = jamBukas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jam_buka,parent,false);
        return new JamBukaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final JamBuka listJam= jamBukas.get(position);
        holder.hari.setText(listJam.getHari());
        holder.jam.setText(listJam.getJam());
    }

    @Override
    public int getItemCount() {
        return jamBukas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hari,jam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            jam = itemView.findViewById(R.id.jam);
        }
    }
}
