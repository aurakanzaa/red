package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class MDetailGaleri {
    @SerializedName("fk_galeri")
    private String fk_galeri;

    @SerializedName("nama_foto")
    private String nama_foto;

    public MDetailGaleri(String fk_galeri, String nama_foto) {
        this.fk_galeri = fk_galeri;
        this.nama_foto = nama_foto;
    }

    public String getFk_galeri() {
        return fk_galeri;
    }

    public void setFk_galeri(String fk_galeri) {
        this.fk_galeri = fk_galeri;
    }

    public String getNama_foto() {
        return nama_foto;
    }

    public void setNama_foto(String nama_foto) {
        this.nama_foto = nama_foto;
    }
}
