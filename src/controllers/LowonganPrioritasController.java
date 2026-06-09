package controllers;

import models.LowonganPrioritas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LowonganPrioritasController {

    private static final String FILE_NAME = "data/lowongan.txt";

    private final List<LowonganPrioritas> lowonganList;

    public LowonganPrioritasController() {
        lowonganList = new ArrayList<>();
        loadData();
    }

    // ==========================
    // CREATE
    // ==========================
    public boolean tambahLowonganPrioritas(
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
            boolean tampilDiAtas
    ) {

        try {

            // VALIDASI SEDERHANA (penting untuk dosen)
            if (perusahaanNama.isEmpty() ||
                    judul.isEmpty() ||
                    levelPrioritas.isEmpty()) {
                return false;
            }

            if (gajiMin > gajiMax) {
                return false;
            }

            LowonganPrioritas lp = new LowonganPrioritas(
                    perusahaanNama,
                    judul,
                    deskripsi,
                    kualifikasi,
                    lokasi,
                    jenis,
                    gajiMin,
                    gajiMax,
                    tanggalTutup,
                    levelPrioritas,
                    biayaPromosi,
                    tampilDiAtas
            );

            lowonganList.add(lp);
            saveData();

            return true;

        } catch (Exception e) {
            System.out.println("Error controller: " + e.getMessage());
            return false;
        }
    }

    // ==========================
    // READ ALL
    // ==========================
    public List<LowonganPrioritas> getLowonganPrioritas() {
        return lowonganList;
    }

    // ==========================
    // FILTER (GUI + CLI bisa pakai)
    // ==========================
    public List<LowonganPrioritas> filterPrioritasTinggi() {

        List<LowonganPrioritas> hasil = new ArrayList<>();

        for (LowonganPrioritas lp : lowonganList) {
            if (lp.getLevelPrioritas() != null &&
                    lp.getLevelPrioritas().equalsIgnoreCase("TINGGI")) {
                hasil.add(lp);
            }
        }

        return hasil;
    }

    // ==========================
    // SAVE FILE
    // ==========================
    private void saveData() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (LowonganPrioritas lp : lowonganList) {

                bw.write(
                        lp.getPerusahaanNama() + ";" +
                                lp.getJudul() + ";" +
                                lp.getDeskripsi() + ";" +
                                lp.getKualifikasi() + ";" +
                                lp.getLokasi() + ";" +
                                lp.getJenis() + ";" +
                                lp.getGajiMin() + ";" +
                                lp.getGajiMax() + ";" +
                                lp.getTanggalTutup() + ";" +
                                lp.getLevelPrioritas() + ";" +
                                lp.getBiayaPromosi() + ";" +
                                lp.isTampilDiAtas()
                );

                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(";");

                if (data.length == 12) {

                    LowonganPrioritas lp = new LowonganPrioritas(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            Integer.parseInt(data[6]),
                            Integer.parseInt(data[7]),
                            LocalDate.parse(data[8]),
                            data[9],
                            Integer.parseInt(data[10]),
                            Boolean.parseBoolean(data[11])
                    );

                    lowonganList.add(lp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}