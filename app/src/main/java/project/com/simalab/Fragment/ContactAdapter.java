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

import project.com.simalab.Fragment.ContactFragment.OnListFragmentInteractionListener;
import project.com.simalab.Models.Sima;
import project.com.simalab.R;

import java.util.List;
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private final List<Sima> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public ContactAdapter(List<Sima> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getNama());
        final String urlGambar = "http://dwdigital.id/rest_sima/images/" + mValues.get(position).getFoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambar).into(holder.mImage);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("id", mValues.get(position).getId());
                bundle.putString("nama", mValues.get(position).getNama());
                bundle.putString("alamat", mValues.get(position).getAlamat());
                bundle.putString("no_telepon", mValues.get(position).getNo_telepon());
                bundle.putString("email", mValues.get(position).getEmail());
                bundle.putString("website", mValues.get(position).getWebsite());
                bundle.putString("facebook", mValues.get(position).getFb());
                bundle.putString("instagram", mValues.get(position).getInstagram());
                bundle.putString("twitter", mValues.get(position).getTwitter());
                bundle.putString("whatsapp", mValues.get(position).getWa());
                bundle.putString("foto", mValues.get(position).getFoto());
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                DetailContactFragment detailContactFragment = new DetailContactFragment();
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
        public Sima mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImage = (ImageView) view.findViewById(R.id.image);
            mContentView = (TextView) view.findViewById(R.id.nama);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
