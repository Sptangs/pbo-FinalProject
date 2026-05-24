package models;

public class Perusahaan {

    private int idPerusahaan;
    private String namaPerusahaan;
    private String alamat;
    private String email;
    private String noTelepon;
    private String bidang;
    private String deskripsi;

    public Perusahaan() {
    }


    public Perusahaan(int idPerusahaan,
                      String namaPerusahaan,
                      String alamat,
                      String email,
                      String noTelepon,
                      String bidang,
                      String deskripsi) {

        setIdPerusahaan(idPerusahaan);
        setNamaPerusahaan(namaPerusahaan);
        setAlamat(alamat);
        setEmail(email);
        setNoTelepon(noTelepon);
        setBidang(bidang);
        setDeskripsi(deskripsi);
    }

    public int getIdPerusahaan() {
        return idPerusahaan;
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

    public void setIdPerusahaan(int idPerusahaan) {
        if (idPerusahaan <= 0) {
            throw new IllegalArgumentException("ID perusahaan harus lebih dari 0");
        }
        this.idPerusahaan = idPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        if (namaPerusahaan == null || namaPerusahaan.isEmpty()) {
            throw new IllegalArgumentException("Nama perusahaan tidak boleh kosong");
        }
        this.namaPerusahaan = namaPerusahaan;
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.isEmpty()) {
            throw new IllegalArgumentException("Alamat tidak boleh kosong");
        }
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Format email tidak valid");
        }
        this.email = email;
    }

    public void setNoTelepon(String noTelepon) {
        if (noTelepon == null || noTelepon.length() < 10) {
            throw new IllegalArgumentException("Nomor telepon tidak valid");
        }
        this.noTelepon = noTelepon;
    }

    public void setBidang(String bidang) {
        if (bidang == null || bidang.isEmpty()) {
            throw new IllegalArgumentException("Bidang perusahaan tidak boleh kosong");
        }
        this.bidang = bidang;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    // Method
    public void displayInfo() {
        System.out.println("===== COMPANY INFO =====");
        System.out.println("ID           : " + idPerusahaan);
        System.out.println("Name         : " + namaPerusahaan);
        System.out.println("Address      : " + alamat);
        System.out.println("Email        : " + email);
        System.out.println("Phone Number : " + noTelepon);
        System.out.println("Field        : " + bidang);
        System.out.println("Description  : " + deskripsi);
    }
}