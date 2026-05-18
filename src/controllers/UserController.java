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

    // =========================
    // ADD USER
    // =========================
    public void addUser(int idUser,
                        String nama,
                        int age,
                        String email,
                        String password,
                        String alamat,
                        String noHp,
                        Role role) {

        User user = new User(
                idUser,
                nama,
                age,
                email,
                password,
                alamat,
                noHp,
                role
        );

        // Simpan ke ArrayList
        listUser.add(user);

        // Simpan ke TXT
        simpanKeTxt(user);

        System.out.println("User berhasil ditambahkan!");
    }

    // =========================
    // SIMPAN KE TXT
    // =========================
    private void simpanKeTxt(User user) {

        try {

            // Folder data
            File folder = new File("data");

            // Jika folder belum ada
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // File users.txt
            File file = new File(folder, "users.txt");

            // Jika file belum ada
            if (!file.exists()) {
                file.createNewFile();
            }

            // Writer
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file, true)
            );

            // Simpan data
            writer.write(
                    user.getIdUser() + "," +
                    user.getNama() + "," +
                    user.getAge() + "," +
                    user.getEmail() + "," +
                    user.getPassword() + "," +
                    user.getAlamat() + "," +
                    user.getNoHp() + "," +
                    user.getRole()
            );

            writer.newLine();

            writer.close();

            System.out.println("Data berhasil disimpan!");

        } catch (IOException e) {

            System.out.println("Gagal menyimpan data!");
            e.printStackTrace();

        }
    }

    public void getUser() {

        if (listUser.isEmpty()) {

            System.out.println("Data user kosong!");
            return;
        }

        for (User user : listUser) {

            System.out.println("===== DATA USER =====");
            System.out.println("ID User : " + user.getIdUser());
            System.out.println("Nama    : " + user.getNama());
            System.out.println("Umur    : " + user.getAge());
            System.out.println("Email   : " + user.getEmail());
            System.out.println("Alamat  : " + user.getAlamat());
            System.out.println("No HP   : " + user.getNoHp());
            System.out.println("Role    : " + user.getRole());
            System.out.println("=====================");
        }
    }

    public void updateUser(int idCari,
                           String namaBaru,
                           int ageBaru,
                           String emailBaru,
                           String passwordBaru,
                           String alamatBaru,
                           String noHpBaru,
                           Role roleBaru) {

        for (User user : listUser) {

            if (user.getIdUser() == idCari) {

                user.setNama(namaBaru);
                user.setAge(ageBaru);
                user.setEmail(emailBaru);
                user.setPassword(passwordBaru);
                user.setAlamat(alamatBaru);
                user.setNoHp(noHpBaru);
                user.setRole(roleBaru);

                System.out.println("User berhasil diupdate!");
                return;
            }
        }

        System.out.println("User tidak ditemukan!");
    }

    public void deleteUser(int idCari) {

        for (int i = 0; i < listUser.size(); i++) {

            if (listUser.get(i).getIdUser() == idCari) {

                listUser.remove(i);

                System.out.println("User berhasil dihapus!");
                return;
            }
        }

        System.out.println("User tidak ditemukan!");
    }
}