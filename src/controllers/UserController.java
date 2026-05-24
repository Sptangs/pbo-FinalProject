package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import models.User;
import models.User.Role;

public class UserController {

    private ArrayList<User> listUser = new ArrayList<>();

    public boolean addUser(String nama,
                       int age,
                       String email,
                       String password,
                       String alamat,
                       String noHp,
                       Role role) {

    try {

        if (email == null || !email.contains("@")) {

            System.out.println("Email tidak valid!");
            return false;
        }

        if (!noHp.matches("[0-9]+")) {

            System.out.println("Nomor HP hanya boleh angka!");
            return false;
        }

        for (User user : listUser) {

            if (user.getEmail().equalsIgnoreCase(email)) {

                System.out.println("Email sudah digunakan!");
                return false;
            }

            if (user.getNoHp().equals(noHp)) {

                System.out.println("Nomor HP sudah digunakan!");
                return false;
            }
        }

        User user = new User(
                nama,
                age,
                email,
                password,
                alamat,
                noHp,
                role
        );

        listUser.add(user);

        saveToTxt(user);

        System.out.println("User berhasil ditambahkan!");

        return true;

    } catch (IllegalArgumentException e) {

        System.out.println("Validation Error: " + e.getMessage());

        return false;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}

    private void saveToTxt(User user) {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, "users.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true));

            writer.write(
                    user.getIdUser() + "," +
                            user.getNama() + "," +
                            user.getAge() + "," +
                            user.getEmail() + "," +
                            user.getPassword() + "," +
                            user.getAlamat() + "," +
                            user.getNoHp() + "," +
                            user.getRole());

            writer.newLine();

            writer.close();

            System.out.println("Data user berhasil disimpan!");

        } catch (IOException e) {

            System.out.println("Gagal menyimpan data user!");
            e.printStackTrace();
        }
    }

    public void getUser() {

        if (listUser.isEmpty()) {

            System.out.println("Data user kosong!");
            return;
        }

        for (User user : listUser) {

            user.displayInfo();
        }
    }

    public User getByIdUser(int idCari) {

        for (User user : listUser) {

            if (user.getIdUser() == idCari) {

                return user;
            }
        }

        return null;
    }

    public void updateUser(int idCari,
            String namaBaru,
            int ageBaru,
            String emailBaru,
            String passwordBaru,
            String alamatBaru,
            String noHpBaru,
            Role roleBaru) {

        try {

            User user = getByIdUser(idCari);

            if (user == null) {

                System.out.println("User tidak ditemukan!");
                return;
            }
            if (emailBaru == null || !emailBaru.contains("@")) {

                System.out.println("Email tidak valid! Harus mengandung '@'");
                return;
            }

            for (User cekUser : listUser) {

                if (cekUser.getIdUser() != idCari) {

                    if (cekUser.getEmail().equalsIgnoreCase(emailBaru)) {

                        System.out.println("Email sudah digunakan!");
                        return;
                    }

                    if (cekUser.getNoHp().equals(noHpBaru)) {

                        System.out.println("Nomor HP sudah digunakan!");
                        return;
                    }
                }
            }

            user.setNama(namaBaru);
            user.setAge(ageBaru);
            user.setEmail(emailBaru);
            user.setPassword(passwordBaru);
            user.setAlamat(alamatBaru);
            user.setNoHp(noHpBaru);
            user.setRole(roleBaru);

            System.out.println("User berhasil diupdate!");

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Terjadi error saat update user!");
            e.printStackTrace();
        }
    }

    public void deleteUser(int idCari) {

        User user = getByIdUser(idCari);

        if (user == null) {

            System.out.println("User tidak ditemukan!");
            return;
        }

        listUser.remove(user);

        System.out.println("User berhasil dihapus!");
    }
}