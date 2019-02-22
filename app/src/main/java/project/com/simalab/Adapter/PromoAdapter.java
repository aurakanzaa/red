package project.com.simalab.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import project.com.simalab.Fragment.DetailPromoFragment;
import project.com.simalab.Fragment.PromoFragment.OnListFragmentInteractionListener;
import project.com.simalab.Models.Promo;
import project.com.simalab.R;

import java.util.List;


public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> {

    private final List<Promo> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;
    private final String level;
    private final String id_user;

//    private final List<DummyItem> mValues;
//    private final OnListFragmentInteractionListener mListener;

    public PromoAdapter(List<Promo> items, OnListFragmentInteractionListener listener, Context context, String level, String id_user) {
        mValues = items;
        mListener = listener;
        this.context = context;
        this.level = level;
        this.id_user = id_user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getNama_promo());
        final String urlGambar = "http://dwdigital.id/rest_sima/images/" + mValues.get(position).getFoto_awal();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambar).into(holder.mImage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("id", mValues.get(position).getId());
                bundle.putString("nama_promo",mValues.get(position).getNama_promo());
                bundle.putString("deskripsi",mValues.get(position).getDeskripsi());
                bundle.putString("tgl_mulai",mValues.get(position).getTgl_mulai());
                bundle.putString("tgl_selesai",mValues.get(position).getTgl_selesai());
                bundle.putString("foto_awal",mValues.get(position).getFoto_awal());
                bundle.putString("level",level);
                bundle.putString("id_user",id_user);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                DetailPromoFragment detailContactFragment = new DetailPromoFragment();
                detailContactFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,detailContactFragment).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mContentView;
        public Promo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage= (ImageView) view.findViewById(R.id.img);
            mContentView = (TextView) view.findViewById(R.id.judulPromo);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
