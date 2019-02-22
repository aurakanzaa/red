package project.com.simalab.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import project.com.simalab.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailPromoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailPromoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailPromoFragment extends Fragment {

    ImageView ivGambarBerita;
    TextView tvTglMulai, tvTglSelesai, tvJudul;
    WebView wvKontenPromo;
    Button BtnPesan, BtnHubungi;
    String level;
    String id_user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailPromoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailPromoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailPromoFragment newInstance(String param1, String param2) {
        DetailPromoFragment fragment = new DetailPromoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_promo, container, false);
        level = getArguments().getString("level");
        id_user= getArguments().getString("id_user");

        ivGambarBerita = (ImageView) view.findViewById(R.id.ivGambarPromo);
        tvTglMulai= (TextView) view.findViewById(R.id.tvTglMulai);
        tvJudul = (TextView) view.findViewById(R.id.tvJudul);
        tvTglSelesai = (TextView) view.findViewById(R.id.tvTglSelesai);
        wvKontenPromo= (WebView) view.findViewById(R.id.wvKontenPromo);
        BtnPesan = view.findViewById(R.id.btnPesan);
        BtnHubungi = view.findViewById(R.id.btnHubungi);

        if (level == null){
            BtnPesan.setVisibility(View.INVISIBLE);
        }
        else{
            BtnPesan.setVisibility(View.VISIBLE);
        }

        showDetailPromo();

        return view;
    }

    private void showDetailPromo() {
        // Tangkap data dari intent
        String nama_promo= getArguments().getString("nama_promo");
        String tgl_selesai = getArguments().getString("tgl_selesai");
        String tgl_mulai= getArguments().getString("tgl_mulai");
        String deskripsi= getArguments().getString("deskripsi");
        String foto_awal = getArguments().getString("foto_awal");
        final String id= getArguments().getString("id");
        // Set judul actionbar / toolbar
//        getSupportActionBar().setTitle(judul_berita);


        // Set ke widget
        tvTglMulai.setText("Tanggal Mulai : " + tgl_mulai);
        tvTglSelesai.setText("Tanggal Selesai : " + tgl_selesai);
        tvJudul.setText(nama_promo);
        // Untuk gambar berita
        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/" + foto_awal;
        Picasso.with(getContext()).load(urlGambarBerita).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenPromo.getSettings().setJavaScriptEnabled(true);
        String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
        wvKontenPromo.setBackgroundColor(Color.TRANSPARENT);
        String dataString = String.format(Locale.US, justifyTag, deskripsi);
        wvKontenPromo.loadData(dataString, "text/html; charset=utf-8", "UTF-8");

        BtnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("level",level);
                bundle.putString("id_promo",id);
                bundle.putString("id_user",id_user);

                FragmentManager manager = getFragmentManager();
                BookingPromoFragment bookingPromoFragment= new BookingPromoFragment();
                bookingPromoFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,bookingPromoFragment).commit();
            }
        });

        BtnHubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame,new ContactFragment()).commit();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
