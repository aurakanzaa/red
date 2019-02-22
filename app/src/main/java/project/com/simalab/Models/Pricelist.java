package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class Pricelist {
    @SerializedName("id")
    private String id;

    @SerializedName("layanan")
    private String layanan;

    @SerializedName("harga")
    private String harga;

    @SerializedName("level")
    private String level;

    public Pricelist() {
    }

    public Pricelist(String id, String layanan, String harga, String level) {
        this.id = id;
        this.layanan = layanan;
        this.harga = harga;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
