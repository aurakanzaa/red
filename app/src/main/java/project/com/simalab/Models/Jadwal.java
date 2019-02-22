package project.com.simalab.Models;

public class Jadwal {
    String id, id_dokter, hari, jam;

    public Jadwal(String id, String id_dokter, String hari, String jam) {
        this.id = id;
        this.id_dokter = id_dokter;
        this.hari = hari;
        this.jam = jam;
    }

    public Jadwal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dokter() {
        return id_dokter;
    }

    public void listJadwal(String id_dokter) {
        this.id_dokter = id_dokter;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

}
