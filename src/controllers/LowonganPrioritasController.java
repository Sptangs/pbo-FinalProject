package controllers;

import models.LowonganPekerjaan;
import models.LowonganPrioritas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LowonganPrioritasController {

    private static final String FILE_NAME =
            "data/lowongan.txt";

    private List<LowonganPekerjaan> lowonganList;

    public LowonganPrioritasController() {

        lowonganList = new ArrayList<>();

        loadData();
    }

    // ==========================
    // CREATE
    // ==========================

    public void tambahLowonganPrioritas(
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

        LowonganPrioritas lowongan =
                new LowonganPrioritas(
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

        lowonganList.add(lowongan);

        saveData();
    }
    public List<LowonganPekerjaan> getAllData() {
        return lowonganList;
    }


    public List<LowonganPrioritas> getLowonganPrioritas() {

        List<LowonganPrioritas> hasil =
                new ArrayList<>();

        for (LowonganPekerjaan lowongan : lowonganList) {

            if (lowongan instanceof LowonganPrioritas) {

                hasil.add(
                        (LowonganPrioritas) lowongan
                );
            }
        }

        return hasil;
    }


    private void saveData() {

        try (BufferedWriter bw =
                     new BufferedWriter(
                             new FileWriter(FILE_NAME))) {

            for (LowonganPekerjaan lowongan
                    : lowonganList) {

                if (lowongan instanceof LowonganPrioritas) {

                    LowonganPrioritas lp =
                            (LowonganPrioritas) lowongan;

                    bw.write(
                            "PRIORITAS;" +
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
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data =
                        line.split(";");

                if (data[0].equals("PRIORITAS")) {

                    LowonganPrioritas lp =
                            new LowonganPrioritas(
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4],
                                    data[5],
                                    data[6],
                                    Integer.parseInt(data[7]),
                                    Integer.parseInt(data[8]),
                                    LocalDate.parse(data[9]),
                                    data[10],
                                    Integer.parseInt(data[11]),
                                    Boolean.parseBoolean(data[12])
                            );

                    lowonganList.add(lp);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}