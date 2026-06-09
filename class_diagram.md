# Class Diagram - CariKerja Project

```mermaid
classDiagram
    %% Abstract Class
    class Entitas {
        -int id
        -int counter$
        +Entitas()
        +Entitas(int id, boolean fromFile)
        +getId() int
    }

    %% Model Classes
    class User {
        -Role role
        -int idUser
        -String nama
        -int age
        -String email
        -String password
        -String alamat
        -String noHp
        +User()
        +User(String, int, String, String, String, String, Role)
        +getIdUser() int
        +getNama() String
        +setNama(String)
        +getRole() Role
        +setRole(Role)
    }

    class Role {
        <<enumeration>>
        ADMIN
        STAFF
        MANAGER
        WORKER
    }

    class Pekerja {
        -String nama
        -int umur
        -String email
        -String noTelepon
        -String alamat
        -String keahlian
        +getIdPekerja() int
        +getNama() String
        +getUmur() int
        +getEmail() String
        +getNoTelepon() String
        +getAlamat() String
        +getKeahlian() String
        +setNama(String)
        +setUmur(int)
        +setEmail(String)
    }

    class Perusahaan {
        -String namaPerusahaan
        -String alamat
        -String email
        -String noTelepon
        -String bidang
        -String deskripsi
        +getIdPerusahaan() int
        +getNamaPerusahaan() String
        +getAlamat() String
        +getEmail() String
        +getNoTelepon() String
        +getBidang() String
        +getDeskripsi() String
        +setNamaPerusahaan(String)
        +setAlamat(String)
    }

    class LowonganPekerjaan {
        -String perusahaanNama
        -String judul
        -String deskripsi
        -String kualifikasi
        -String lokasi
        -String jenis
        -int gajiMin
        -int gajiMax
        -LocalDate tanggalPosting
        -LocalDate tanggalTutup
        -boolean aktif
        +getIdLowongan() int
        +getJudul() String
        +getDeskripsi() String
        +getKualifikasi() String
        +getGaji() String
        +getTanggalPosting() LocalDate
        +getTanggalTutup() LocalDate
        +isAktif() boolean
        +setJudul(String)
        +setAktif(boolean)
    }

    class Lamaran {
        -LowonganPekerjaan jobPosting
        -Pekerja worker
        -String cvPath
        -String coverLetter
        -Date applicationDate
        -StatusLamaran status
        -String hrNote
        -Date processedDate
        +Lamaran(LowonganPekerjaan, Pekerja, String, String)
        +getIdLamaran() int
        +getJobPosting() LowonganPekerjaan
        +getWorker() Pekerja
        +getCvPath() String
        +getCoverLetter() String
        +getStatus() StatusLamaran
        +setStatus(StatusLamaran)
        +getHrNote() String
        +setHrNote(String)
    }

    class StatusLamaran {
        <<enumeration>>
        DIAJUKAN
        DIREVIEW
        DITERIMA
        DITOLAK
        DIBATALKAN
        +getLabel() String
        +bisaDiubah() boolean
        +bisaDibatalkan() boolean
    }

    %% Exception Classes
    class Exception {
        <<abstract>>
    }

    class InvalidLamaranException {
        +InvalidLamaranException(String message)
    }

    class InvalidStatusTransitionException {
        +InvalidStatusTransitionException(String message)
    }

    %% Controller Classes
    class PekerjaController {
        -ArrayList~Pekerja~ listPekerja
        -String message
        +PekerjaController()
        +getMessage() String
        +getAllPekerja() ArrayList
        +addPekerja(Pekerja) boolean
        +updatePekerja(Pekerja) boolean
        +deletePekerja(int) boolean
        +saveTxt()
        +loadFromTxt()
    }

    class PerusahaanController {
        -ArrayList~Perusahaan~ listPerusahaan
        -String message
        +PerusahaanController()
        +getMessage() String
        +getAllPerusahaan() ArrayList
        +addPerusahaan(Perusahaan) boolean
        +updatePerusahaan(Perusahaan) boolean
        +deletePerusahaan(int) boolean
        +saveTxt()
        +loadFromTxt()
    }

    class LowonganPekerjaanController {
        -ArrayList~LowonganPekerjaan~ listLowongan
        -String message
        +LowonganPekerjaanController()
        +getMessage() String
        +getAllLowongan() ArrayList
        +addLowongan(LowonganPekerjaan) boolean
        +updateLowongan(LowonganPekerjaan) boolean
        +deleteLowongan(int) boolean
        +saveTxt()
        +loadFromTxt()
    }

    class LamaranController {
        -ArrayList~Lamaran~ applicationList
        -LowonganPekerjaanController lowonganController
        -String message
        +LamaranController(LowonganPekerjaanController)
        +getMessage() String
        +addApplication(LowonganPekerjaan, Pekerja, String, String) boolean
        +updateStatus(int, StatusLamaran) boolean
        +getApplicationsByWorker(int) ArrayList
        +saveTxt()
        +loadFromTxt()
    }

    class UserController {
        -ArrayList~User~ listUser
        -String message
        +UserController()
        +getMessage() String
        +getAllUser() ArrayList
        +addUser(User) boolean
        +updateUser(User) boolean
        +deleteUser(int) boolean
        +authenticate(String, String) User
        +saveTxt()
        +loadFromTxt()
    }

    %% Validator Classes
    class LamaranValidator {
        +validateJobPosting(LowonganPekerjaan)$
        +validateWorker(Pekerja)$
        +validateCvPath(String)$
        +validateCoverLetter(String)$
    }

    %% Utility Classes
    class DateUtil {
        +formatDate(Date) String$
        +parseDate(String) Date$
        +getCurrentDate() Date$
    }

    class LoggerUtil {
        +info(String)$
        +error(String)$
        +debug(String)$
    }

    %% Inheritance Relationships
    Pekerja --|> Entitas
    Perusahaan --|> Entitas
    LowonganPekerjaan --|> Entitas
    Lamaran --|> Entitas

    InvalidLamaranException --|> Exception
    InvalidStatusTransitionException --|> Exception

    %% Composition/Association Relationships
    User "1" --> "1" Role : has
    Lamaran "1" --> "1" LowonganPekerjaan : applies for
    Lamaran "1" --> "1" Pekerja : belongs to
    Lamaran "1" --> "1" StatusLamaran : has status
    
    %% Controller Relationships
    LamaranController "1" --> "1" LowonganPekerjaanController : uses
    PekerjaController "1" --> "*" Pekerja : manages
    PerusahaanController "1" --> "*" Perusahaan : manages
    LowonganPekerjaanController "1" --> "*" LowonganPekerjaan : manages
    LamaranController "1" --> "*" Lamaran : manages
    UserController "1" --> "*" User : manages

    %% Validator & Util Associations
    LamaranValidator -.-> Lamaran : validates
    LamaranValidator -.-> LowonganPekerjaan : validates
    LamaranValidator -.-> Pekerja : validates
    DateUtil -.-> Lamaran : uses
    LoggerUtil -.-> LamaranController : uses
```

## Penjelasan Diagram

### Hierarki Class
- **Entitas** adalah abstract base class untuk semua model utama (Pekerja, Perusahaan, LowonganPekerjaan, Lamaran)
- Exception classes mewarisi dari Exception

### Model Classes
- **User**: Manages user authentication dengan role (ADMIN, STAFF, MANAGER, WORKER)
- **Pekerja**: Informasi pekerja (nama, umur, email, keahlian)
- **Perusahaan**: Informasi perusahaan (nama, bidang, deskripsi)
- **LowonganPekerjaan**: Lowongan pekerjaan dari perusahaan (gaji, kualifikasi, tanggal)
- **Lamaran**: Aplikasi pekerjaan dengan status (DIAJUKAN, DIREVIEW, DITERIMA, DITOLAK, DIBATALKAN)

### Controller Classes
- Setiap model memiliki controller untuk mengelola operasi CRUD
- **LamaranController** bergantung pada **LowonganPekerjaanController**

### Enum Types
- **StatusLamaran**: Status aplikasi dengan validasi transisi
- **Role**: Role pengguna dalam sistem

### Relationships
- **Inheritance** (--|>): Class yang lebih spesifik mewarisi dari class yang lebih umum
- **Composition** (-->): Class memiliki dan tergantung pada class lain
- **Dependency** (-.->): Class yang mempunyai ketergantungan fungsional
