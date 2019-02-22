package project.com.simalab.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import project.com.simalab.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailBeritaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailBeritaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailBeritaFragment extends Fragment {
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis, tvJudul;
    WebView wvKontenBerita;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailBeritaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailBeritaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailBeritaFragment newInstance(String param1, String param2) {
        DetailBeritaFragment fragment = new DetailBeritaFragment();
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
        View v = inflater.inflate(R.layout.fragment_detail_berita, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Berita");
        // Inisialisasi
        ivGambarBerita = (ImageView) v.findViewById(R.id.ivGambarBerita);
        tvTglTerbit = (TextView) v.findViewById(R.id.tvTglTerbit);
        tvJudul = (TextView) v.findViewById(R.id.tvJudul);
        tvPenulis = (TextView) v.findViewById(R.id.tvPenulis);
        wvKontenBerita = (WebView) v.findViewById(R.id.wvKontenBerita);

        // Jalankan method tampil detail berita
        showDetailBerita();

        return v;
    }

    private void showDetailBerita() {

        String judul_berita = getArguments().getString("JDL_BERITA");
        final String tanggal_berita = getArguments().getString("TGL_BERITA");
        final String penulis_berita = getArguments().getString("PNS_BERITA");
        final String isi_berita = getArguments().getString("ISI_BERITA");
        final String foto_berita = getArguments().getString("FTO_BERITA");
        // Set judul actionbar / toolbar
//        getSupportActionBar().setTitle(judul_berita);


        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tanggal_berita);
        tvJudul.setText(judul_berita);
        // Untuk gambar berita
        Picasso.with(getContext()).load(foto_berita).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
        wvKontenBerita.setBackgroundColor(Color.TRANSPARENT);
        String dataString = String.format(Locale.US, justifyTag, isi_berita);
        wvKontenBerita.loadData(dataString, "text/html; charset=utf-8", "UTF-8");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
