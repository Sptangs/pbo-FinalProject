package controllers;

import models.Perusahaan;

import java.io.*;
import java.util.ArrayList;

public class PerusahaanController {

    private ArrayList<Perusahaan> listPerusahaan = new ArrayList<>();
    private String message;

    public PerusahaanController() {
        loadFromTxt();
    }

    public String getMessage() {
        return message;
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    private boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("\\d{10,}");
    }

    private void loadFromTxt() {
        try {
            File file = new File("data/perusahaan.txt");
            if (!file.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {

                String[] d = line.split(",");

                if (d.length >= 7) {
                    listPerusahaan.add(new Perusahaan(
                            d[1], d[2], d[3], d[4], d[5], d[6]
                    ));
                }
            }

            br.close();

        } catch (Exception e) {
            message = "Gagal load data";
        }
    }

    private void saveToTxt() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("data/perusahaan.txt")
            );

            for (Perusahaan p : listPerusahaan) {
                bw.write(
                        p.getIdPerusahaan() + "," +
                        p.getNamaPerusahaan() + "," +
                        p.getAlamat() + "," +
                        p.getEmail() + "," +
                        p.getNoTelepon() + "," +
                        p.getBidang() + "," +
                        p.getDeskripsi()
                );
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            message = "Gagal save data";
        }
    }

    public ArrayList<Perusahaan> getAllPerusahaan() {
        return listPerusahaan;
    }

    public Perusahaan getById(int id) {
        for (Perusahaan p : listPerusahaan) {
            if (p.getIdPerusahaan() == id) return p;
        }
        message = "Data tidak ditemukan";
        return null;
    }

    public boolean addPerusahaan(String nama,
                                 String alamat,
                                 String email,
                                 String telepon,
                                 String bidang,
                                 String deskripsi) {

        if (isEmpty(nama) || isEmpty(alamat) || isEmpty(email)
                || isEmpty(telepon) || isEmpty(bidang)) {
            message = "Semua field wajib diisi";
            return false;
        }

        if (!isEmailValid(email)) {
            message = "Email tidak valid";
            return false;
        }

        if (!isPhoneValid(telepon)) {
            message = "Nomor telepon tidak valid";
            return false;
        }

        for (Perusahaan p : listPerusahaan) {
            if (p.getEmail().equalsIgnoreCase(email) ||
                p.getNoTelepon().equals(telepon)) {
                message = "Email / No HP sudah digunakan";
                return false;
            }
        }

        if (!isEmpty(deskripsi) && deskripsi.length() > 10) {
            message = "Deskripsi maksimal 10 karakter";
            return false;
        }

        listPerusahaan.add(new Perusahaan(
                nama, alamat, email, telepon, bidang, deskripsi
        ));

        saveToTxt();
        message = "Berhasil tambah perusahaan";
        return true;
    }

    public boolean editPerusahaan(int id,
                                  String nama,
                                  String alamat,
                                  String email,
                                  String telepon,
                                  String bidang,
                                  String deskripsi) {

        Perusahaan p = getById(id);
        if (p == null) return false;

        if (isEmpty(nama) || isEmpty(alamat) || isEmpty(email)
                || isEmpty(telepon) || isEmpty(bidang)) {
            message = "Semua field wajib diisi";
            return false;
        }

        if (!isEmailValid(email) || !isPhoneValid(telepon)) {
            message = "Data tidak valid";
            return false;
        }

        for (Perusahaan x : listPerusahaan) {
            if (x.getIdPerusahaan() != id) {
                if (x.getEmail().equalsIgnoreCase(email) ||
                    x.getNoTelepon().equals(telepon)) {
                    message = "Email / No HP sudah dipakai";
                    return false;
                }
            }
        }

        p.setNamaPerusahaan(nama);
        p.setAlamat(alamat);
        p.setEmail(email);
        p.setNoTelepon(telepon);
        p.setBidang(bidang);
        p.setDeskripsi(deskripsi);

        saveToTxt();
        message = "Berhasil update";
        return true;
    }

    public boolean deletePerusahaan(int id) {
        Perusahaan p = getById(id);
        if (p == null) return false;

        listPerusahaan.remove(p);
        saveToTxt();

        message = "Berhasil hapus";
        return true;
    }
}