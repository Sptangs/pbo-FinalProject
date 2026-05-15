// package views;

// import java.util.Scanner;

// import controllers.UserController;

// public class UserView {

//     Scanner input = new Scanner(System.in);

//     UserController controller = new UserController();

//     public void menu() {

//         int pilihan;

//         do {

//             System.out.println("\n==============================");
//             System.out.println("      MENU USER");
//             System.out.println("==============================");
//             System.out.println("1. Tambah User");
//             System.out.println("2. Tampil User");
//             System.out.println("3. Update User");
//             System.out.println("4. Hapus User");
//             System.out.println("5. Keluar");
//             System.out.println("==============================");

//             System.out.print("Pilih Menu : ");
//             pilihan = input.nextInt();
//             input.nextLine();

//             switch (pilihan) {

//                 case 1:

//                     AddUserView();
//                     break;

//                 case 2:

//                     controller.GetUser();
//                     break;

//                 case 3:

//                     updateUser();
//                     break;

//                 case 4:

//                     DeleteUserView();
//                     break;

//                 case 5:

//                     System.out.println("Program selesai.");
//                     break;

//                 default:

//                     System.out.println("Menu tidak tersedia!");
//             }

//         } while (pilihan != 5);
//     }

//     public void AddUserView() {

//         System.out.println("\n=== TAMBAH USER ===");

//         System.out.print("ID User : ");
//         int id = input.nextInt();
//         input.nextLine();

//         System.out.print("Nama : ");
//         String nama = input.nextLine();

//         System.out.print("Email : ");
//         String email = input.nextLine();

//         System.out.print("Password : ");
//         String password = input.nextLine();

//         System.out.print("Alamat : ");
//         String alamat = input.nextLine();

//         controller.AddUser(id, nama, email, password, alamat);
//     }
//     public void updateUser() {

//         System.out.println("\n=== UPDATE USER ===");

//         System.out.print("ID User : ");
//         int id = input.nextInt();
//         input.nextLine();

//         System.out.print("Nama Baru : ");
//         String nama = input.nextLine();

//         System.out.print("Email Baru : ");
//         String email = input.nextLine();

//         System.out.print("Password Baru : ");
//         String password = input.nextLine();

//         System.out.print("Alamat Baru : ");
//         String alamat = input.nextLine();

//         controller.updateUser(id, nama, email, password, alamat);
//     }


//     public void DeleteUserView() {

//         System.out.println("\n=== HAPUS USER ===");

//         System.out.print("ID User : ");
//         int id = input.nextInt();

//         controller.deleteUser(id);
//     }
// }