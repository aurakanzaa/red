package project.com.simalab.Fragment;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import project.com.simalab.Adapter.JamBukaAdapter;
import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.JamBuka;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailContactFragment extends Fragment {

    TextView Nama, Alamat, No_telepon, Email, Website, Facebook, Instagram, Twitter, Whatsapp;
    Button BtnDirection;
    ImageView imageView;
    RequestServices gotService;

    private RecyclerView recyclerView;
    private JamBukaAdapter mAdapter;
    private List<JamBuka> jamBukas = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailContactFragment newInstance(String param1, String param2) {
        DetailContactFragment fragment = new DetailContactFragment();
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
        final View v = inflater.inflate(R.layout.fragment_detail_contact, container, false);
        gotService = ServiceGenerator.createService(RequestServices.class);

        String nama = getArguments().getString("nama");
        final String alamat = getArguments().getString("alamat");
        final String no_telepon = getArguments().getString("no_telepon");
        final String email = getArguments().getString("email");
        final String website = getArguments().getString("website");
        final String facebook = getArguments().getString("facebook");
        final String instagram = getArguments().getString("instagram");
        final String twitter = getArguments().getString("twitter");
        final String whatsapp = getArguments().getString("whatsapp");
        final String foto = getArguments().getString("foto");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(nama);

        Nama = v.findViewById(R.id.nama);
        Alamat = v.findViewById(R.id.alamat);
        No_telepon = v.findViewById(R.id.no_telepon);
        Email = v.findViewById(R.id.email);
        Website = v.findViewById(R.id.website);
        Facebook = v.findViewById(R.id.facebook);
        Instagram = v.findViewById(R.id.instagram);
        Twitter = v.findViewById(R.id.twitter);
        Whatsapp = v.findViewById(R.id.whatsapp);
        BtnDirection = v.findViewById(R.id.btnDirection);
        imageView = v.findViewById(R.id.imgSima);

        final String urlGambarBerita = "http://dwdigital.id/rest_sima/images/" + foto;
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(getContext()).load(urlGambarBerita).into(imageView);

        recyclerView = (RecyclerView) v.findViewById(R.id.rvJamBuka);
        mAdapter = new JamBukaAdapter(getContext(),jamBukas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Call<List<JamBuka>> callMovie = gotService.getJamBuka();
        callMovie.enqueue(new Callback<List<JamBuka>>() {
            @Override
            public void onResponse(Call<List<JamBuka>> callMovie, Response<List<JamBuka>> response) {
                Log.d("SOKO", response.body().toString());
                jamBukas.clear();
                jamBukas.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<JamBuka>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });

        Nama.setText(nama);
        Alamat.setText(alamat);
        No_telepon.setText(no_telepon);
        Email.setText(email);
        Website.setText(website);
        Whatsapp.setText(whatsapp);

        Alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q="+alamat+""));
                startActivity(intent);
            }
        });

        No_telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = no_telepon;
                String dial = "tel:" + phoneNo;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        Whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PackageManager packageManager = ;
                Intent i = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://api.whatsapp.com/send?phone="+ whatsapp +"&text=" + URLEncoder.encode("", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
//                    if (i.resolveActivity(packageManager) != null) {
                    startActivity(i);
//                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",email, null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Tanya SIMA");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });


        Website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.item_link);
                dialog.show();
                Button openLink = dialog.findViewById(R.id.BtnOpenLink);
                Button copyLink = dialog.findViewById(R.id.BtnCopyLink);
                Button cancel = dialog.findViewById(R.id.BtnCancel);
                openLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Uri uri = Uri.parse(website); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                copyLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",website);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(),"Link copied to Clipboard",Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.item_link);
                dialog.show();
                Button openLink = dialog.findViewById(R.id.BtnOpenLink);
                Button copyLink = dialog.findViewById(R.id.BtnCopyLink);
                Button cancel = dialog.findViewById(R.id.BtnCancel);
                openLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Uri uri = Uri.parse(facebook); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                copyLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",facebook);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(),"Link copied to Clipboard",Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.item_link);
                dialog.show();
                Button openLink = dialog.findViewById(R.id.BtnOpenLink);
                Button copyLink = dialog.findViewById(R.id.BtnCopyLink);
                Button cancel = dialog.findViewById(R.id.BtnCancel);
                openLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Uri uri = Uri.parse(instagram); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                copyLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",instagram);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(),"Link copied to Clipboard",Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.item_link);
                dialog.show();
                Button openLink = dialog.findViewById(R.id.BtnOpenLink);
                Button copyLink = dialog.findViewById(R.id.BtnCopyLink);
                Button cancel = dialog.findViewById(R.id.BtnCancel);
                openLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Uri uri = Uri.parse(twitter); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                copyLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label",twitter);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(),"Link copied to Clipboard",Toast.LENGTH_SHORT).show();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        BtnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+alamat));
                startActivity(intent);
            }
        });

        return v;
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
