## 👨‍💻 Tim Pengembang

| Nama                      | NIM       | Role                          | GitHub    |
| ------------------------- | --------- | ----------------------------- | --------- |
| Septian Angga Saputra     | 254311014 | Class Architect & Repo Master | @Sptangs  |
| Dodo Fansyuri Andrian     | 254311023 | Data & Logic Engineer         | @todaxyz  |
| Krisna Wahyu Indra Kusuma | 254311029 | UI & Robustness Engineer, QA  | @12Krisna |

---

# 🏗 Class Diagram
<img width="1520" height="1562" alt="Diagram Tanpa Judul-Halaman-2 drawio" src="https://github.com/user-attachments/assets/7b9f4f8e-eb43-4c9e-bc02-21b61f4c89af" />


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

# ▶ Menjalankan Program

## Dashboard Admin (Utama)

```bash
java -cp build Main
```

## Dashboard Admin

```bash
java -cp build views.AdminDashboard
```

## Form Lamaran

```bash
java -cp build views.FormLamaranView
```

## Riwayat Lamaran

```bash
java -cp build views.RiwayatLamaranView
```

---

# 🗄 Struktur Penyimpanan Data

## users.txt

```text
ID|Nama|Age|Email|Alamat|NoHP|Role
```

## pekerja.txt

```text
ID|Nama|Keahlian|Pengalaman|Kontak
```

## perusahaan.txt

```text
ID|Nama|Email|Alamat|NoTelepon|Deskripsi
```

## lowongan.txt

```text
ID|Perusahaan|Posisi|Deskripsi|Gaji|Status|TanggalDibuat
```

## lamaran.txt

```text
ID|Lowongan|Pekerja|CV|CoverLetter|Tanggal|Status
```

---

# 📚 Konsep OOP yang Diterapkan

## 1. Abstraction

Class `Entitas` digunakan sebagai abstract class yang menyediakan atribut dasar bagi entitas dalam sistem.

## 2. Inheritance

Class yang mewarisi `Entitas`:

* Pekerja
* Perusahaan
* LowonganPekerjaan

## 3. Encapsulation

Penggunaan:

* Private Attribute
* Getter dan Setter

## 4. Association

Class `Lamaran` berelasi dengan:

* Pekerja
* LowonganPekerjaan
* StatusLamaran

## 5. Enumeration

### Role

* ADMIN
* STAFF
* MANAGER
* WORKER

### StatusLamaran

* DIAJUKAN
* DIREVIEW
* DITERIMA
* DITOLAK
* DIBATALKAN

## 6. Exception Handling

Custom exception:

* InvalidLamaranException
* InvalidStatusTransitionException

## 7. MVC Pattern

* Model
* View
* Controller

---

## 📝 Terakhir Diperbarui

Juni 2026
