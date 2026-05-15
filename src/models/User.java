package models;

public class User {
    public int id_user;
    public String nama;
    public String email;
    public String password;
    public String alamat;

    public void mengisiData(int id_user, String nama, String email, String password, String alamat) {
        this.id_user = id_user;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
    }

    public void tampilInfo() {
        System.out.println("===== DATA USER =====");
        System.out.println("ID User   : " + id_user);
        System.out.println("Nama      : " + nama);
        System.out.println("Email     : " + email);
        System.out.println("Alamat    : " + alamat);
    }
}
