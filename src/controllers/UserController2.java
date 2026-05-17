package controllers;

import models.User; 
import java.util.Date; 

public class UserController2 {

    public UserController2() {
        // Constructor kosong, bisa diisi inisialisasi koneksi database nanti 🍍
    }

    /**
     * Metode untuk menambahkan data user baru (insert_data)
     * @param email Email dari user
     * @param password Password user
     * @param role Role user 
     * @param createdAt Tanggal akun dibuat
     * @return boolean Mengembalikan true jika sukses, false jika gagal
     */
    public boolean insert_data(String email, String password, String role, Date createdAt) {
        try {
            // Logika database di sini
            
            System.out.println("Berhasil! Data user dengan email " + email + " telah ditambahkan ke sistem. 🌱");
            return true; 
            
        } catch (Exception e) {
            System.out.println("Waduh, gagal menambahkan data user: " + e.getMessage() + " 🥀");
            return false;
        }
    }

    /**
     * Metode untuk memperbarui data user yang sudah ada (update_data)
     * @param idUser ID User (Primary Key) yang akan diupdate
     * @param email Email baru
     * @param password Password baru
     * @param role Role baru
     * @return boolean Mengembalikan true jika sukses, false jika gagal
     */
    public boolean update_data(int idUser, String email, String password, String role) {
        try {
            // Logika untuk melakukan update ke database
            
            System.out.println("Sip! Data user dengan ID " + idUser + " berhasil diperbarui. 🍉");
            return true; 
            
        } catch (Exception e) {
            System.out.println("Gagal memperbarui data user: " + e.getMessage() + " 🥀");
            return false;
        }
    }
}