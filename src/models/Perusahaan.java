package models;

public class Perusahaan extends Entitas {

    private String namaPerusahaan;
    private String alamat;
    private String email;
    private String noTelepon;
    private String bidang;
    private String deskripsi;

    public Perusahaan(String namaPerusahaan,
                      String alamat,
                      String email,
                      String noTelepon,
                      String bidang,
                      String deskripsi) {

        super();

        setNamaPerusahaan(namaPerusahaan);
        setAlamat(alamat);
        setEmail(email);
        setNoTelepon(noTelepon);
        setBidang(bidang);
        setDeskripsi(deskripsi);
    }

    public Perusahaan(int id,
                      String namaPerusahaan,
                      String alamat,
                      String email,
                      String noTelepon,
                      String bidang,
                      String deskripsi) {

        super(id, true);

        this.namaPerusahaan = namaPerusahaan;
        this.alamat = alamat;
        this.email = email;
        this.noTelepon = noTelepon;
        this.bidang = bidang;
        this.deskripsi = deskripsi;
    }


    public int getIdPerusahaan() {
        return getId();
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getBidang() {
        return bidang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }


    public void setNamaPerusahaan(String namaPerusahaan) {
        if (namaPerusahaan == null || namaPerusahaan.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong!");
        }
        this.namaPerusahaan = namaPerusahaan;
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat tidak boleh kosong!");
        }
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email tidak valid!");
        }
        this.email = email;
    }

    public void setNoTelepon(String noTelepon) {
        if (noTelepon == null || !noTelepon.matches("\\d+")) {
            throw new IllegalArgumentException("Nomor telepon hanya angka!");
        }
        if (noTelepon.length() < 10) {
            throw new IllegalArgumentException("Minimal 10 digit!");
        }
        this.noTelepon = noTelepon;
    }

    public void setBidang(String bidang) {
        if (bidang == null || bidang.trim().isEmpty()) {
            throw new IllegalArgumentException("Bidang tidak boleh kosong!");
        }
        this.bidang = bidang;
    }

    public void setDeskripsi(String deskripsi) {
        if (deskripsi == null || deskripsi.trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong!");
        }
        this.deskripsi = deskripsi;
    }
}