# CariKerja - Aplikasi Manajemen Lowongan Pekerjaan

## 📌 Deskripsi Proyek
Aplikasi desktop berbasis Java Swing untuk mengelola lowongan pekerjaan, lamaran, dan data pengguna (User, Perusahaan, Pekerja, Admin).

## 👥 Anggota Kelompok 8
- **Septian Angga Saputra (254311014)** - Class Architect & Repo Master
- **Dodo Fansyuri Andrian (254311023)** - Data & Logic Engineer  
- **Krisna Wahyu Indra Kusuma (254311029)** - UI & Robustness Engineer, QA

## 🛠️ Teknologi yang Digunakan
- Java JDK 23
- Java Swing (GUI)
- File I/O (penyimpanan data di folder `data/`)
- JUnit (Unit Testing - manual)

## 📂 Struktur Proyek
```text
pbo-FinalProject/
├── data/           # File teks penyimpanan data (users.txt, perusahaan.txt, lowongan.txt)
├── lib/            # Library eksternal (jcalendar.jar)
├── src/
│   ├── controllers/ # Logika bisnis (UserController, LamaranController, dll)
│   ├── models/      # Kelas entitas (User, Perusahaan, Lamaran, dll)
│   ├── views/       # GUI (AdminDashboard, UserManagementPanel, FormLamaranView, dll)
│   ├── exceptions/  # Kelas exception kustom
│   ├── utils/       # Utility classes (DateUtil, LoggerUtil)
│   └── validators/  # Validator (LamaranValidator)
└── build/          # Hasil kompilasi (diabaikan oleh Git)
```

text

## 🚀 Cara Menjalankan Aplikasi
1. **Clone repositori**
   ```bash
   git clone https://github.com/Sptangs/pbo-FinalProject.git
   cd pbo-FinalProject
Kompilasi semua sumber

bash
javac -cp "lib/*" -d build src/controllers/*.java src/models/*.java src/views/*.java src/*.java
Jalankan Aplikasi

bash
java -cp "build;lib/*" views.AdminDashboard
🧪 Menjalankan Unit Testing
Unit testing dilakukan secara manual dengan skenario test case pada modul User (Create, Update, Delete).
Lihat laporan unit testing untuk detail hasil (8 PASS, 1 FAIL pada TS_U03).

🔍 Fitur Utama
Modul	Deskripsi
User	Manajemen data user (CRUD) dengan validasi email & nomor HP
Perusahaan	Manajemen data perusahaan
Lowongan	Manajemen lowongan pekerjaan
Lamaran	Pekerja mengirim lamaran, admin mengelola lamaran masuk
🐞 Temuan Bug & Perbaikan
Bug: Validasi email tidak berfungsi saat update user (Issue #1).
Rekomendasi: Controller updateUser seharusnya mengembalikan boolean agar GUI bisa menolak perubahan jika email tidak valid.

📚 Referensi
Modul Praktikum PBO – Politeknik Negeri Madiun

Oracle Java Swing Tutorial

📄 Lisensi
Dokumen ini untuk keperluan akademik – Politeknik Negeri Madiun.

Terima kasih.
