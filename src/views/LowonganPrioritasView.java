package views;

import controllers.LowonganPrioritasController;
import exceptions.InputTidakValidException;
import models.LowonganPrioritas;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LowonganPrioritasView {

    private final Scanner input;
    private final LowonganPrioritasController controller;

    public LowonganPrioritasView() {
        input = new Scanner(System.in);
        controller = new LowonganPrioritasController();
    }

    public void showMenu() {

        int pilihan = -1;

        do {

            System.out.println("\n=================================");
            System.out.println("     MENU LOWONGAN PRIORITAS");
            System.out.println("=================================");
            System.out.println("1. Tambah Lowongan Prioritas");
            System.out.println("2. Tampilkan Semua Prioritas");
            System.out.println("3. Filter Prioritas Tinggi");
            System.out.println("0. Kembali");
            System.out.print("Pilih Menu : ");

            try {

                pilihan = Integer.parseInt(input.nextLine());

                switch (pilihan) {

                    case 1:
                        tambahLowonganPrioritas();
                        break;

                    case 2:
                        tampilkanSemua();
                        break;

                    case 3:
                        filterPrioritasTinggi();
                        break;

                    case 0:
                        System.out.println("Kembali...");
                        break;

                    default:
                        System.out.println("Menu tidak tersedia.");
                }

            } catch (NumberFormatException e) {

                System.out.println("Input menu harus berupa angka.");

            }

        } while (pilihan != 0);
    }

    private void tambahLowonganPrioritas() {

        try {

            System.out.println("\n=== TAMBAH LOWONGAN PRIORITAS ===");

            System.out.print("Nama Perusahaan : ");
            String perusahaan = input.nextLine();

            System.out.print("Judul Lowongan : ");
            String judul = input.nextLine();

            System.out.print("Deskripsi : ");
            String deskripsi = input.nextLine();

            System.out.print("Kualifikasi : ");
            String kualifikasi = input.nextLine();

            System.out.print("Lokasi : ");
            String lokasi = input.nextLine();

            System.out.print("Jenis Pekerjaan : ");
            String jenis = input.nextLine();

            System.out.print("Gaji Minimum : ");
            int gajiMin =
                    Integer.parseInt(input.nextLine());

            System.out.print("Gaji Maksimum : ");
            int gajiMax =
                    Integer.parseInt(input.nextLine());

            System.out.print("Tanggal Tutup (yyyy-MM-dd) : ");
            LocalDate tanggalTutup =
                    LocalDate.parse(input.nextLine());

            System.out.print("Level Prioritas : ");
            String levelPrioritas =
                    input.nextLine();

            if (levelPrioritas.trim().isEmpty()) {

                throw new InputTidakValidException(
                        "Level prioritas tidak boleh kosong.");
            }

            System.out.print("Biaya Promosi : ");
            int biayaPromosi =
                    Integer.parseInt(input.nextLine());

            System.out.print("Tampil Di Atas (true/false) : ");
            boolean tampilDiAtas =
                    Boolean.parseBoolean(input.nextLine());

            controller.tambahLowonganPrioritas(
                    perusahaan,
                    judul,
                    deskripsi,
                    kualifikasi,
                    lokasi,
                    jenis,
                    gajiMin,
                    gajiMax,
                    tanggalTutup,
                    levelPrioritas,
                    biayaPromosi,
                    tampilDiAtas
            );

            System.out.println(
                    "\nLowongan prioritas berhasil ditambahkan.");

        } catch (InputTidakValidException e) {

            System.out.println(
                    "Error : " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println(
                    "Error : Input angka tidak valid.");

        } catch (Exception e) {

            System.out.println(
                    "Error : " + e.getMessage());
        }
    }

    private void tampilkanSemua() {

        List<LowonganPrioritas> daftar =
                controller.getLowonganPrioritas();

        System.out.println(
                "\n===== DAFTAR LOWONGAN PRIORITAS =====");

        if (daftar.isEmpty()) {

            System.out.println("Belum ada data.");
            return;
        }

        for (LowonganPrioritas lp : daftar) {

            System.out.println(
                    lp.tampilkanDetail());

            System.out.println(
                    "-----------------------------------");
        }
    }

    private void filterPrioritasTinggi() {

        List<LowonganPrioritas> daftar =
                controller.getLowonganPrioritas();

        System.out.println(
                "\n===== PRIORITAS TINGGI =====");

        boolean ditemukan = false;

        for (LowonganPrioritas lp : daftar) {

            if (lp.getLevelPrioritas()
                    .equalsIgnoreCase("TINGGI")) {

                System.out.println(
                        lp.tampilkanDetail());

                System.out.println(
                        "-----------------------------------");

                ditemukan = true;
            }
        }

        if (!ditemukan) {

            System.out.println(
                    "Tidak ada lowongan prioritas tinggi.");
        }
    }
}