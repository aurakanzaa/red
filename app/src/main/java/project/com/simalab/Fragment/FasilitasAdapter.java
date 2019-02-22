package project.com.simalab.Fragment;

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

import project.com.simalab.Fragment.FasilitasFragment.OnListFragmentInteractionListener;
import project.com.simalab.Models.Fasilitas;
import project.com.simalab.R;

import java.util.List;


public class FasilitasAdapter extends RecyclerView.Adapter<FasilitasAdapter.ViewHolder> {

    private final List<Fasilitas> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;
    private final String level;
    private final String id_user;

    public FasilitasAdapter(List<Fasilitas> items, OnListFragmentInteractionListener listener, Context context, String level, String id_user) {
        mValues = items;
        mListener = listener;
        this.context = context;
        this.level = level;
        this.id_user = id_user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fasilitas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getJudul());
        final String urlGambar = "http://dwdigital.id/rest_sima/images/fasilitas/" + mValues.get(position).getFoto_awal();

        Picasso.with(context).load(urlGambar).into(holder.mImage);
        holder.mView.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Bundle bundle = new Bundle();
                bundle.putString("id", mValues.get(position).getId());
                bundle.putString("judul", mValues.get(position).getJudul());
                bundle.putString("foto_awal", mValues.get(position).getFoto_awal());
                bundle.putString("deskripsi", mValues.get(position).getDeskripsi());


                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                DetailFasilitasFragment detailFasilitasFragment= new DetailFasilitasFragment();
                detailFasilitasFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame, detailFasilitasFragment).commit();
             }
            });
        }
    @Override
    public int getItemCount() { return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImage;
        public final TextView mContentView;
        public Fasilitas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.img);
            mContentView = (TextView) view.findViewById(R.id.judulFasilitas);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
