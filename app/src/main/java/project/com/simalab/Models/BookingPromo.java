package project.com.simalab.Models;

public class BookingPromo {
    String nama;
    String email;
    String tgl_lahir;
    String jenis_kelamin;
    String no_telp;
    String alamat;
    String fk_promo;

    public BookingPromo(String nama, String email, String tgl_lahir, String jenis_kelamin, String no_telp, String alamat, String fk_promo) {
        this.nama = nama;
        this.email = email;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.fk_promo = fk_promo;
    }

    public BookingPromo() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFk_promo() {
        return fk_promo;
    }

    public void setFk_promo(String fk_promo) {
        this.fk_promo = fk_promo;
    }
}
