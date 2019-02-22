package project.com.simalab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import project.com.simalab.Adapter.SliderAdapter;
import project.com.simalab.Fragment.AboutFragment;
import project.com.simalab.Fragment.CariDokterFragment;
import project.com.simalab.Fragment.ContactFragment;
import project.com.simalab.Fragment.FasilitasFragment;
import project.com.simalab.Fragment.GaleriFragment;
//import project.com.simalab.Fragment.KtpFragment;
import project.com.simalab.Fragment.LoginFragment;
import project.com.simalab.Fragment.LoginNewFragment;
import project.com.simalab.Fragment.PromoFragment;
import project.com.simalab.Generator.ServiceGenerator;
import project.com.simalab.Models.Berita;
import project.com.simalab.Models.Promo;
import project.com.simalab.Services.RequestServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import project.com.simalab.Adapter.BeritaAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Integer> color;
    List<String> colorName;
    ViewPager viewPager;
    TabLayout indicator;
    private RecyclerView recyclerView;
    RequestServices gotService;
    List<Berita> beritaList= new ArrayList<>();
    List<Promo> promolist= new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    BeritaAdapter beritaAdapter;

    private FragmentManager fragmentManager;
    String level,id_user,namanama;
    NavigationView navigationView;
    Button BtnRegistrasi, BtnProfil, BtnRekamMedik, BtnHasil, BtnFasilitas, BtnGaleri, BtnHomeService, BtnHubungiKami;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        level = getIntent().getStringExtra("level");
        id_user = getIntent().getStringExtra("id_user");
        namanama = getIntent().getStringExtra("nama");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("level",level);
                i.putExtra("id_user",id_user);
                i.putExtra("nama",namanama);
                startActivity(i);
            }
        });

        BtnRegistrasi = findViewById(R.id.buttonRegistrasi);
        BtnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        BtnProfil = findViewById(R.id.buttonProfil);
        BtnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame,new AboutFragment()).commit();
                getSupportActionBar().setTitle("About");
            }
        });
        BtnRekamMedik = findViewById(R.id.buttonRekamMedik);
        BtnRekamMedik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        BtnHasil = findViewById(R.id.buttonHasil);
        BtnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        BtnFasilitas = findViewById(R.id.buttonFasilitas);
        BtnFasilitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("level",level);
                bundle.putString("id_user",id_user);
                FragmentManager manager = getSupportFragmentManager();
                FasilitasFragment promoFragment= new FasilitasFragment();
                promoFragment.setArguments(bundle);
                manager.beginTransaction().replace(R.id.frame,promoFragment).commit();
                getSupportActionBar().setTitle("Fasilitas");
            }
        });
        BtnGaleri = findViewById(R.id.buttonGaleri);
        BtnGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame,new GaleriFragment()).commit();
                getSupportActionBar().setTitle("Galeri");
            }
        });
        BtnHomeService = findViewById(R.id.buttonHomeService);
        BtnHomeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        BtnHubungiKami = findViewById(R.id.buttonHubungi);
        BtnHubungiKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame,new ContactFragment()).commit();
                getSupportActionBar().setTitle("Contact");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (level == null){
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_about).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_contact).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_fasilitas).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_galeri).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_pricelist).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_promo).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);

//            /            ini tambahan test
//            navigationView.getMenu().findItem(R.id.nav_booking).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_login2).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_reg).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_ktp).setVisible(false);
        }
        if  (level == "2"){
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_about).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_contact).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_fasilitas).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_galeri).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_pricelist).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_promo).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);

//            ini tambahan test
//            navigationView.getMenu().findItem(R.id.nav_booking).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_login2).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_reg).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_ktp).setVisible(false);
        }

        View viewHeader = navigationView.getHeaderView(0);
        TextView nama = viewHeader.findViewById(R.id.nama);
        if (namanama == null){
            nama.setText("SIMA APP");
        }
        else{
            nama.setText(namanama);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (TabLayout) findViewById(R.id.indicator);
        gotService = ServiceGenerator.createService(RequestServices.class);

        Call<List<Promo>> callPromo = gotService.getPromo();
        callPromo.enqueue(new Callback<List<Promo>>() {
            @Override
            public void onResponse(Call<List<Promo>> callPromo, Response<List<Promo>> response) {
                Log.d("SOKO", response.body().toString());
                promolist.clear();
                promolist.addAll(response.body());
                viewPager.setAdapter(new SliderAdapter(getApplicationContext(), promolist,level,id_user));
                indicator.setupWithViewPager(viewPager, true);
            }
            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 3000, 3000);

        reloadData();
        beritaAdapter = new BeritaAdapter(getApplicationContext(), beritaList);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(beritaAdapter);
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < promolist.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void reloadData(){
        Call<List<Berita>> callMovie = gotService.request_show_all_berita();
        callMovie.enqueue(new Callback<List<Berita>>() {
            @Override
            public void onResponse(Call<List<Berita>> callMovie, Response<List<Berita>> response) {
                Log.d("SOKO", response.body().toString());
                beritaList.clear();
                beritaList.addAll(response.body());
                beritaAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Berita>> call, Throwable t) {
                Log.d("SOKO", "GAGAL");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_login){
//            Intent i = new Intent(getApplicationContext(),Login.class);
//            startActivity(i);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new LoginFragment()).commit();
            getSupportActionBar().setTitle("Login");
        }
        else if (id == R.id.nav_about) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new AboutFragment()).commit();
            getSupportActionBar().setTitle("About");
        }else if(id == R.id.nav_contact){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new ContactFragment()).commit();
            getSupportActionBar().setTitle("Contact");
        }
        else if(id == R.id.nav_galeri){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new GaleriFragment()).commit();
            getSupportActionBar().setTitle("Galeri");
        }
        else if(id == R.id.nav_fasilitas){
            Bundle bundle=new Bundle();
            bundle.putString("level",level);
            bundle.putString("id_user",id_user);
            FragmentManager manager = getSupportFragmentManager();
            FasilitasFragment promoFragment= new FasilitasFragment();
            promoFragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.frame,promoFragment).commit();
            getSupportActionBar().setTitle("Fasilitas");
        }
        else if(id == R.id.nav_pricelist){
//            Intent i = new Intent(getApplicationContext(),PricelistActivity.class);
//            i.putExtra("level",level);
//            startActivity(i);
        }
        else if(id == R.id.nav_promo){
            Bundle bundle=new Bundle();
            bundle.putString("level",level);
            bundle.putString("id_user",id_user);
            FragmentManager manager = getSupportFragmentManager();
            PromoFragment promoFragment= new PromoFragment();
            promoFragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.frame,promoFragment).commit();
        }
        else if(id == R.id.nav_logout){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else if(id == R.id.nav_booking){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new CariDokterFragment()).commit();
            getSupportActionBar().setTitle("Booking Dokter");
        }
        else if(id == R.id.nav_ktp){
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.frame,new KtpActivity()).commit();
//            getSupportActionBar().setTitle("KTP");
            Intent i = new Intent(getApplicationContext(),KtpActivity.class);
            startActivity(i);
        }
        else if(id == R.id.nav_login_new){
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame,new LoginNewFragment()).commit();
            getSupportActionBar().setTitle("Login");
        }
//        else if(id == R.id.nav_reg_new){
//            fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.frame,new RegNewFragment()).commit();
//            getSupportActionBar().setTitle("Login");
//        }
        else if(id == R.id.nav_dokter){
            Intent i = new Intent(getApplicationContext(),DokterActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
