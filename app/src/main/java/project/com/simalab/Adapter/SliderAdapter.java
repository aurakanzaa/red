package project.com.simalab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

//import project.com.simalab.DetailPromo;
import project.com.simalab.Fragment.DetailContactFragment;
import project.com.simalab.Fragment.DetailPromoFragment;
import project.com.simalab.Models.Promo;
import project.com.simalab.R;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<Promo> promo;
    String level;
    String id_user;

    public SliderAdapter(Context context, List<Promo> promo, String level, String id_user) {
        this.context = context;
        this.promo = promo;
        this.level = level;
        this.id_user = id_user;
    }

    @Override
    public int getCount() {
        return promo.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slide, null);

        ImageView imageView= (ImageView) view.findViewById(R.id.imgPromo);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/" + promo.get(position).getFoto_awal();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.with(context).load(urlGambarBerita).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id", promo.get(position).getId());
                bundle.putString("nama_promo",promo.get(position).getNama_promo());
                bundle.putString("deskripsi",promo.get(position).getDeskripsi());
                bundle.putString("tgl_mulai",promo.get(position).getTgl_mulai());
                bundle.putString("tgl_selesai",promo.get(position).getTgl_selesai());
                bundle.putString("foto_awal",promo.get(position).getFoto_awal());
                bundle.putString("level",level);
                bundle.putString("id_user",id_user);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                DetailPromoFragment detailPromoFragment= new DetailPromoFragment();
                detailPromoFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,detailPromoFragment).commit();
            }
        });

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
