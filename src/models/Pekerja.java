package models;

public class Pekerja extends Entitas {

    private String nama;
    private int umur;
    private String email;
    private String noTelepon;
    private String alamat;
    private String keahlian;

    public Pekerja(int idPekerja,
            String nama,
            int umur,
            String email,
            String noTelepon,
            String alamat,
            String keahlian) {

        super();

        setNama(nama);
        setUmur(umur);
        setEmail(email);
        setNoTelepon(noTelepon);
        setAlamat(alamat);
        setKeahlian(keahlian);
    }

    public int getIdPekerja() {
        return getId();
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public String getEmail() {
        return email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("[DEBUG] Nama kosong");
        }
        this.nama = nama.trim();
    }

    public void setUmur(int umur) {
        if (umur < 17 || umur > 70) {
            throw new IllegalArgumentException("[DEBUG] Umur harus 17-70");
        }
        this.umur = umur;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("[DEBUG] Email tidak valid");
        }
        this.email = email.trim();
    }

    public void setNoTelepon(String noTelepon) {
        if (noTelepon == null || !noTelepon.matches("[0-9]+")) {
            throw new IllegalArgumentException("[DEBUG] Telepon hanya angka");
        }
        this.noTelepon = noTelepon;
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("[DEBUG] Alamat kosong");
        }
        this.alamat = alamat.trim();
    }

    public void setKeahlian(String keahlian) {
        if (keahlian == null || keahlian.trim().isEmpty()) {
            throw new IllegalArgumentException("[DEBUG] Keahlian kosong");
        }
        this.keahlian = keahlian.trim();
    }

    public String getInfo() {
        return "Pekerja: " + nama +
                " | Keahlian: " + keahlian;
    }
}