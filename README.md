# CariKerja - Aplikasi Manajemen Lowongan Pekerjaan

Aplikasi desktop berbasis Java untuk mengelola lowongan pekerjaan, pelamaran kerja, dan data pengguna menggunakan konsep Pemrograman Berorientasi Objek (PBO).

---

## 👨‍💻 Pembuat

- Septian Angga Saputra
- Dodo Fansyuri Andrian
- Krisna Wahyu

---

# 📋 Daftar Isi

- [Tentang Project](#-tentang-project)
- [Fitur Utama](#-fitur-utama)
- [Teknologi yang Digunakan](#-teknologi-yang-digunakan)
- [Struktur Project](#-struktur-project)
- [Instalasi dan Setup](#-instalasi-dan-setup)
- [Cara Menggunakan](#-cara-menggunakan)
- [Struktur Penyimpanan Data](#-struktur-penyimpanan-data)
- [Konsep OOP yang Diterapkan](#-konsep-oop-yang-diterapkan)
- [Troubleshooting](#-troubleshooting)
- [Lisensi](#-lisensi)

---

# 📌 Tentang Project

**CariKerja** adalah aplikasi desktop berbasis Java yang dirancang untuk membantu proses pencarian kerja dan manajemen lowongan pekerjaan dalam satu sistem terintegrasi.

Project ini dibuat sebagai tugas akhir mata kuliah **Pemrograman Berorientasi Objek (PBO)** dan menerapkan berbagai konsep OOP seperti:

- Inheritance
- Encapsulation
- Polymorphism
- Exception Handling
- MVC (Model View Controller)

Aplikasi ini memungkinkan:

- **Pekerja** mencari dan melamar pekerjaan
- **Perusahaan** membuat dan mengelola lowongan
- **Admin** mengelola seluruh sistem
- **Manager/HR** memproses data pelamaran

---

# ✨ Fitur Utama

## 👤 Manajemen Pengguna

- Registrasi pengguna
- Login dan autentikasi
- Manajemen role pengguna
- Edit profil pengguna
- Kelola data kontak dan alamat

---

## 💼 Manajemen Lowongan Pekerjaan

- Menambah lowongan pekerjaan
- Mengedit lowongan pekerjaan
- Menentukan kualifikasi pekerjaan
- Menentukan rentang gaji
- Aktivasi/nonaktif lowongan
- Pencarian lowongan

---

## 📨 Sistem Pelamaran

- Submit lamaran pekerjaan
- Upload CV digital
- Menulis surat pengantar
- Riwayat pelamaran
- Tracking status lamaran

---

## 🛠 Dashboard Admin

- Kelola data pengguna
- Kelola data perusahaan
- Monitoring lowongan
- Validasi pelamaran
- Monitoring aktivitas sistem

---

## ✅ Validasi dan Exception Handling

- Validasi form input
- Validasi status pelamaran
- Custom exception
- Logging aktivitas

---

# 🛠 Teknologi yang Digunakan

| Teknologi | Keterangan |
|---|---|
| Java 8+ | Bahasa pemrograman utama |
| Java Swing | GUI Desktop |
| MVC Pattern | Arsitektur aplikasi |
| TXT File Storage | Penyimpanan data |
| Git | Version control |

---

# 📁 Struktur Project

```bash
CariKerja/
├── src/
│   ├── Main.java
│   │
│   ├── models/
│   │   ├── Entitas.java
│   │   ├── User.java
│   │   ├── Pekerja.java
│   │   ├── Perusahaan.java
│   │   ├── LowonganPekerjaan.java
│   │   ├── Lamaran.java
│   │   └── StatusLamaran.java
│   │
│   ├── controllers/
│   │   ├── UserController.java
│   │   ├── PekerjaController.java
│   │   ├── PerusahaanController.java
│   │   ├── LowonganPekerjaanController.java
│   │   └── LamaranController.java
│   │
│   ├── views/
│   │   ├── AdminDashboard.java
│   │   ├── UserManagementPanel.java
│   │   ├── PekerjaManagementPanel.java
│   │   ├── PerusahaanManagementPanel.java
│   │   ├── LowonganManagementPanel.java
│   │   ├── FormLamaranView.java
│   │   ├── KelolaLamaranView.java
│   │   └── RiwayatLamaranView.java
│   │
│   ├── exceptions/
│   │   ├── InvalidLamaranException.java
│   │   └── InvalidStatusTransitionException.java
│   │
│   ├── validators/
│   │   └── LamaranValidator.java
│   │
│   ├── utils/
│   │   ├── DateUtil.java
│   │   └── LoggerUtil.java
│   │
│   └── asset/
│       └── cv/
│
├── build/
├── data/
│   ├── users.txt
│   ├── perusahaan.txt
│   └── lowongan.txt
│
├── lib/
└── README.md
```

---

# 💻 Instalasi dan Setup

## 📌 Prasyarat

Pastikan perangkat telah terinstall:

- Java Development Kit (JDK) 8 atau lebih baru
- Git (opsional)
- Terminal / CMD

---

## 📥 Clone Repository

```bash
git clone <repository-url>
cd CariKerja
```

---

## ⚙ Compile Project

```bash
javac -d build src/Main.java src/views/*.java src/models/*.java src/controllers/*.java src/exceptions/*.java src/validators/*.java src/utils/*.java
```

---

## ▶ Menjalankan Program

```bash
cd build
java Main
```

---

# 🚀 Cara Menggunakan

## 🔐 Login Sistem

1. Jalankan aplikasi
2. Masukkan email dan password
3. Login sesuai role pengguna

---

## 👷 Untuk Pekerja

1. Cari lowongan pekerjaan
2. Baca detail lowongan
3. Klik tombol **Lamar**
4. Upload CV
5. Isi surat pengantar
6. Submit lamaran
7. Lihat status pelamaran

---

## 🏢 Untuk Perusahaan

1. Tambahkan lowongan pekerjaan
2. Edit lowongan
3. Kelola pelamar
4. Proses pelamaran

---

## 👨‍💼 Untuk Admin

1. Kelola pengguna
2. Kelola perusahaan
3. Monitoring lowongan
4. Validasi data
5. Monitoring sistem

---

# 🗄 Struktur Penyimpanan Data

## users.txt

```txt
ID|Nama|Email|Password|Alamat|NoHp|Role|Age
```

---

## perusahaan.txt

```txt
ID|Nama|Email|Alamat|Nomor|Deskripsi
```

---

## lowongan.txt

```txt
ID|Perusahaan|Posisi|Deskripsi|Gaji|Status|TanggalDibuat
```

---

# 📚 Konsep OOP yang Diterapkan

## 1. Inheritance

Class `User` menjadi parent class dari:

- `Pekerja`
- `Perusahaan`

---

## 2. Encapsulation

Penggunaan:

- Private attribute
- Getter dan Setter

---

## 3. Polymorphism

Method overriding pada model dan controller.

---

## 4. Exception Handling

Menggunakan custom exception:

- `InvalidLamaranException`
- `InvalidStatusTransitionException`

---

## 5. MVC Pattern

Pemisahan antara:

- Model
- View
- Controller

Agar struktur aplikasi lebih rapi dan mudah dikembangkan.

---

# 🐛 Troubleshooting

## ❌ Error "Cannot find symbol"

### Solusi:

- Pastikan semua file `.java` berada di folder yang benar
- Compile dari root project

---

## ❌ Data Tidak Tersimpan

### Solusi:

- Pastikan folder `data/` tersedia
- Pastikan file `.txt` sudah dibuat

---

## ❌ GUI Tidak Muncul

### Solusi:

```bash
java -Dswing.systemlaf=javax.swing.plaf.windows.WindowsLookAndFeel Main
```

---

# 📄 Lisensi

Project ini dibuat untuk kebutuhan akademik dan pembelajaran.

Silakan digunakan dan dikembangkan sesuai kebutuhan.

---

# 📧 Kontak

Untuk pertanyaan dan masukan terkait project, silakan hubungi tim pengembang.

---

## 📝 Terakhir Diperbarui

**Mei 2026**
