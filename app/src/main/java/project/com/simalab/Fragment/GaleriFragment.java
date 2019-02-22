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
import project.com.simalab.Models.Galeri;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class GaleriFragment extends Fragment {

    private List<Galeri> galeriList = new ArrayList<>();
    RequestServices gotService;
    private GaleriAdapter mAdapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private GaleriFragment.OnListFragmentInteractionListener mListener;


    public GaleriFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static GaleriFragment newInstance(int columnCount) {
        GaleriFragment fragment = new GaleriFragment();
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
        View view = inflater.inflate(R.layout.fragment_galeri, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Galeri");
        gotService = ServiceGenerator.createService(RequestServices.class);

        // Set the adapter
//        if (view instanceof RecyclerView) {
        mAdapter = new GaleriAdapter(galeriList,mListener,getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        Call<List<Galeri>> callMovie = gotService.getGaleri();
        callMovie.enqueue(new Callback<List<Galeri>>() {
            @Override
            public void onResponse(Call<List<Galeri>> callMovie, Response<List<Galeri>> response) {
                Log.d("SOKO", response.body().toString());
                galeriList.clear();
                galeriList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Galeri>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new GaleriAdapter(DummyContent.ITEMS, mListener));
//        }
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
//        void onListFragmentInteraction(DummyItem item);
    }
}
