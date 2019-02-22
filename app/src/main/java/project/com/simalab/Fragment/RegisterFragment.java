package project.com.simalab.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import java.util.Calendar;
import java.util.Locale;

import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.User;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    RequestServices getService;
    EditText TextUsername, TextPassword, editTextNama, editTextEmail,editTextTglLahir,editTextJenisKelamin,editTextNoTelp,editTextAlamat;
    Button buttonRegister,buttonLogin;
    FragmentManager fragmentManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Register");
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

        getService = ServiceGenerator.createService(RequestServices.class);

//        iKtp = findViewById(R.id.ktp);
        TextUsername = view.findViewById(R.id.editTextUsername);
        TextPassword = view.findViewById(R.id.editTextPassword);
        editTextNama = view.findViewById(R.id.editTextNama);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextTglLahir= view.findViewById(R.id.ediTextTglLahir);
        editTextJenisKelamin = view.findViewById(R.id.editTextJenisKelamin);
        editTextNoTelp = view.findViewById(R.id.editTextNoTelp);
        editTextAlamat = view.findViewById(R.id.editTextAlamat);
        buttonRegister = view.findViewById(R.id.buttonRegister);
        buttonLogin = view.findViewById(R.id.buttonLogin);

        editTextTglLahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<User> callMovie = getService.postRegister(TextUsername.getText().toString(),TextPassword.getText().toString(),editTextNama.getText().toString(),editTextEmail.getText().toString(),editTextTglLahir.getText().toString(),editTextJenisKelamin.getText().toString(),editTextNoTelp.getText().toString(),"ktp_",editTextAlamat.getText().toString(),"2");
                callMovie.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> callMovie, Response<User> response) {
                        Log.d("uname",TextUsername.getText().toString());
                        Toast.makeText(getContext(),TextUsername.getText().toString(),Toast.LENGTH_SHORT).show();
                        fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frame,new LoginFragment()).commit();
                        Toast.makeText(getContext(), "Register Berhasil. Silahkan Login", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame,new LoginFragment()).commit();
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Login");
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
