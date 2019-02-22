package project.com.simalab.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import project.com.simalab.Fragment.DetailGaleriFragment.OnListFragmentInteractionListener;

import project.com.simalab.Models.MDetailGaleri;
import project.com.simalab.R;

import java.util.List;

public class DetailGaleriAdapter extends RecyclerView.Adapter<DetailGaleriAdapter.ViewHolder> {

    private final List<MDetailGaleri> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public DetailGaleriAdapter(List<MDetailGaleri> items, OnListFragmentInteractionListener listener,Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detailgaleri, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/galeri/" + mValues.get(position).getNama_foto();
        Picasso.with(context).load(urlGambarBerita).into(holder.mIdView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        public MDetailGaleri mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.imgDetail);
        }
    }
}
