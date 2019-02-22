package project.com.simalab.Models;

public class Sima {
    String id;
    String nama;
    String alamat;
    String no_telepon;
    String email;
    String website;
    String fb;
    String instagram;
    String twitter;
    String wa;
    String foto;

    public Sima(String id, String nama, String alamat, String no_telepon, String email, String website, String fb, String instagram, String twitter, String wa, String foto) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.no_telepon = no_telepon;
        this.email = email;
        this.website = website;
        this.fb = fb;
        this.instagram = instagram;
        this.twitter = twitter;
        this.wa = wa;
        this.foto = foto;

    }

    public Sima() {
    }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getNo_telepon() {
            return no_telepon;
        }

        public void setNo_telepon(String no_telepon) {
            this.no_telepon = no_telepon;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getFb() {
            return fb;
        }

        public void setFb(String fb) {
            this.fb = fb;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getWa() {
            return wa;
        }

        public void setWa(String wa) {
            this.wa = wa;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
}
