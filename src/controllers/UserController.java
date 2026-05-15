// package controllers;

// import java.util.ArrayList;
// import models.User;

// public class UserController {

//     private ArrayList<User> listUser = new ArrayList<>();

//     public void AddUser(int id,
//                             String nama,
//                             String email,
//                             String password,
//                             String alamat) {

//         User user = new User();

//         user.mengisiData(id, nama, email, password, alamat);

//         listUser.add(user);

//         System.out.println("User berhasil ditambahkan!");
//     }

//     public void GetUser() {

//         if (listUser.isEmpty()) {

//             System.out.println("Data user kosong!");
//             return;
//         }

//         for (User user : listUser) {

//             user.tampilInfo();
//         }
//     }

//     public void updateUser(int idCari,
//                             String namaBaru,
//                             String emailBaru,
//                             String passwordBaru,
//                             String alamatBaru) {

//         for (User user : listUser) {

//             if (user.id_user == idCari) {

//                 user.nama = namaBaru;
//                 user.email = emailBaru;
//                 user.password = passwordBaru;
//                 user.alamat = alamatBaru;

//                 System.out.println("Data user berhasil diupdate!");
//                 return;
//             }
//         }

//         System.out.println("User tidak ditemukan!");
//     }

//     public void deleteUser(int idCari) {

//         for (User user : listUser) {

//             if (user.id_user == idCari) {

//                 listUser.remove(user);

//                 System.out.println("User berhasil dihapus!");
//                 return;
//             }
//         }

//         System.out.println("User tidak ditemukan!");
//     }
// }