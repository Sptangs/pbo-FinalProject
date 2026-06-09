package models;

import java.time.LocalDate;

public class LowonganPrioritas extends LowonganPekerjaan {

    private String levelPrioritas;
    private int biayaPromosi;
    private boolean tampilDiAtas;

    public LowonganPrioritas(
            String perusahaanNama,
            String judul,
            String deskripsi,
            String kualifikasi,
            String lokasi,
            String jenis,
            int gajiMin,
            int gajiMax,
            LocalDate tanggalTutup,
            String levelPrioritas,
            int biayaPromosi,
            boolean tampilDiAtas) {

        super(
                perusahaanNama,
                judul,
                deskripsi,
                kualifikasi,
                lokasi,
                jenis,
                gajiMin,
                gajiMax,
                tanggalTutup);

        this.levelPrioritas = levelPrioritas;
        this.biayaPromosi = biayaPromosi;
        this.tampilDiAtas = tampilDiAtas;
    }


    public String getLevelPrioritas() {
        return levelPrioritas;
    }

    public void setLevelPrioritas(String levelPrioritas) {
        this.levelPrioritas = levelPrioritas;
    }

    public int getBiayaPromosi() {
        return biayaPromosi;
    }

    public void setBiayaPromosi(int biayaPromosi) {
        this.biayaPromosi = biayaPromosi;
    }

    public boolean isTampilDiAtas() {
        return tampilDiAtas;
    }

    public void setTampilDiAtas(boolean tampilDiAtas) {
        this.tampilDiAtas = tampilDiAtas;
    }

    @Override
    public String toString() {
        return "LowonganPrioritas{" +
                "judul='" + getJudul() + '\'' +
                ", perusahaan='" + getPerusahaanNama() + '\'' +
                ", levelPrioritas='" + levelPrioritas + '\'' +
                ", biayaPromosi=" + biayaPromosi +
                ", tampilDiAtas=" + tampilDiAtas +
                '}';
    }

    public String tampilkanDetail() {
        return "=== LOWONGAN PRIORITAS ==="
                + "\nJudul: " + getJudul()
                + "\nPerusahaan: " + getPerusahaanNama()
                + "\nLevel Prioritas: " + levelPrioritas
                + "\nBiaya Promosi: Rp" + biayaPromosi
                + "\nTampil Di Atas: " + tampilDiAtas;
    }
}