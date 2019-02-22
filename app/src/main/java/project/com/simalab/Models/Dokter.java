package project.com.simalab.Models;

public class Dokter {

    String id, nama, spesialis ,foto;

    int harga;
    public Dokter(String id_dokter, String nama, String spesialis, String foto, int harga) {
        this.id = id_dokter;
        this.nama = nama;
        this.spesialis = spesialis;
        this.harga = harga;
        this.foto = foto;
    }

    public Dokter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }



    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
