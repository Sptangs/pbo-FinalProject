package models;

import java.time.LocalDate;

public class LowonganPekerjaan extends Entitas {

    private String perusahaanNama;

    private String judul;
    private String deskripsi;
    private String kualifikasi;
    private String lokasi;
    private String jenis;

    private int gajiMin;
    private int gajiMax;

    private LocalDate tanggalPosting;
    private LocalDate tanggalTutup;

    private boolean aktif;

    public LowonganPekerjaan(String perusahaanNama,
                             String judul,
                             String deskripsi,
                             String kualifikasi,
                             String lokasi,
                             String jenis,
                             int gajiMin,
                             int gajiMax,
                             LocalDate tanggalTutup) {

        super();

        if (isEmpty(perusahaanNama)) {
            throw new IllegalArgumentException("Perusahaan nama tidak valid");
        }

        if (isEmpty(judul) || isEmpty(deskripsi) ||
            isEmpty(kualifikasi) || isEmpty(lokasi) ||
            isEmpty(jenis)) {
            throw new IllegalArgumentException("Data lowongan tidak boleh kosong");
        }

        if (gajiMin < 0 || gajiMax < 0) {
            throw new IllegalArgumentException("Gaji tidak boleh negatif");
        }

        if (gajiMax < gajiMin) {
            throw new IllegalArgumentException("Gaji maksimum harus lebih besar atau sama dengan gaji minimum");
        }

        if (tanggalTutup == null) {
            throw new IllegalArgumentException("Tanggal tutup tidak boleh kosong");
        }

        if (tanggalTutup.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal tutup tidak boleh di masa lalu");
        }

        this.perusahaanNama = perusahaanNama.trim();

        this.judul = judul.trim();
        this.deskripsi = deskripsi.trim();
        this.kualifikasi = kualifikasi.trim();
        this.lokasi = lokasi.trim();
        this.jenis = jenis.trim();

        this.gajiMin = gajiMin;
        this.gajiMax = gajiMax;

        this.tanggalPosting = LocalDate.now();
        this.tanggalTutup = tanggalTutup;

        this.aktif = true;
    }

    public LowonganPekerjaan(int id,
                             String perusahaanNama,
                             String judul,
                             String deskripsi,
                             String kualifikasi,
                             String lokasi,
                             String jenis,
                             int gajiMin,
                             int gajiMax,
                             LocalDate tanggalPosting,
                             LocalDate tanggalTutup,
                             boolean aktif) {

        super(id, true);

        this.perusahaanNama = perusahaanNama != null ? perusahaanNama.trim() : "";
        this.judul = judul != null ? judul.trim() : "";
        this.deskripsi = deskripsi != null ? deskripsi.trim() : "";
        this.kualifikasi = kualifikasi != null ? kualifikasi.trim() : "";
        this.lokasi = lokasi != null ? lokasi.trim() : "";
        this.jenis = jenis != null ? jenis.trim() : "";
        this.gajiMin = gajiMin;
        this.gajiMax = gajiMax;
        this.tanggalPosting = tanggalPosting != null ? tanggalPosting : LocalDate.now();
        this.tanggalTutup = tanggalTutup;
        this.aktif = aktif;
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    public String getPerusahaanNama() {
        return perusahaanNama;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKualifikasi() {
        return kualifikasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getJenis() {
        return jenis;
    }

    public int getGajiMin() {
        return gajiMin;
    }

    public int getGajiMax() {
        return gajiMax;
    }

    public LocalDate getTanggalPosting() {
        return tanggalPosting;
    }

    public LocalDate getTanggalTutup() {
        return tanggalTutup;
    }

    public boolean isAktif() {
        return aktif;
    }


    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public void setJudul(String judul) {
        if (isEmpty(judul)) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        this.judul = judul.trim();
    }

    public void setDeskripsi(String deskripsi) {
        if (isEmpty(deskripsi)) {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
        }
        this.deskripsi = deskripsi.trim();
    }

    public void setKualifikasi(String kualifikasi) {
        if (isEmpty(kualifikasi)) {
            throw new IllegalArgumentException("Kualifikasi tidak boleh kosong");
        }
        this.kualifikasi = kualifikasi.trim();
    }

    public void setLokasi(String lokasi) {
        if (isEmpty(lokasi)) {
            throw new IllegalArgumentException("Lokasi tidak boleh kosong");
        }
        this.lokasi = lokasi.trim();
    }

    public void setJenis(String jenis) {
        if (isEmpty(jenis)) {
            throw new IllegalArgumentException("Jenis tidak boleh kosong");
        }
        this.jenis = jenis.trim();
    }

    public void setGajiMin(int gajiMin) {
        if (gajiMin < 0) {
            throw new IllegalArgumentException("Gaji minimum tidak boleh negatif");
        }
        if (gajiMin > this.gajiMax) {
            throw new IllegalArgumentException("Gaji minimum tidak boleh lebih besar dari gaji maksimum");
        }
        this.gajiMin = gajiMin;
    }

    public void setGajiMax(int gajiMax) {
        if (gajiMax < 0) {
            throw new IllegalArgumentException("Gaji maksimum tidak boleh negatif");
        }
        if (gajiMax < this.gajiMin) {
            throw new IllegalArgumentException("Gaji maksimum tidak boleh lebih kecil dari gaji minimum");
        }
        this.gajiMax = gajiMax;
    }

    public void setTanggalTutup(LocalDate tanggalTutup) {
        if (tanggalTutup == null) {
            throw new IllegalArgumentException("Tanggal tutup tidak boleh kosong");
        }
        if (tanggalTutup.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal tutup tidak boleh di masa lalu");
        }
        this.tanggalTutup = tanggalTutup;
    }


    public boolean isTerbuka() {
        return aktif && !tanggalTutup.isBefore(LocalDate.now());
    }


    public void tutupLowongan() {
        this.aktif = false;
    }

    public void bukaKembali() {
        if (tanggalTutup.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tidak bisa membuka lowongan yang sudah melewati tanggal tutup");
        }
        this.aktif = true;
    }

    public void perpanjangTanggalTutup(LocalDate tanggalBaru) {
        if (tanggalBaru == null) {
            throw new IllegalArgumentException("Tanggal baru tidak boleh kosong");
        }
        if (tanggalBaru.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal baru tidak boleh di masa lalu");
        }
        if (tanggalBaru.isBefore(this.tanggalTutup)) {
            throw new IllegalArgumentException("Tanggal baru harus lebih lama dari tanggal tutup saat ini");
        }
        this.tanggalTutup = tanggalBaru;
    }

    public String tampilkanDetail() {
    return "Judul: " + judul
            + "\nPerusahaan: " + perusahaanNama
            + "\nLokasi: " + lokasi
            + "\nJenis: " + jenis;
}

    @Override
    public String toString() {
        return "LowonganPekerjaan{" +
                "id=" + getId() +
                ", perusahaanNama='" + perusahaanNama + '\'' +
                ", judul='" + judul + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", jenis='" + jenis + '\'' +
                ", gajiMin=" + gajiMin +
                ", gajiMax=" + gajiMax +
                ", tanggalTutup=" + tanggalTutup +
                ", aktif=" + aktif +
                '}';
    }
}