package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class Promo {
    @SerializedName("id")
    private String id;

    @SerializedName("nama_promo")
    private String nama_promo;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("tgl_mulai")
    private String tgl_mulai;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("foto_awal")
    private String foto_awal;

    public Promo() {
    }

    public Promo(String id, String nama_promo, String deskripsi, String tgl_mulai, String tgl_selesai, String foto_awal) {
        this.id = id;
        this.nama_promo = nama_promo;
        this.deskripsi = deskripsi;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.foto_awal = foto_awal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_promo() {
        return nama_promo;
    }

    public void setNama_promo(String nama_promo) {
        this.nama_promo = nama_promo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public String getFoto_awal() {
        return foto_awal;
    }

    public void setFoto_awal(String foto_awal) {
        this.foto_awal = foto_awal;
    }
}
