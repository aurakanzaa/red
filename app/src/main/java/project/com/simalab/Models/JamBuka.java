package project.com.simalab.Models;

public class JamBuka {
    String id;
    String hari;
    String jam;

    public JamBuka() {
    }

    public JamBuka(String id, String hari, String jam) {
        this.id = id;
        this.hari = hari;
        this.jam = jam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
