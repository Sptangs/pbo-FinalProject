package controllers;

import models.LowonganPekerjaan;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class LowonganPekerjaanController {

    private List<LowonganPekerjaan> lowonganList;
    private String message;
    private int nextId;

    public LowonganPekerjaanController() {
        lowonganList = new ArrayList<>();
        message = "";
        nextId = 1;
    }

    public String getMessage() {
        return message;
    }

    public List<LowonganPekerjaan> getAllLowongan() {
        return new ArrayList<>(lowonganList);
    }

    public List<Object[]> getLowonganDataForTable() {
        List<Object[]> dataList = new ArrayList<>();
        
        for (LowonganPekerjaan l : lowonganList) {
            dataList.add(new Object[]{
                l.getId(),
                l.getPerusahaanNama(),
                l.getJudul(),
                l.getDeskripsi(),
                l.getKualifikasi(),
                l.getLokasi(),
                l.getJenis(),
                l.getGajiMin(),
                l.getGajiMax(),
                l.getTanggalTutup()
            });
        }
        
        return dataList;
    }

    public Object[] getLowonganById(int id) {
        for (LowonganPekerjaan l : lowonganList) {
            if (l.getId() == id) {
                return new Object[]{
                    l.getId(),
                    l.getPerusahaanNama(),
                    l.getJudul(),
                    l.getDeskripsi(),
                    l.getKualifikasi(),
                    l.getLokasi(),
                    l.getJenis(),
                    l.getGajiMin(),
                    l.getGajiMax(),
                    l.getTanggalTutup()
                };
            }
        }
        return null;
    }

    public boolean addLowongan(String perusahaanNama,
                               String judul,
                               String deskripsi,
                               String kualifikasi,
                               String lokasi,
                               String jenis,
                               String gajiMinStr,
                               String gajiMaxStr,
                               String tanggalTutupStr) {
        try {
            if (perusahaanNama == null || perusahaanNama.trim().isEmpty()) {
                message = "Nama perusahaan tidak boleh kosong!";
                return false;
            }

            if (judul == null || judul.trim().isEmpty()) {
                message = "Judul tidak boleh kosong!";
                return false;
            }

            if (deskripsi == null || deskripsi.trim().isEmpty()) {
                message = "Deskripsi tidak boleh kosong!";
                return false;
            }

            if (kualifikasi == null || kualifikasi.trim().isEmpty()) {
                message = "Kualifikasi tidak boleh kosong!";
                return false;
            }

            if (lokasi == null || lokasi.trim().isEmpty()) {
                message = "Lokasi tidak boleh kosong!";
                return false;
            }

            if (jenis == null || jenis.trim().isEmpty()) {
                message = "Jenis tidak boleh kosong!";
                return false;
            }

            if (gajiMinStr == null || gajiMinStr.trim().isEmpty()) {
                message = "Gaji minimum tidak boleh kosong!";
                return false;
            }

            if (gajiMaxStr == null || gajiMaxStr.trim().isEmpty()) {
                message = "Gaji maksimum tidak boleh kosong!";
                return false;
            }

            int gajiMin, gajiMax;
            try {
                gajiMin = Integer.parseInt(gajiMinStr.trim());
                gajiMax = Integer.parseInt(gajiMaxStr.trim());
            } catch (NumberFormatException e) {
                message = "Gaji harus berupa angka!";
                return false;
            }

            if (gajiMin < 0) {
                message = "Gaji minimum tidak boleh negatif!";
                return false;
            }

            if (gajiMax < 0) {
                message = "Gaji maksimum tidak boleh negatif!";
                return false;
            }

            if (gajiMax < gajiMin) {
                message = "Gaji maksimum harus lebih besar atau sama dengan gaji minimum!";
                return false;
            }

            if (tanggalTutupStr == null || tanggalTutupStr.trim().isEmpty()) {
                message = "Tanggal tutup tidak boleh kosong!";
                return false;
            }

            LocalDate tanggalTutup;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                tanggalTutup = LocalDate.parse(tanggalTutupStr.trim(), formatter);
            } catch (DateTimeParseException e) {
                message = "Format tanggal tidak valid! Gunakan format yyyy-MM-dd";
                return false;
            }

            if (tanggalTutup.isBefore(LocalDate.now())) {
                message = "Tanggal tutup tidak boleh di masa lalu!";
                return false;
            }

            LowonganPekerjaan lowongan = new LowonganPekerjaan(
                    perusahaanNama,
                    judul,
                    deskripsi,
                    kualifikasi,
                    lokasi,
                    jenis,
                    gajiMin,
                    gajiMax,
                    tanggalTutup
            );


            lowonganList.add(lowongan);

            message = "Lowongan berhasil ditambahkan!";
            return true;

        } catch (IllegalArgumentException e) {
            message = e.getMessage();
            return false;
        } catch (Exception e) {
            message = "Terjadi kesalahan: " + e.getMessage();
            return false;
        }
    }

    public boolean editLowongan(int id,
                                String judul,
                                String deskripsi,
                                String kualifikasi,
                                String lokasi,
                                String jenis,
                                String gajiMinStr,
                                String gajiMaxStr) {
        try {
            LowonganPekerjaan lowongan = null;
            for (LowonganPekerjaan l : lowonganList) {
                if (l.getId() == id) {
                    lowongan = l;
                    break;
                }
            }

            if (lowongan == null) {
                message = "Lowongan tidak ditemukan!";
                return false;
            }

            if (judul == null || judul.trim().isEmpty()) {
                message = "Judul tidak boleh kosong!";
                return false;
            }

            if (deskripsi == null || deskripsi.trim().isEmpty()) {
                message = "Deskripsi tidak boleh kosong!";
                return false;
            }

            if (kualifikasi == null || kualifikasi.trim().isEmpty()) {
                message = "Kualifikasi tidak boleh kosong!";
                return false;
            }

            if (lokasi == null || lokasi.trim().isEmpty()) {
                message = "Lokasi tidak boleh kosong!";
                return false;
            }

            if (jenis == null || jenis.trim().isEmpty()) {
                message = "Jenis tidak boleh kosong!";
                return false;
            }

            if (gajiMinStr == null || gajiMinStr.trim().isEmpty()) {
                message = "Gaji minimum tidak boleh kosong!";
                return false;
            }

            if (gajiMaxStr == null || gajiMaxStr.trim().isEmpty()) {
                message = "Gaji maksimum tidak boleh kosong!";
                return false;
            }

            int gajiMin, gajiMax;
            try {
                gajiMin = Integer.parseInt(gajiMinStr.trim());
                gajiMax = Integer.parseInt(gajiMaxStr.trim());
            } catch (NumberFormatException e) {
                message = "Gaji harus berupa angka!";
                return false;
            }

            if (gajiMin < 0) {
                message = "Gaji minimum tidak boleh negatif!";
                return false;
            }

            if (gajiMax < 0) {
                message = "Gaji maksimum tidak boleh negatif!";
                return false;
            }

            if (gajiMax < gajiMin) {
                message = "Gaji maksimum harus lebih besar atau sama dengan gaji minimum!";
                return false;
            }

            lowongan.setJudul(judul);
            lowongan.setDeskripsi(deskripsi);
            lowongan.setKualifikasi(kualifikasi);
            lowongan.setLokasi(lokasi);
            lowongan.setJenis(jenis);
            lowongan.setGajiMin(gajiMin);
            lowongan.setGajiMax(gajiMax);

            message = "Lowongan berhasil diupdate!";
            return true;

        } catch (Exception e) {
            message = "Terjadi kesalahan: " + e.getMessage();
            return false;
        }
    }

    public boolean deleteLowongan(int id) {
        try {
            LowonganPekerjaan toRemove = null;
            for (LowonganPekerjaan l : lowonganList) {
                if (l.getId() == id) {
                    toRemove = l;
                    break;
                }
            }

            if (toRemove == null) {
                message = "Lowongan tidak ditemukan!";
                return false;
            }

            lowonganList.remove(toRemove);
            message = "Lowongan berhasil dihapus!";
            return true;

        } catch (Exception e) {
            message = "Terjadi kesalahan: " + e.getMessage();
            return false;
        }
    }
}