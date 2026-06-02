package controllers;

import models.Pekerja;

import java.io.*;
import java.util.ArrayList;

public class PekerjaController {

    private ArrayList<Pekerja> listPekerja;
    private String message;

    public PekerjaController() {
        listPekerja = new ArrayList<>();
        loadFromTxt();
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Pekerja> getAllPekerja() {
        return listPekerja;
    }

    private void loadFromTxt() {

        try {
            File file = new File("data/pekerja.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 7) {

                    Pekerja p = new Pekerja(
                            Integer.parseInt(data[0]),
                            data[1],
                            Integer.parseInt(data[2]),
                            data[3],
                            data[4],
                            data[5],
                            data[6]);

                    listPekerja.add(p);
                }
            }

            reader.close();

        } catch (Exception e) {
            message = "ERROR LOAD DATA : " + e.getMessage();
            e.printStackTrace();
        }
    }

    public int getNextId() {
        if (listPekerja.isEmpty()) {
            return 1;
        }
        int maxId = 0;
        for (Pekerja p : listPekerja) {
            if (p.getIdPekerja() > maxId) {
                maxId = p.getIdPekerja();
            }
        }
        return maxId + 1;
    }

    public boolean addPekerja(
            int idPekerja,
            String nama,
            int umur,
            String email,
            String noTelepon,
            String alamat,
            String keahlian) {

        try {

            if (nama.isEmpty() ||
                    email.isEmpty() ||
                    noTelepon.isEmpty() ||
                    alamat.isEmpty() ||
                    keahlian.isEmpty()) {

                message = "Semua field wajib diisi!";
                return false;
            }

            if (umur < 17 || umur > 70) {
                message = "Umur harus 17 - 70 tahun!";
                return false;
            }

            if (!email.contains("@")) {
                message = "Format email tidak valid!";
                return false;
            }

            if (!noTelepon.matches("[0-9]+")) {
                message = "Nomor telepon hanya boleh angka!";
                return false;
            }

            if (noTelepon.length() < 10) {
                message = "Nomor telepon minimal 10 digit!";
                return false;
            }

            for (Pekerja p : listPekerja) {

                if (p.getIdPekerja() == idPekerja) {
                    message = "ID sudah digunakan!";
                    return false;
                }

                if (p.getEmail().equalsIgnoreCase(email)) {
                    message = "Email sudah digunakan!";
                    return false;
                }

                if (p.getNoTelepon().equals(noTelepon)) {
                    message = "Nomor telepon sudah digunakan!";
                    return false;
                }
            }

            Pekerja pekerja = new Pekerja(
                    idPekerja,
                    nama,
                    umur,
                    email,
                    noTelepon,
                    alamat,
                    keahlian);

            listPekerja.add(pekerja);

            saveToTxt();

            message = "Data pekerja berhasil ditambahkan!";
            return true;

        } catch (Exception e) {
            message = "ERROR ADD : " + e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPekerja(
            int id,
            String nama,
            int umur,
            String email,
            String noTelepon,
            String alamat,
            String keahlian) {

        try {

            Pekerja pekerja = getById(id);

            if (pekerja == null) {
                message = "Pekerja tidak ditemukan!";
                return false;
            }

            if (nama.isEmpty() ||
                    email.isEmpty() ||
                    noTelepon.isEmpty() ||
                    alamat.isEmpty() ||
                    keahlian.isEmpty()) {

                message = "Semua field wajib diisi!";
                return false;
            }

            if (umur < 17 || umur > 70) {
                message = "Umur harus 17 - 70 tahun!";
                return false;
            }

            if (!email.contains("@")) {
                message = "Format email tidak valid!";
                return false;
            }

            if (!noTelepon.matches("[0-9]+")) {
                message = "Nomor telepon hanya boleh angka!";
                return false;
            }

            if (noTelepon.length() < 10) {
                message = "Nomor telepon minimal 10 digit!";
                return false;
            }

            for (Pekerja p : listPekerja) {

                if (p.getIdPekerja() != id) {

                    if (p.getEmail().equalsIgnoreCase(email)) {
                        message = "Email sudah digunakan!";
                        return false;
                    }

                    if (p.getNoTelepon().equals(noTelepon)) {
                        message = "Nomor telepon sudah digunakan!";
                        return false;
                    }
                }
            }

            pekerja.setNama(nama);
            pekerja.setUmur(umur);
            pekerja.setEmail(email);
            pekerja.setNoTelepon(noTelepon);
            pekerja.setAlamat(alamat);
            pekerja.setKeahlian(keahlian);

            saveToTxt();

            message = "Data pekerja berhasil diupdate!";
            return true;

        } catch (Exception e) {
            message = "ERROR EDIT : " + e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePekerja(int id) {

        try {

            Pekerja pekerja = getById(id);

            if (pekerja == null) {
                message = "Pekerja tidak ditemukan!";
                return false;
            }

            listPekerja.remove(pekerja);
            saveToTxt();

            message = "Data pekerja berhasil dihapus!";
            return true;

        } catch (Exception e) {
            message = "ERROR DELETE : " + e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    private Pekerja getById(int id) {

        for (Pekerja p : listPekerja) {
            if (p.getIdPekerja() == id) {
                return p;
            }
        }

        return null;
    }

    private void saveToTxt() {

        try {

            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("data/pekerja.txt"));

            for (Pekerja p : listPekerja) {

                writer.write(
                        p.getIdPekerja() + "," +
                                p.getNama() + "," +
                                p.getUmur() + "," +
                                p.getEmail() + "," +
                                p.getNoTelepon() + "," +
                                p.getAlamat() + "," +
                                p.getKeahlian());

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            message = "ERROR SAVE : " + e.getMessage();
            e.printStackTrace();
        }
    }
}