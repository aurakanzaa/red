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

import project.com.simalab.Fragment.GaleriFragment.OnListFragmentInteractionListener;
import project.com.simalab.Models.Galeri;
import project.com.simalab.R;

import java.util.List;

public class GaleriAdapter extends RecyclerView.Adapter<GaleriAdapter.ViewHolder> {

    private final List<Galeri> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public GaleriAdapter(List<Galeri> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_galeri, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getAlbum());
        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/" + mValues.get(position).getFoto_awal();
        Picasso.with(context).load(urlGambarBerita).into(holder.mIdView);
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//        //Picasso.with(context).load(galleryList.get(i).getImage_ID()).resize(240, 120).into(viewHolder.img);
//
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(v.getContext(),DetailGaleri.class);
////                i.putExtra("id",listGaleri.getId());
////                v.getContext().startActivity(i);
//                }
//            }
//        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id", mValues.get(position).getId());
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                DetailGaleriFragment detailGaleriFragment = new DetailGaleriFragment();
                detailGaleriFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,detailGaleriFragment).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public final TextView mContentView;
        public Galeri mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.img);
            mContentView = (TextView) view.findViewById(R.id.title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
