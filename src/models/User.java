package models;

public class User {

    public enum Role {
        ADMIN,
        STAFF,
        MANAGER,
        WORKER
    }

    private int idUser;
    private String nama;
    private int age;
    private String email;
    private String password;
    private String alamat;
    private String noHp;
    private Role role;

    public User() {}

    public User(int idUser,
                String nama,
                int age,
                String email,
                String password,
                String alamat,
                String noHp,
                Role role) {

        this.idUser = idUser;
        this.nama = nama;
        this.age = age;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.noHp = noHp;
        this.role = role;
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

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}