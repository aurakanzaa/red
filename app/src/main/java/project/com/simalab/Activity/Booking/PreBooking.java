package project.com.simalab.Activity.Booking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.com.simalab.Adapter.DokterAdapter;
import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.Dokter;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.com.simalab.R;
public class PreBooking extends AppCompatActivity {
    private RecyclerView recyclerView;
    RequestServices gotService;
    List<Dokter> dokterList = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    DokterAdapter galeriAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().setTitle("Dokter");

        gotService = ServiceGenerator.createService(RequestServices.class);
        reloadData();
        galeriAdapter = new DokterAdapter(getApplicationContext(), dokterList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvDok);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(galeriAdapter);
    }

    private void reloadData(){
        Call<List<Dokter>> callMovie = gotService.getDokter();
        callMovie.enqueue(new Callback<List<Dokter>>() {
            @Override
            public void onResponse(Call<List<Dokter>> callMovie, Response<List<Dokter>> response) {
                Log.d("SOKO", response.body().toString());
                dokterList.clear();
                dokterList.addAll(response.body());
                galeriAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Dokter>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });
    }

}
