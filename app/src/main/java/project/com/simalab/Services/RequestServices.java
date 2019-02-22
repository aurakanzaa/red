package project.com.simalab.Services;

import java.util.List;

import project.com.simalab.Models.Berita;
import project.com.simalab.Models.BookingPromo;
import project.com.simalab.Models.Dokter;
import project.com.simalab.Models.Fasilitas;
import project.com.simalab.Models.Galeri;
import project.com.simalab.Models.Jadwal;
import project.com.simalab.Models.JamBuka;
import project.com.simalab.Models.MDetailGaleri;
import project.com.simalab.Models.Pricelist;
import project.com.simalab.Models.Promo;
import project.com.simalab.Models.Sima;
import project.com.simalab.Models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestServices {
    @GET("Login")
    Call<List<User>> getLogin();

    @FormUrlEncoded
    @POST("Login/index_get")
    Call<User> postRegister(@Field("username") String username,
                            @Field("password") String password,
                            @Field("nama") String nama,
                            @Field("email") String email,
                            @Field("tgl_lahir") String tgl_lahir,
                            @Field("jenis_kelamin") String jenis_kelamin,
                            @Field("no_telp") String no_telp,
                            @Field("foto_ktp") String foto_ktp,
                            @Field("alamat") String alamat,
                            @Field("level") String level);

    @GET("Berita/index_get")
    Call<List<Berita>> request_show_all_berita();

    @GET("Galeri/index_get")
    Call<List<Galeri>> getGaleri();

    @GET("Promo/index_get")
    Call<List<Promo>> getPromo();

    @GET("Fasilitas/index_get")
    Call<List<Fasilitas>> getFasilitas();

//    @FormUrlEncoded
    @GET("FotoGaleri/{fk_galeri}")
    Call<List<MDetailGaleri>> getDetailGaleri(@Path("fk_galeri") String fk_galeri);

    @GET("Contact/index_get")
    Call<List<Sima>> getContact();

    @GET("JamBuka/index_get")
    Call<List<JamBuka>> getJamBuka();

    @GET("Pricelist/{level}")
    Call<List<Pricelist>> getPricelist(@Path("level") String level);

    @GET("UserById/{id_user}")
    Call<List<User>> getUserById(@Path("id_user") String id_user);

    @GET("Jadwal_dokter/{id_dokter}")
    Call<List<Jadwal>> getJadwal(@Path("id_dokter") String id_jadwal);

    @GET("Dokter/index_get")
    Call<List<Dokter>> getDokter();

    @FormUrlEncoded
    @POST("Promo/index_get")
    Call<BookingPromo> postPromo(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jenis_kelamin") String jenis_kelamin,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat,
            @Field("fk_promo") String fk_promo);
}
