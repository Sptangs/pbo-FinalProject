package views;

import controllers.LowonganPekerjaanController;
import exceptions.PrioritasInvalidException;
import models.LowonganPekerjaan;
import models.LowonganPrioritas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class LowonganManagementPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnEdit, btnDelete, btnRefresh;
    private JButton btnAddPrioritas, btnFilterPrioritas, btnSortPrioritas;
    private LowonganPekerjaanController controller;

    public LowonganManagementPanel() {
        controller = new LowonganPekerjaanController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel titleLabel = new JLabel("Manajemen Lowongan");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // Tombol atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(Color.WHITE);

        btnAdd = createButton("Tambah");
        btnEdit = createButton("Edit");
        btnDelete = createButton("Hapus");
        btnRefresh = createButton("Refresh");
        btnAddPrioritas = createButton("Tambah Prioritas");
        btnFilterPrioritas = createButton("Filter Prioritas");
        btnSortPrioritas = createButton("Urut Prioritas");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);
        topPanel.add(btnRefresh);
        topPanel.add(btnAddPrioritas);
        topPanel.add(btnFilterPrioritas);
        topPanel.add(btnSortPrioritas);

        // Tabel
        String[] columns = { "ID", "Perusahaan", "Judul", "Deskripsi", "Lokasi", "Gaji", "Prioritas" };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        refreshTable();

        // Event
        btnAdd.addActionListener(e -> tambahLowongan());
        btnEdit.addActionListener(e -> editLowongan());
        btnDelete.addActionListener(e -> deleteLowongan());
        btnRefresh.addActionListener(e -> refreshTable());
        btnAddPrioritas.addActionListener(e -> tambahLowonganPrioritas());
        btnFilterPrioritas.addActionListener(e -> filterPrioritas());
        btnSortPrioritas.addActionListener(e -> sortPrioritas());
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 35));
        return btn;
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<LowonganPekerjaan> list = controller.getAllLowongan();
        for (LowonganPekerjaan l : list) {
            String prioritas = "";
            if (l instanceof LowonganPrioritas) {
                prioritas = String.valueOf(((LowonganPrioritas) l).getPrioritas());
            }
            tableModel.addRow(new Object[] {
                    l.getId(),
                    l.getPerusahaanNama(),
                    l.getJudul(),
                    l.getDeskripsi(),
                    l.getLokasi(),
                    l.getGajiMin() + " - " + l.getGajiMax(),
                    prioritas
            });
        }
    }

    private void tambahLowongan() {
        JOptionPane.showMessageDialog(this, "Fitur tambah lowongan biasa belum diimplementasikan.");
    }

    private void editLowongan() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih lowongan yang akan diedit!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Fitur edit belum diimplementasikan.");
    }

    private void deleteLowongan() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih lowongan yang akan dihapus!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        if (controller.deleteLowongan(id)) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Lowongan berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, controller.getMessage());
        }
    }

    //fitur prioritas uas
    private void tambahLowonganPrioritas() {
        JTextField txtId = new JTextField();
        JTextField txtPerusahaan = new JTextField();
        JTextField txtJudul = new JTextField();
        JTextField txtDeskripsi = new JTextField();
        JTextField txtLokasi = new JTextField();
        JTextField txtGaji = new JTextField();
        JTextField txtPrioritas = new JTextField();

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Perusahaan:"));
        panel.add(txtPerusahaan);
        panel.add(new JLabel("Judul:"));
        panel.add(txtJudul);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(txtDeskripsi);
        panel.add(new JLabel("Lokasi:"));
        panel.add(txtLokasi);
        panel.add(new JLabel("Gaji:"));
        panel.add(txtGaji);
        panel.add(new JLabel("Prioritas (1-3):"));
        panel.add(txtPrioritas);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Lowongan Prioritas",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String perusahaan = txtPerusahaan.getText().trim();
                String judul = txtJudul.getText().trim();
                String deskripsi = txtDeskripsi.getText().trim();
                String lokasi = txtLokasi.getText().trim();
                int gaji = Integer.parseInt(txtGaji.getText().trim());
                int prioritas = Integer.parseInt(txtPrioritas.getText().trim());

                if (prioritas < 1 || prioritas > 3) {
                    throw new PrioritasInvalidException("Prioritas harus antara 1-3!");
                }

                LowonganPrioritas lp = new LowonganPrioritas(
                        id, perusahaan, judul, deskripsi, "", lokasi, "",
                        gaji, gaji,
                        LocalDate.now(), LocalDate.now().plusMonths(1), true, prioritas);

                boolean berhasil = controller.addLowonganPrioritas(lp);
                if (berhasil) {
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Lowongan prioritas berhasil ditambahkan!");
                } else {
                    JOptionPane.showMessageDialog(this, controller.getMessage());
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID, Gaji, dan Prioritas harus berupa angka!");
            } catch (PrioritasInvalidException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validasi Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void filterPrioritas() {
        List<LowonganPekerjaan> filtered = controller.filterPrioritasTinggi();
        if (filtered.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada lowongan prioritas tinggi (prioritas >=2)!");
            return;
        }
        tableModel.setRowCount(0);
        for (LowonganPekerjaan l : filtered) {
            String prioritas = "";
            if (l instanceof LowonganPrioritas) {
                prioritas = String.valueOf(((LowonganPrioritas) l).getPrioritas());
            }
            tableModel.addRow(new Object[] {
                    l.getId(),
                    l.getPerusahaanNama(),
                    l.getJudul(),
                    l.getDeskripsi(),
                    l.getLokasi(),
                    l.getGajiMin() + " - " + l.getGajiMax(),
                    prioritas
            });
        }
    }

    private void sortPrioritas() {
        controller.sortByPrioritas();
        refreshTable();
        JOptionPane.showMessageDialog(this, "Data telah diurutkan berdasarkan prioritas (tertinggi ke terendah)");
    }
}