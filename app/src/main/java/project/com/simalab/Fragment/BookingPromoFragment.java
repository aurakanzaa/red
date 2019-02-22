package project.com.simalab.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.MainActivity;
import project.com.simalab.Models.BookingPromo;
import project.com.simalab.Models.User;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookingPromoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookingPromoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingPromoFragment extends Fragment {

    RequestServices gotService;
    List<User> users= new ArrayList<>();
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    String id_promo, level, id_user;
    EditText editTextNama, editTextEmail,editTextTglLahir,editTextJenisKelamin,editTextNoTelp,editTextAlamat;
    Button buttonPesan;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BookingPromoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingPromoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingPromoFragment newInstance(String param1, String param2) {
        BookingPromoFragment fragment = new BookingPromoFragment();
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
        View view = inflater.inflate(R.layout.fragment_booking_promo, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Promo");
        gotService = ServiceGenerator.createService(RequestServices.class);

        id_promo = getArguments().getString("id_promo");
        level = getArguments().getString("level");
        id_user = getArguments().getString("id_user");

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        Call<List<User>> callMovie = gotService.getUserById(id_user);
        callMovie.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> callMovie, Response<List<User>> response) {
                Log.d("SOKO", response.body().toString());
                users.clear();
                users.addAll(response.body());
                editTextNama.setText(response.body().get(0).getNama());
                editTextEmail.setText(response.body().get(0).getEmail());
                editTextTglLahir.setText(response.body().get(0).getTgl_lahir());
                editTextJenisKelamin.setText(response.body().get(0).getJenis_kelamin());
                editTextNoTelp.setText(response.body().get(0).getNo_telp());
                editTextAlamat.setText(response.body().get(0).getAlamat());
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });

        editTextNama = view.findViewById(R.id.editTextNama);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextTglLahir= view.findViewById(R.id.ediTextTglLahir);
        editTextJenisKelamin = view.findViewById(R.id.editTextJenisKelamin);
        editTextNoTelp = view.findViewById(R.id.editTextNoTelp);
        editTextAlamat = view.findViewById(R.id.editTextAlamat);
        buttonPesan = view.findViewById(R.id.buttonPesan);

        editTextTglLahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<BookingPromo> callMovie = gotService.postPromo(editTextNama.getText().toString(),editTextEmail.getText().toString(),editTextTglLahir.getText().toString(),editTextJenisKelamin.getText().toString(),editTextNoTelp.getText().toString(),editTextAlamat.getText().toString(),id_promo);
                callMovie.enqueue(new Callback<BookingPromo>() {
                    @Override
                    public void onResponse(Call<BookingPromo> callMovie, Response<BookingPromo> response) {
                        Intent i = new Intent(getContext(), MainActivity.class);
                        i.putExtra("level",level);
                        i.putExtra("id_user",id_user);
                        startActivity(i);
                        Toast.makeText(getContext(), "Pesan Promo Berhasil", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<BookingPromo> call, Throwable t) {
                        Toast.makeText(getContext(), "Pesan Promo Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextTglLahir.setText(sdf.format(myCalendar.getTime()));
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
