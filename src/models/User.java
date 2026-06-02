package models;

public class User {

    public enum Role {
        ADMIN,
        STAFF,
        MANAGER,
        WORKER
    }

    private static int counterId = 1;

    private int idUser;
    private String nama;
    private int age;
    private String email;
    private String password;
    private String alamat;
    private String noHp;
    private Role role;

    public User() {
    }

    public User(String nama,
                int age,
                String email,
                String password,
                String alamat,
                String noHp,
                Role role) {

        this.idUser = counterId++;

        setNama(nama);
        setAge(age);
        setEmail(email);
        setPassword(password);
        setAlamat(alamat);
        setNoHp(noHp);
        setRole(role);
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNama() {
        return nama;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public Role getRole() {
        return role;
    }

    public void setNama(String nama) {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama tidak boleh kosong!"
            );
        }

        this.nama = nama;
    }

    public void setAge(int age) {

        if (age <= 0) {
            throw new IllegalArgumentException(
                    "Umur tidak valid!"
            );
        }

        this.age = age;
    }

    public void setEmail(String email) {

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException(
                    "Format email tidak valid!"
            );
        }

        this.email = email;
    }

    public void setPassword(String password) {

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException(
                    "Password minimal 6 karakter!"
            );
        }

        this.password = password;
    }

    public void setAlamat(String alamat) {

        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Alamat tidak boleh kosong!"
            );
        }

        this.alamat = alamat;
    }

    public void setNoHp(String noHp) {

        if (noHp == null || noHp.length() < 10) {
            throw new IllegalArgumentException(
                    "Nomor HP tidak valid!"
            );
        }

        this.noHp = noHp;
    }

    public void setRole(Role role) {

        if (role == null) {
            throw new IllegalArgumentException(
                    "Role tidak boleh kosong!"
            );
        }

        this.role = role;
    }

    public void displayInfo() {

        System.out.println("===== DATA USER =====");
        System.out.println("ID       : " + idUser);
        System.out.println("Nama     : " + nama);
        System.out.println("Umur     : " + age);
        System.out.println("Email    : " + email);
        System.out.println("Alamat   : " + alamat);
        System.out.println("No HP    : " + noHp);
        System.out.println("Role     : " + role);
        System.out.println("=====================");
    }
}