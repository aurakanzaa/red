package project.com.simalab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import project.com.simalab.R;
import java.util.List;

import project.com.simalab.JadwalActivity;
import project.com.simalab.Models.Dokter;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.ViewHolder> {
    private List<Dokter> dokterList;
    private Context context;

    public DokterAdapter(Context context, List<Dokter> dokterList) {
        this.dokterList = dokterList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dokter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Dokter listDokter = dokterList.get(i);
        viewHolder.nama.setText(listDokter.getNama());
        viewHolder.spesialis.setText(listDokter.getSpesialis());
        viewHolder.harga.setText(Integer.toString(listDokter.getHarga()));
        final String foto = "http://10.0.2.2/rest_sima/images/dokter/" + listDokter.getFoto();

//        Picasso.with(context).load(foto).into(viewHolder.img);
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(foto).fit().into(viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,listDokter.getId(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(),JadwalActivity.class);
                i.putExtra("id",listDokter.getId());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dokterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, spesialis, harga;
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView)itemView.findViewById(R.id.namaDokter);
            spesialis = (TextView)itemView.findViewById(R.id.spesialis);
            harga = (TextView)itemView.findViewById(R.id.harga);
            img = (ImageView) itemView.findViewById(R.id.foto);
        }
    }
}
