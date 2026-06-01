# CariKerja - Aplikasi Manajemen Lowongan Pekerjaan

**Pembuat:** 
Septian Angga Saputra
Dodo Fansyuri Andrian 
Krisna Wahyu

---

## 📋 Daftar Isi
- [Tentang Project](#tentang-project)
- [Fitur Utama](#fitur-utama)
- [Teknologi yang Digunakan](#teknologi-yang-digunakan)
- [Struktur Project](#struktur-project)
- [Instalasi dan Setup](#instalasi-dan-setup)
- [Cara Menggunakan](#cara-menggunakan)
- [Struktur Database](#struktur-database)
- [Lisensi](#lisensi)

---

## 📌 Tentang Project

**CariKerja** adalah aplikasi desktop berbasis Java yang dirancang untuk memudahkan manajemen lowongan pekerjaan, pelamaran, dan pengguna. Aplikasi ini dibangun sebagai tugas akhir mata kuliah Pemrograman Berorientasi Objek (PBO) Level 2 dan menerapkan prinsip-prinsip OOP seperti inheritance, encapsulation, dan polymorphism.

Aplikasi ini memungkinkan:
- **Perusahaan** untuk membuka dan mengelola lowongan pekerjaan
- **Pencari Kerja (Pekerja)** untuk melamar lowongan pekerjaan
- **Admin** untuk mengelola sistem, pengguna, dan data keseluruhan
- **HR/Manager** untuk mengelola dan memproses pelamaran

---

## ✨ Fitur Utama

### 1. **Manajemen Pengguna**
- Pendaftaran pengguna dengan berbagai role (Admin, Staff, Manager, Worker)
- Login dan authentikasi
- Manajemen profile pengguna
- Kelola informasi kontak dan alamat

### 2. **Manajemen Lowongan Pekerjaan**
- Buat dan edit lowongan pekerjaan
- Deskripsikan posisi, syarat, dan kualifikasi
- Tentukan gaji dan benefit
- Status aktif/non-aktif lowongan
- Filter dan pencarian lowongan

### 3. **Sistem Pelamaran**
- Submit lamaran dengan CV dan surat pengantar
- Tracking status lamaran (Dikirim, Diproses, Diterima, Ditolak, dll)
- Manajemen CV digital
- Riwayat pelamaran lengkap

### 4. **Dashboard Admin**
- Kelola semua pengguna dalam sistem
- Monitor status lowongan
- Proses dan validasi pelamaran
- Generate laporan

### 5. **Validasi Data**
- Validasi input form lamaran
- Exception handling untuk status transisi yang tidak valid
- Logging aktivitas sistem

---

## 🛠 Teknologi yang Digunakan

- **Language:** Java 8+
- **GUI Framework:** Java Swing
- **Architecture Pattern:** Model-View-Controller (MVC)
- **Data Storage:** File-based (TXT files)
- **Build System:** Manual compilation dengan `javac`

---

## 📁 Struktur Project

```
CariKerja/
├── src/
│   ├── Main.java                           # Entry point aplikasi
│   ├── models/                             # Model/Entity classes
│   │   ├── User.java                       # Model pengguna
│   │   ├── Pekerja.java                    # Model pencari kerja
│   │   ├── Perusahaan.java                 # Model perusahaan
│   │   ├── LowonganPekerjaan.java          # Model lowongan
│   │   ├── Lamaran.java                    # Model pelamaran
│   │   ├── StatusLamaran.java              # Status enum
│   │   └── Entitas.java                    # Base class model
│   │
│   ├── controllers/                        # Controller classes
│   │   ├── UserController.java
│   │   ├── PekerjaController.java
│   │   ├── PerusahaanController.java
│   │   ├── LowonganPekerjaanController.java
│   │   └── LamaranController.java
│   │
│   ├── views/                              # View/UI classes
│   │   ├── AdminDashboard.java             # Main dashboard
│   │   ├── UserManagementPanel.java
│   │   ├── PekerjaManagementPanel.java
│   │   ├── PerusahaanManagementPanel.java
│   │   ├── LowonganManagementPanel.java
│   │   ├── FormLamaranView.java
│   │   ├── KelolaLamaranView.java
│   │   └── RiwayatLamaranView.java
│   │
│   ├── exceptions/                         # Custom exceptions
│   │   ├── InvalidLamaranException.java
│   │   └── InvalidStatusTransitionException.java
│   │
│   ├── validators/                         # Validator classes
│   │   └── LamaranValidator.java
│   │
│   ├── utils/                              # Utility classes
│   │   ├── DateUtil.java
│   │   └── LoggerUtil.java
│   │
│   └── asset/
│       └── cv/                             # Lokasi penyimpanan CV pengguna
│
├── build/                                  # Compiled classes
├── data/                                   # Data files
│   ├── users.txt                           # Data pengguna
│   ├── perusahaan.txt                      # Data perusahaan
│   └── lowongan.txt                        # Data lowongan
├── lib/                                    # Library files
│   ├── license.txt
│   ├── readme.txt
│   └── changelog.txt
└── README.md                               # File ini
```

---

## 💻 Instalasi dan Setup

### Prerequisite
- Java Development Kit (JDK) 8 atau lebih tinggi
- Terminal/Command Prompt

### Langkah-langkah Instalasi

1. **Clone atau download project:**
   ```bash
   git clone <repository-url>
   cd CariKerja
   ```

2. **Compile project:**
   ```bash
   javac -d build src/Main.java src/views/*.java src/models/*.java src/controllers/*.java src/exceptions/*.java src/validators/*.java src/utils/*.java
   ```

3. **Jalankan aplikasi:**
   ```bash
   cd build
   java Main
   ```

### Struktur Folder yang Diperlukan
Pastikan folder-folder berikut ada:
- `build/` - untuk compiled classes
- `data/` - untuk menyimpan data file
- `src/asset/cv/` - untuk menyimpan file CV

---

## 🚀 Cara Menggunakan

### Login
1. Jalankan aplikasi
2. Login dengan kredensial pengguna yang terdaftar
3. Pilih role Anda: Admin, Staff, Manager, atau Worker

### Untuk Pencari Kerja (Worker)
1. Cari lowongan pekerjaan yang tersedia
2. Baca deskripsi dan syarat lowongan
3. Klik "Lamar" dan isi form pelamaran
4. Upload CV dan tulis surat pengantar
5. Submit pelamaran
6. Lihat status pelamaran di "Riwayat Lamaran"

### Untuk Perusahaan (Employer)
1. Buat dan publikasikan lowongan pekerjaan
2. Edit atau tutup lowongan yang sudah ada
3. Lihat dan kelola pelamar

### Untuk Admin
1. Kelola semua user (create, read, update, delete)
2. Kelola perusahaan dan pekerja
3. Monitor lowongan pekerjaan
4. Proses pelamaran dan berikan feedback

---

## 🗄 Struktur Database

### Data Pengguna (users.txt)
```
ID|Nama|Email|Password|Alamat|NoHp|Role|Age
```

### Data Perusahaan (perusahaan.txt)
```
ID|Nama|Email|Alamat|Nomor|Deskripsi
```

### Data Lowongan (lowongan.txt)
```
ID|Perusahaan|Posisi|Deskripsi|Gaji|Status|TanggalDibuat
```

---

## 📝 Class Diagram Utama

### Inheritance Hierarchy
- **Entitas** (Parent Class)
  - Lamaran
  - LowonganPekerjaan
  
- **User** (Parent Class)
  - Pekerja
  - Perusahaan

### Enum
- **StatusLamaran**: Enum untuk status pelamaran (DIKIRIM, DIPROSES, DITERIMA, DITOLAK, dll)
- **User.Role**: Enum untuk role pengguna (ADMIN, STAFF, MANAGER, WORKER)

---

## 🐛 Troubleshooting

### "Cannot find symbol" saat compile
- Pastikan semua file .java ada di folder `src/`
- Jalankan compile command dari root directory project

### Aplikasi tidak membaca data
- Pastikan folder `data/` dan file `.txt` sudah ada
- Periksa path relatif dari folder `build/`

### GUI tidak muncul dengan benar
- Pastikan Java Swing kompatibel dengan sistem Anda
- Coba jalankan dengan opsi: `java -Dswing.systemlaf=javax.swing.plaf.windows.WindowsLookAndFeel Main`

---

## 📚 Konsep OOP yang Diterapkan

1. **Inheritance** - User sebagai parent class dari Pekerja dan Perusahaan
2. **Encapsulation** - Private attributes dengan public getters/setters
3. **Polymorphism** - Method override dalam controller dan model
4. **Exception Handling** - Custom exceptions untuk error handling
5. **MVC Pattern** - Pemisahan antara Model, View, dan Controller

---

## 📄 Lisensi

Project ini merupakan tugas akademik. Silakan gunakan dan modifikasi sesuai kebutuhan.

---

## 📧 Kontak

Untuk pertanyaan atau masukan, silakan hubungi pembuat project.

**Terakhir diupdate:** Mei 2026