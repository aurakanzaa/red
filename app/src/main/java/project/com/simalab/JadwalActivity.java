package project.com.simalab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.com.simalab.Adapter.JadwalAdapter;
import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.Jadwal;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RequestServices gotService;
    List<Jadwal> jadwalList= new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    JadwalAdapter galeriAdapter;
    String idDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        getSupportActionBar().setTitle("Jadwal");

        Intent i = getIntent();
        idDokter = i.getStringExtra("id");
        gotService = ServiceGenerator.createService(RequestServices.class);
        reloadData();
        galeriAdapter = new JadwalAdapter(getApplicationContext(), jadwalList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvJadwal);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(galeriAdapter);
    }

    private void reloadData(){
        Call<List<Jadwal>> callMovie = gotService.getJadwal(idDokter);
        callMovie.enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> callMovie, Response<List<Jadwal>> response) {
                Log.d("SOKO", response.body().toString());
                jadwalList.clear();
                jadwalList.addAll(response.body());
                galeriAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });
    }
}
