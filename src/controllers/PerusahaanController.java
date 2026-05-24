package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.Perusahaan;

public class PerusahaanController {

    private ArrayList<Perusahaan> listPerusahaan = new ArrayList<>();

    public void addPerusahaan(int idPerusahaan,
                              String namaPerusahaan,
                              String alamat,
                              String email,
                              String noTelepon,
                              String bidang,
                              String deskripsi) {

        try {

            Perusahaan perusahaan = new Perusahaan(
                    idPerusahaan,
                    namaPerusahaan,
                    alamat,
                    email,
                    noTelepon,
                    bidang,
                    deskripsi
            );

            listPerusahaan.add(perusahaan);

            saveToTxt(perusahaan);

            System.out.println("Perusahaan berhasil ditambahkan!");

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Error while adding perusahaan!");
            e.printStackTrace();

        }
    }

    private void saveToTxt(Perusahaan perusahaan) {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, "companies.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            writer.write(
                    perusahaan.getIdPerusahaan() + "," +
                    perusahaan.getNamaPerusahaan() + "," +
                    perusahaan.getAlamat() + "," +
                    perusahaan.getEmail() + "," +
                    perusahaan.getNoTelepon() + "," +
                    perusahaan.getBidang() + "," +
                    perusahaan.getDeskripsi()
            );

            writer.newLine();

            writer.close();

            System.out.println("Data perusahaan berhasil disimpan!");

        } catch (IOException e) {

            System.out.println("Gagal menyimpan data perusahaan!");
            e.printStackTrace();

        }
    }

    public void getPerusahaan() {

        if (listPerusahaan.isEmpty()) {

            System.out.println("Data perusahaan kosong!");
            return;
        }

        for (Perusahaan perusahaan : listPerusahaan) {

            System.out.println("===== DATA PERUSAHAAN =====");
            System.out.println("ID           : " + perusahaan.getIdPerusahaan());
            System.out.println("Nama         : " + perusahaan.getNamaPerusahaan());
            System.out.println("Alamat       : " + perusahaan.getAlamat());
            System.out.println("Email        : " + perusahaan.getEmail());
            System.out.println("No Telepon   : " + perusahaan.getNoTelepon());
            System.out.println("Bidang       : " + perusahaan.getBidang());
            System.out.println("Deskripsi    : " + perusahaan.getDeskripsi());
            System.out.println("===========================");
        }
    }

    public Perusahaan getByIdPerusahaan(int idCari) {

        for (Perusahaan perusahaan : listPerusahaan) {

            if (perusahaan.getIdPerusahaan() == idCari) {
                return perusahaan;
            }
        }

        return null;
    }

    public void editPerusahaan(int idCari,
                               String namaBaru,
                               String alamatBaru,
                               String emailBaru,
                               String noTeleponBaru,
                               String bidangBaru,
                               String deskripsiBaru) {

        try {

            Perusahaan perusahaan = getByIdPerusahaan(idCari);

            if (perusahaan == null) {

                System.out.println("Perusahaan tidak ditemukan!");
                return;
            }

            perusahaan.setNamaPerusahaan(namaBaru);
            perusahaan.setAlamat(alamatBaru);
            perusahaan.setEmail(emailBaru);
            perusahaan.setNoTelepon(noTeleponBaru);
            perusahaan.setBidang(bidangBaru);
            perusahaan.setDeskripsi(deskripsiBaru);

            System.out.println("Perusahaan berhasil diupdate!");

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Error while updating perusahaan!");
            e.printStackTrace();

        }
    }

    public void deletePerusahaan(int idCari) {

        Perusahaan perusahaan = getByIdPerusahaan(idCari);

        if (perusahaan != null) {

            listPerusahaan.remove(perusahaan);

            System.out.println("Perusahaan berhasil dihapus!");
            return;
        }

        System.out.println("Perusahaan tidak ditemukan!");
    }
}