package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.User.Role;

public class UserController {
    
    private List<User> listUser = new ArrayList<>();

    public void addUser(User userBaru) {
        listUser.add(userBaru);
        System.out.println("Data Berhasil ditambahkan!");
    }

    public boolean addUser(String nama, int age, String email, String password, String alamat, String noHp, Role role) {
        try {
            User user = new User(nama, age, email, password, alamat, noHp, role);
            listUser.add(user);
            System.out.println("Data Berhasil ditambahkan!");
            return true;
        } catch (Exception e) {
            System.out.println("Error menambah user: " + e.getMessage());
            return false;
        }
    }

    public User getByIdUser(int idCari) {
        for (User u : listUser) {
            if (u.getIdUser() == idCari) { 
                return u;
            }
        }
        return null;
    }

    public boolean updateUser(int idCari, String namaBaru, int ageBaru, String emailBaru, String passwordBaru, String alamatBaru, String noHpBaru, Role roleBaru) {
        
        User user = getByIdUser(idCari);
        
        if (user == null) {
            System.out.println("User tidak ditemukan!");
            return false; 
        }

        if (emailBaru == null || !emailBaru.contains("@")) {
            System.out.println("Email tidak valid! Harus mengandung '@'");
            return false; 
        }

        if (!noHpBaru.matches("[0-9]+")) {
            System.out.println("Nomor HP hanya boleh angka!");
            return false; 
        }

        user.setNama(namaBaru);
        user.setAge(ageBaru);
        user.setEmail(emailBaru);
        user.setPassword(passwordBaru);
        user.setAlamat(alamatBaru);
        user.setNoHp(noHpBaru);
        user.setRole(roleBaru);

        System.out.println("User berhasil diupdate!");
        return true; 
    }

    public void deleteUser(int idCari) {
        User user = getByIdUser(idCari);
        if (user != null) {
            listUser.remove(user);
            System.out.println("Data Berhasil dihapus!");
        } else {
            System.out.println("User tidak ditemukan!");
        }
    }
    
    public List<User> getAllUsers() {
        return listUser;
    }
}