package project.com.simalab.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import project.com.simalab.Fragment.DetailBeritaFragment;
import project.com.simalab.MainActivity;
import project.com.simalab.Models.Berita;
import project.com.simalab.R;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolder> {

    Context context;
    List<Berita> berita;
    FragmentManager manager;

    public BeritaAdapter(Context context, List<Berita> berita) {
        this.context = context;
        this.berita = berita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, viewGroup, false);

        // Hubungkan dengan MyViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.tvJudul.setText(berita.get(position).getJudulBerita());
        viewHolder.tvIsiBerita.setText(berita.get(position).getIsiBerita());

        // Dapatkan url gambar
        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/" + berita.get(position).getFoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarBerita).into(viewHolder.ivGambarBerita);

        // Event klik ketika item list nya di klik
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("JDL_BERITA", berita.get(position).getJudulBerita());
                bundle.putString("TGL_BERITA", berita.get(position).getTanggalPosting());
                bundle.putString("PNS_BERITA", berita.get(position).getPenulis());
                bundle.putString("FTO_BERITA", urlGambarBerita);
                bundle.putString("ISI_BERITA", berita.get(position).getIsiBerita());

//                if (context instanceof MainActivity) {
//
//                    MainActivity myActivity = (MainActivity)context;
//
//                    manager  = myActivity.getSupportFragmentManager();
//                }
                manager  = ((MainActivity) context).getSupportFragmentManager();
//                = ((Application)view.getContext()).getSupportFragmentManager();
                DetailBeritaFragment detailBeritaFragment= new DetailBeritaFragment();
                detailBeritaFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,detailBeritaFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGambarBerita;
        TextView tvJudul, tvIsiBerita;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulBerita);
            tvIsiBerita = (TextView) itemView.findViewById(R.id.tvIsiBerita);
        }
    }
}
