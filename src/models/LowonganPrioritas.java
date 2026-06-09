package models;

import java.time.LocalDate;

public class LowonganPrioritas extends LowonganPekerjaan {
    private int prioritas;

    //konstruktor untuk lowongan baru 
    public LowonganPrioritas(String perusahaanNama, String judul, String deskripsi, 
                             String kualifikasi, String lokasi, String jenis, 
                             int gajiMin, int gajiMax, LocalDate tanggalTutup, int prioritas) {
        super(perusahaanNama, judul, deskripsi, kualifikasi, lokasi, jenis, gajiMin, gajiMax, tanggalTutup);
        setPrioritas(prioritas);
    }

    //konstruktor untuk loading dari file
    public LowonganPrioritas(int id, String perusahaanNama, String judul, String deskripsi, 
                             String kualifikasi, String lokasi, String jenis, 
                             int gajiMin, int gajiMax, LocalDate tanggalPosting, 
                             LocalDate tanggalTutup, boolean aktif, int prioritas) {
        super(id, perusahaanNama, judul, deskripsi, kualifikasi, lokasi, jenis, 
              gajiMin, gajiMax, tanggalPosting, tanggalTutup, aktif);
        setPrioritas(prioritas);
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        if (prioritas < 1 || prioritas > 3) {
            throw new IllegalArgumentException("Prioritas harus antara 1 - 3!");
        }
        this.prioritas = prioritas;
    }

    @Override
    public String toString() {
        return super.toString() + ", Prioritas: " + prioritas;
    }
}