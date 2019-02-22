package project.com.simalab.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.Sima;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactFragment extends Fragment {

    private List<Sima> simaList = new ArrayList<>();
    RequestServices gotService;
    private ContactAdapter mAdapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactFragment newInstance(int columnCount) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Contact");
        gotService = ServiceGenerator.createService(RequestServices.class);

        // Set the adapter
//        if (view instanceof RecyclerView) {
        mAdapter = new ContactAdapter(simaList,mListener,getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
//        }
        Call<List<Sima>> callMovie = gotService.getContact();
        callMovie.enqueue(new Callback<List<Sima>>() {
            @Override
            public void onResponse(Call<List<Sima>> callMovie, Response<List<Sima>> response) {
                Log.d("SOKO", response.body().toString());
                simaList.clear();
                simaList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Sima>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });

        return view;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Sima item);
    }
}
