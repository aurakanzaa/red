package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class Fasilitas {
    @SerializedName("id")
    private String id;

    @SerializedName("judul")
    private String judul;

    @SerializedName("foto_awal")
    private String foto_awal;

    @SerializedName("Deskripsi")
    private String Deskripsi;

    public Fasilitas() {
    }

    public Fasilitas(String id, String judul, String foto_awal, String deskripsi) {
        this.id = id;
        this.judul = judul;
        this.foto_awal = foto_awal;
        this.Deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getFoto_awal() {
        return foto_awal;
    }

    public void setFoto_awal(String foto_awal) {
        this.foto_awal = foto_awal;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.Deskripsi = deskripsi;
    }
}
