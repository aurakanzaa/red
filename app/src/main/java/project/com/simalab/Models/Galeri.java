package project.com.simalab.Models;

import com.google.gson.annotations.SerializedName;

public class Galeri {

    @SerializedName("id")
    private String id;

    @SerializedName("album")
    private String album;

    @SerializedName("foto_awal")
    private String foto_awal;

    public Galeri() {
    }

    public Galeri(String id, String album, String foto_awal) {
        this.id = id;
        this.album = album;
        this.foto_awal = foto_awal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFoto_awal() {
        return foto_awal;
    }

    public void setFoto_awal(String foto_awal) {
        this.foto_awal = foto_awal;
    }
}
