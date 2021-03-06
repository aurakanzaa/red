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

import project.com.simalab.Adapter.PromoAdapter;
import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.Promo;
import project.com.simalab.R;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class PromoFragment extends Fragment {

    private List<Promo> promoList = new ArrayList<>();
    RequestServices gotService;
    private PromoAdapter mAdapter;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public PromoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PromoFragment newInstance(int columnCount) {
        PromoFragment fragment = new PromoFragment();
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
        View view = inflater.inflate(R.layout.fragment_promo, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Promo");
        gotService = ServiceGenerator.createService(RequestServices.class);

        String level = getArguments().getString("level");
        String id_user = getArguments().getString("id_user");

        // Set the adapter
//        if (view instanceof RecyclerView) {
        mAdapter = new PromoAdapter(promoList,mListener,getContext(),level,id_user);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
//        }
        Call<List<Promo>> callMovie = gotService.getPromo();
        callMovie.enqueue(new Callback<List<Promo>>() {
            @Override
            public void onResponse(Call<List<Promo>> callMovie, Response<List<Promo>> response) {
                Log.d("SOKO", response.body().toString());
                promoList.clear();
                promoList.addAll(response.body());
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });

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
