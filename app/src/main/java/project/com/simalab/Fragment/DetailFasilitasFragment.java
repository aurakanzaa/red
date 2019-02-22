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
 * {@link DetailFasilitasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFasilitasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFasilitasFragment extends Fragment {
    ImageView ivGambar;
    TextView tvJudul;
    WebView wvKontenFasilitas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailFasilitasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFasilitasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFasilitasFragment newInstance(String param1, String param2) {
        DetailFasilitasFragment fragment = new DetailFasilitasFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail_fasilitas, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Fasilitas");

        ivGambar = (ImageView) view.findViewById(R.id.ivGambarFasilitas);
        tvJudul = (TextView) view.findViewById(R.id.tvJudul);
        wvKontenFasilitas= (WebView) view.findViewById(R.id.wvKontenFasilitas);

        // Jalankan method tampil detail berita
        showDetailFasilitas();
        return view;
    }

    private void showDetailFasilitas() {
        // Tangkap data dari intent
        String id = getArguments().getString("id");
        String judul = getArguments().getString("judul");
        String deskripsi = getArguments().getString("deskripsi");
        String foto_awal= getArguments().getString("foto_awal");
        // Set judul actionbar / toolbar
//        getSupportActionBar().setTitle(judul_berita);


        // Set ke widget
        tvJudul.setText(judul);
        // Untuk gambar berita
        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/fasilitas/" + foto_awal;
        Picasso.with(getContext()).load(urlGambarBerita).into(ivGambar);
        // Set isi berita sebagai html ke WebView
        wvKontenFasilitas.getSettings().setJavaScriptEnabled(true);
        String justifyTag = "<html><body style='text-align:justify;'>%s</body></html>";
        wvKontenFasilitas.setBackgroundColor(Color.TRANSPARENT);
        String dataString = String.format(Locale.US, justifyTag, deskripsi);
        wvKontenFasilitas.loadData(dataString, "text/html; charset=utf-8", "UTF-8");
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
