import java.util.ArrayList;
import java.util.List;

public class UserController {
    
    // 1. WADAH DATA: Keranjang utama di dalam memori (Collections)
    private List<User> listUser = new ArrayList<>();

    // 2. CREATE: Menambah user baru ke dalam list
    public void addUser(User userBaru) {
        listUser.add(userBaru);
        System.out.println("Data Berhasil ditambahkan!");
    }

    // 3. READ: Mencari user berdasarkan ID
    public User getByIdUser(int idCari) {
        for (User u : listUser) {
            if (u.getId() == idCari) { 
                return u;
            }
        }
        return null;
    }

    // 4. UPDATE (REFACTORING): Menggunakan boolean untuk merespons ke GUI
    public boolean updateUser(int idCari, String namaBaru, int ageBaru, String emailBaru, String passwordBaru, String alamatBaru, String noHpBaru) {
        
        User user = getByIdUser(idCari);
        
        if (user == null) {
            System.out.println("User tidak ditemukan!");
            return false; 
        }

        // Memperbaiki BUG TS_U03: Validasi wajib mengandung '@'
        if (emailBaru == null || !emailBaru.contains("@")) {
            System.out.println("Email tidak valid! Harus mengandung '@'");
            return false; 
        }

        // Validasi Nomor HP agar hanya angka
        if (!noHpBaru.matches("[0-9]+")) {
            System.out.println("Nomor HP hanya boleh angka!");
            return false; 
        }

        // Eksekusi Update
        user.setNama(namaBaru);
        user.setAge(ageBaru);
        user.setEmail(emailBaru);
        user.setPassword(passwordBaru);
        user.setAlamat(alamatBaru);
        user.setNoHp(noHpBaru);

        System.out.println("User berhasil diupdate!");
        return true; // Sukses, memberi sinyal true agar GUI memproses tampilan
    }

    // 5. DELETE: Menghapus user
    public void deleteUser(int idCari) {
        User user = getByIdUser(idCari);
        if (user != null) {
            listUser.remove(user);
            System.out.println("Data Berhasil dihapus!");
        } else {
            System.out.println("User tidak ditemukan!");
        }
    }
    
    // READ ALL: Mengambil semua data
    public List<User> getAllUsers() {
        return listUser;
    }
}