package controllers;

import models.LowonganPekerjaan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LowonganPekerjaanController {

    private ArrayList<LowonganPekerjaan> list = new ArrayList<>();
    private String message;

    public LowonganPekerjaanController() {
        loadFromTxt();
    }

    public String getMessage() {
        return message;
    }

    // ================= UTIL =================
    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isNumber(String s) {
        return s != null && s.matches("\\d+");
    }

    // ================= LOAD =================
    private void loadFromTxt() {
        try {
            File file = new File("data/lowongan.txt");
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");

                if (d.length >= 11) {

                    list.add(new LowonganPekerjaan(
                            Integer.parseInt(d[0]),   // id
                            Integer.parseInt(d[1]),   // perusahaanId
                            d[2], // judul
                            d[3], // deskripsi
                            d[4], // kualifikasi
                            d[5], // lokasi
                            d[6], // jenis
                            Integer.parseInt(d[7]), // gajiMin
                            Integer.parseInt(d[8]), // gajiMax
                            LocalDate.parse(d[9]), // tanggalPosting
                            LocalDate.parse(d[10]) // tanggalTutup
                    ));
                }
            }

            br.close();

        } catch (Exception e) {
            message = "Gagal load lowongan";
        }
    }

    // ================= SAVE =================
    private void saveToTxt() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("data/lowongan.txt")
            );

            for (LowonganPekerjaan l : list) {

                bw.write(
                        l.getId() + "," +
                        l.getPerusahaanId() + "," +
                        l.getJudul() + "," +
                        l.getDeskripsi() + "," +
                        l.getKualifikasi() + "," +
                        l.getLokasi() + "," +
                        l.getJenis() + "," +
                        l.getGajiMin() + "," +
                        l.getGajiMax() + "," +
                        l.getTanggalPosting() + "," +
                        l.getTanggalTutup()
                );

                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            message = "Gagal save lowongan";
        }
    }

    // ================= GET ALL =================
    public ArrayList<LowonganPekerjaan> getAllLowongan() {
        return list;
    }

    public int getNextId() {
        if (list.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (LowonganPekerjaan l : list) {
            if (l.getId() > maxId) {
                maxId = l.getId();
            }
        }
        return maxId + 1;
    }

    // ================= FIND =================
    public LowonganPekerjaan getById(int id) {
        for (LowonganPekerjaan l : list) {
            if (l.getId() == id) return l;
        }
        message = "Lowongan tidak ditemukan";
        return null;
    }

    // ================= ADD =================
    public boolean addLowongan(int perusahaanId,
                               String judul,
                               String deskripsi,
                               String kualifikasi,
                               String lokasi,
                               String jenis,
                               String gajiMin,
                               String gajiMax,
                               String tanggalTutup) {

        try {
            if (perusahaanId <= 0 ||
                isEmpty(judul) || isEmpty(deskripsi) ||
                isEmpty(kualifikasi) || isEmpty(lokasi) ||
                isEmpty(jenis) || isEmpty(tanggalTutup) ||
                !isNumber(gajiMin) || !isNumber(gajiMax)) {

                message = "Data tidak valid";
                return false;
            }

            list.add(new LowonganPekerjaan(
                    perusahaanId,
                    judul,
                    deskripsi,
                    kualifikasi,
                    lokasi,
                    jenis,
                    Integer.parseInt(gajiMin),
                    Integer.parseInt(gajiMax),
                    LocalDate.parse(tanggalTutup)
            ));

            saveToTxt();
            message = "Berhasil tambah lowongan";
            return true;
        } catch (Exception e) {
            message = "ERROR ADD : " + e.getMessage();
            return false;
        }
    }

    // ================= EDIT =================
    public boolean editLowongan(int id,
                                String judul,
                                String deskripsi,
                                String kualifikasi,
                                String lokasi,
                                String jenis,
                                String gajiMin,
                                String gajiMax) {

        try {
            LowonganPekerjaan l = getById(id);
            if (l == null) {
                message = "Lowongan tidak ditemukan";
                return false;
            }

            if (isEmpty(judul) || isEmpty(deskripsi)) {
                message = "Data wajib diisi";
                return false;
            }

            if (!isNumber(gajiMin) || !isNumber(gajiMax)) {
                message = "Gaji harus berupa angka";
                return false;
            }

            l.setJudul(judul);
            l.setDeskripsi(deskripsi);
            l.setKualifikasi(kualifikasi);
            l.setLokasi(lokasi);
            l.setJenis(jenis);
            l.setGajiMin(Integer.parseInt(gajiMin));
            l.setGajiMax(Integer.parseInt(gajiMax));

            saveToTxt();
            message = "Berhasil update";
            return true;
        } catch (Exception e) {
            message = "ERROR EDIT : " + e.getMessage();
            return false;
        }
    }

    // ================= DELETE =================
    public boolean deleteLowongan(int id) {
        LowonganPekerjaan l = getById(id);
        if (l == null) return false;

        list.remove(l);
        saveToTxt();

        message = "Berhasil hapus";
        return true;
    }
}