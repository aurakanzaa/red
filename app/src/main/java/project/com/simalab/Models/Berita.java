package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("penulis")
    private String penulis;

    @SerializedName("foto")
    private String foto;

    @SerializedName("id")
    private String id;

    @SerializedName("judul_berita")
    private String judulBerita;

    @SerializedName("tanggal_posting")
    private String tanggalPosting;

    @SerializedName("isi_berita")
    private String isiBerita;

    public Berita(String penulis, String foto, String id, String judulBerita, String tanggalPosting, String isiBerita) {
        this.penulis = penulis;
        this.foto = foto;
        this.id = id;
        this.judulBerita = judulBerita;
        this.tanggalPosting = tanggalPosting;
        this.isiBerita = isiBerita;
    }

    public Berita() {
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getTanggalPosting() {
        return tanggalPosting;
    }

    public void setTanggalPosting(String tanggalPosting) {
        this.tanggalPosting = tanggalPosting;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }
}
