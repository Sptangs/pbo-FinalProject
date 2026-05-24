package models;

import java.time.LocalDate;

public class LowonganPekerjaan extends Entitas {

    private int perusahaanId;

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

    public LowonganPekerjaan(int perusahaanId,
                             String judul,
                             String deskripsi,
                             String kualifikasi,
                             String lokasi,
                             String jenis,
                             int gajiMin,
                             int gajiMax,
                             LocalDate tanggalTutup) {

        super();

        if (perusahaanId <= 0) {
            throw new IllegalArgumentException("Perusahaan ID tidak valid");
        }

        if (isEmpty(judul) || isEmpty(deskripsi) ||
            isEmpty(kualifikasi) || isEmpty(lokasi) ||
            isEmpty(jenis)) {
            throw new IllegalArgumentException("Data lowongan tidak boleh kosong");
        }

        if (gajiMin < 0 || gajiMax < 0 || gajiMax < gajiMin) {
            throw new IllegalArgumentException("Range gaji tidak valid");
        }

        this.perusahaanId = perusahaanId;

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
                             int perusahaanId,
                             String judul,
                             String deskripsi,
                             String kualifikasi,
                             String lokasi,
                             String jenis,
                             int gajiMin,
                             int gajiMax,
                             LocalDate tanggalPosting,
                             LocalDate tanggalTutup) {

        super(id, true);

        this.perusahaanId = perusahaanId;
        this.judul = judul.trim();
        this.deskripsi = deskripsi.trim();
        this.kualifikasi = kualifikasi.trim();
        this.lokasi = lokasi.trim();
        this.jenis = jenis.trim();
        this.gajiMin = gajiMin;
        this.gajiMax = gajiMax;
        this.tanggalPosting = tanggalPosting;
        this.tanggalTutup = tanggalTutup;
        this.aktif = true;
    }

    // ================= UTIL =================
    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // ================= GETTER =================

    public int getPerusahaanId() {
        return perusahaanId;
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
        if (!isEmpty(judul)) {
            this.judul = judul.trim();
        }
    }

    public void setDeskripsi(String deskripsi) {
        if (!isEmpty(deskripsi)) {
            this.deskripsi = deskripsi.trim();
        }
    }

    public void setKualifikasi(String kualifikasi) {
        if (!isEmpty(kualifikasi)) {
            this.kualifikasi = kualifikasi.trim();
        }
    }

    public void setLokasi(String lokasi) {
        if (!isEmpty(lokasi)) {
            this.lokasi = lokasi.trim();
        }
    }

    public void setJenis(String jenis) {
        if (!isEmpty(jenis)) {
            this.jenis = jenis.trim();
        }
    }

    public void setGajiMin(int gajiMin) {
        if (gajiMin >= 0) {
            this.gajiMin = gajiMin;
        }
    }

    public void setGajiMax(int gajiMax) {
        if (gajiMax >= 0 && gajiMax >= this.gajiMin) {
            this.gajiMax = gajiMax;
        }
    }
}