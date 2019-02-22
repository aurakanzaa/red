package project.com.simalab.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import project.com.simalab.R;
import java.util.List;

import project.com.simalab.Models.Jadwal;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private List<Jadwal> jadwalList;
    private Context context;

    public JadwalAdapter(Context context, List<Jadwal> jadwalList) {
        this.jadwalList = jadwalList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_galeri, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Jadwal listJadwal = jadwalList.get(i);
        viewHolder.title.setText(listJadwal.getHari());
//        final String urlGambarBerita = "http://10.0.2.2/rest_sima/jadwal/" + listJadwal.getFoto_awal();
//        // Set image ke widget dengna menggunakan Library Piccasso
//        // krena imagenya dari internet
//        Picasso.with(context).load(urlGambarBerita).into(viewHolder.img);
//        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        //Picasso.with(context).load(jadwalList.get(i).getImage_ID()).resize(240, 120).into(viewHolder.img);
//        viewHolder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(),DetailGaleri.class);
//                i.putExtra("id",listJadwal.getId());
//                v.getContext().startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return jadwalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
