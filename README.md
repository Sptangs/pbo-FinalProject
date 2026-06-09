# 💼 CariKerja

<div align="center">

### Job Application Management System

Sistem Manajemen Lowongan dan Lamaran Kerja Berbasis Java Desktop  
Dibangun menggunakan prinsip **Object-Oriented Programming (OOP)** dan arsitektur **MVC (Model-View-Controller)**.

![Java](https://img.shields.io/badge/Java-8+-orange)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue)
![Architecture](https://img.shields.io/badge/Architecture-MVC-green)
![Storage](https://img.shields.io/badge/Database-TXT%20File-lightgrey)

</div>

---

# 📖 Deskripsi

**CariKerja** merupakan aplikasi desktop berbasis Java yang dirancang untuk membantu proses pengelolaan lowongan pekerjaan dan pelamaran kerja dalam satu sistem terintegrasi.

Aplikasi ini dikembangkan sebagai **Tugas Akhir Mata Kuliah Pemrograman Berorientasi Objek (PBO)** dengan menerapkan konsep-konsep utama OOP seperti:

- Abstraction
- Inheritance
- Encapsulation
- Association
- Enumeration
- Exception Handling
- MVC Pattern

Sistem memungkinkan administrator untuk mengelola data pengguna, perusahaan, lowongan pekerjaan, serta memproses lamaran kerja yang masuk.

---

# 👨‍💻 Tim Pengembang

| Nama | NIM | Role | GitHub |
|--------|--------|--------|--------|
| Septian Angga Saputra | 254311014 | Class Architect & Repo Master | [@Sptangs](https://github.com/Sptangs) |
| Dodo Fansyuri Andrian | 254311023 | Data & Logic Engineer | [@todaxyz](https://github.com/todaxyz) |
| Krisna Wahyu Indra Kusuma | 254311029 | UI & Robustness Engineer, QA | [@12Krisna](https://github.com/12Krisna) |

---

# 🏗️ Class Diagram

Diagram berikut menggambarkan struktur class serta hubungan antar entitas pada sistem CariKerja.

![Class Diagram](https://github.com/user-attachments/assets/7b9f4f8e-eb43-4c9e-bc02-21b61f4c89af)

---

## Fitur Baru yang Ditambahkan

Sistem sekarang sudah mendukung **Lowongan Prioritas** untuk meningkatkan visibilitas lowongan tertentu.

### Fitur yang sudah ditambahkan:

- ✅ Tambah Lowongan Prioritas
- ✅ Filter Lowongan Prioritas Tinggi
- ✅ Level Prioritas (TINGGI / NORMAL / RENDAH)
- ✅ Biaya Promosi Lowongan
- ✅ Opsi "Tampil di Atas"
- ✅ UI Panel Lowongan Prioritas (Swing)
- ✅ Penyimpanan ke `data/lowongan.txt`

---

## 🧠 Controller Baru

### LowonganPrioritasController

Menangani:

- CRUD Lowongan Prioritas
- Filter berdasarkan level prioritas
- Load & Save data ke file TXT
- Validasi input lowongan

---

## Format Data LOWONGAN (UPDATED)

```text
Perusahaan|Judul|Deskripsi|Kualifikasi|Lokasi|Jenis|GajiMin|GajiMax|TanggalTutup|LevelPrioritas|BiayaPromosi|TampilDiAtas

# ✨ Fitur Utama

## 👤 Manajemen Pengguna

- Tambah User
- Edit User
- Hapus User
- Pengelolaan Role Pengguna

Role yang tersedia:

- ADMIN
- STAFF
- MANAGER
- WORKER

---

## 👨‍💼 Manajemen Pekerja

- CRUD Data Pekerja
- Pengelolaan Profil Pekerja
- Penyimpanan Data ke File TXT

---

## 🏢 Manajemen Perusahaan

- CRUD Data Perusahaan
- Pengelolaan Informasi Perusahaan
- Penyimpanan Data ke File TXT

---

## 💼 Manajemen Lowongan Pekerjaan

- Tambah Lowongan
- Edit Lowongan
- Hapus Lowongan
- Pengelolaan Informasi Lowongan
- Pengaturan Status Lowongan

---

## 📨 Sistem Pelamaran

- Pemilihan Lowongan
- Upload CV
- Submit Lamaran
- Tracking Status Lamaran

Status Lamaran:

```text
DIAJUKAN
DIREVIEW
DITERIMA
DITOLAK
DIBATALKAN
```

---

## 📊 Dashboard Admin

Dashboard terintegrasi untuk:

- Kelola User
- Kelola Pekerja
- Kelola Perusahaan
- Kelola Lowongan
- Monitoring Lamaran

---

# 🏛️ Arsitektur MVC

```text
┌─────────────┐
│    Model    │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ Controller  │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│    View     │
└─────────────┘
```

### Model

Menyimpan data dan representasi objek bisnis.

Contoh:

- User
- Pekerja
- Perusahaan
- LowonganPekerjaan
- Lamaran

### Controller

Menangani logika bisnis dan proses CRUD.

Contoh:

- UserController
- PekerjaController
- PerusahaanController
- LowonganPekerjaanController
- LamaranController

### View

Menyediakan antarmuka pengguna berbasis Java Swing.

Contoh:

- AdminDashboard
- FormLamaranView
- RiwayatLamaranView

---

# 📁 Struktur Project

```text
CariKerja/
│
├── .gitignore
├── README.md
│
├── build/
│
├── data/
│   ├── users.txt
│   ├── pekerja.txt
│   ├── perusahaan.txt
│   ├── lowongan.txt
│   └── lamaran.txt
│
├── lib/
│   └── jcalendar-0.8.1.jar
│
└── src/
    │
    ├── Main.java
    │
    ├── asset/
    │   └── cv/
    │
    ├── assets/
    │   └── cv/
    │
    ├── models/
    │   ├── Entitas.java
    │   ├── User.java
    │   ├── Pekerja.java
    │   ├── Perusahaan.java
    │   ├── LowonganPekerjaan.java
    │   ├── Lamaran.java
    │   └── StatusLamaran.java
    │
    ├── controllers/
    │   ├── UserController.java
    │   ├── PekerjaController.java
    │   ├── PerusahaanController.java
    │   ├── LowonganPekerjaanController.java
    │   └── LamaranController.java
    │
    ├── views/
    │   ├── AdminDashboard.java
    │   ├── UserManagementPanel.java
    │   ├── PekerjaManagementPanel.java
    │   ├── PerusahaanManagementPanel.java
    │   ├── LowonganManagementPanel.java
    │   ├── FormLamaranView.java
    │   ├── KelolaLamaranView.java
    │   └── RiwayatLamaranView.java
    │
    ├── exceptions/
    │   ├── InvalidLamaranException.java
    │   └── InvalidStatusTransitionException.java
    │
    ├── validators/
    │   └── LamaranValidator.java
    │
    └── utils/
        ├── DateUtil.java
        └── LoggerUtil.java
```

---

# ⚙️ Instalasi

## Clone Repository

```bash
git clone https://github.com/Sptangs/pbo-FinalProject.git
cd pbo-FinalProject
```

---

# 🔨 Build Project

```bash
javac -cp "lib/jcalendar-0.8.1.jar" -d build src/Main.java src/models/*.java src/controllers/*.java src/views/*.java src/exceptions/*.java src/validators/*.java src/utils/*.java
```

---

# ▶️ Menjalankan Program

| Fitur | Perintah |
|--------|--------|
| Main Application | `java -cp build Main` |
| Dashboard Admin | `java -cp build views.AdminDashboard` |
| Form Lamaran | `java -cp build views.FormLamaranView` |
| Riwayat Lamaran | `java -cp build views.RiwayatLamaranView` |

---

# 🗄️ Struktur Data

### users.txt

```text
ID|Nama|Age|Email|Alamat|NoHP|Role
```

### pekerja.txt

```text
ID|Nama|Keahlian|Pengalaman|Kontak
```

### perusahaan.txt

```text
ID|Nama|Email|Alamat|NoTelepon|Deskripsi
```

### lowongan.txt

```text
ID|Perusahaan|Posisi|Deskripsi|Gaji|Status|TanggalDibuat
```

### lamaran.txt

```text
ID|Lowongan|Pekerja|CV|CoverLetter|Tanggal|Status
```

---

# 📚 Implementasi OOP

## Abstraction

Menggunakan abstract class:

```java
Entitas
```

Sebagai dasar bagi entitas sistem.

---

## Inheritance

Class yang mewarisi `Entitas`:

- Pekerja
- Perusahaan
- LowonganPekerjaan

---

## Encapsulation

Seluruh atribut menggunakan access modifier:

```java
private
```

dan diakses melalui getter/setter.

---

## Association

Class `Lamaran` berelasi dengan:

- Pekerja
- LowonganPekerjaan
- StatusLamaran

---

## Exception Handling

Custom Exception:

- InvalidLamaranException
- InvalidStatusTransitionException

---

# 🛠️ Teknologi

| Teknologi | Keterangan |
|------------|------------|
| Java 8+ | Bahasa Pemrograman |
| Java Swing | GUI Framework |
| MVC Pattern | Arsitektur Aplikasi |
| TXT File | Penyimpanan Data |
| Git | Version Control |
| GitHub | Repository Hosting |
| JCalendar | Date Picker Library |

---

# 📄 Lisensi

Project ini dikembangkan untuk keperluan akademik dalam Mata Kuliah Pemrograman Berorientasi Objek (PBO).

---

<div align="center">

### ☕ CariKerja Team

Politeknik Negeri Madiun • 2026

</div>
