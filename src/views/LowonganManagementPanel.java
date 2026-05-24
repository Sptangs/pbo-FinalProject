package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.LowonganPekerjaanController;
import models.LowonganPekerjaan;

public class LowonganManagementPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel tableModel;

    private LowonganPekerjaanController controller;

    public LowonganManagementPanel() {

        controller = new LowonganPekerjaanController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Lowongan Pekerjaan");

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 10));

        topPanel.setBackground(Color.WHITE);

        btnAdd = createButton("Tambah");
        btnEdit = createButton("Edit");
        btnDelete = createButton("Hapus");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Perusahaan ID",
                        "Judul",
                        "Deskripsi",
                        "Kualifikasi",
                        "Lokasi",
                        "Jenis",
                        "Gaji Min",
                        "Gaji Max",
                        "Tanggal Tutup"
                },
                0);

        table = new JTable(tableModel);

        table.setRowHeight(32);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13));

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 38));

        table.setSelectionBackground(
                new Color(52, 152, 219));

        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.setBackground(Color.WHITE);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshTable();

        btnAdd.addActionListener(e -> showFormDialog());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> deleteData());
    }

    private JButton createButton(String text) {

        JButton btn = new JButton(text);

        btn.setBackground(new Color(52, 152, 219));

        btn.setForeground(Color.WHITE);

        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btn.setFocusPainted(false);

        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.setPreferredSize(new Dimension(120, 35));

        return btn;
    }

    private void refreshTable() {

        tableModel.setRowCount(0);

        for (LowonganPekerjaan l : controller.getAllLowongan()) {

            tableModel.addRow(new Object[]{
                    l.getId(),
                    l.getPerusahaanId(),
                    l.getJudul(),
                    l.getDeskripsi(),
                    l.getKualifikasi(),
                    l.getLokasi(),
                    l.getJenis(),
                    l.getGajiMin(),
                    l.getGajiMax(),
                    l.getTanggalTutup()
            });
        }
    }

    private void showFormDialog() {

        JTextField txtPerusahaanId = new JTextField();
        JTextField txtJudul = new JTextField();
        JTextField txtDeskripsi = new JTextField();
        JTextField txtKualifikasi = new JTextField();
        JTextField txtLokasi = new JTextField();
        JTextField txtJenis = new JTextField();
        JTextField txtGajiMin = new JTextField();
        JTextField txtGajiMax = new JTextField();
        JTextField txtTanggalTutup = new JTextField();

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));

        panel.add(new JLabel("Perusahaan ID"));
        panel.add(txtPerusahaanId);

        panel.add(new JLabel("Judul"));
        panel.add(txtJudul);

        panel.add(new JLabel("Deskripsi"));
        panel.add(txtDeskripsi);

        panel.add(new JLabel("Kualifikasi"));
        panel.add(txtKualifikasi);

        panel.add(new JLabel("Lokasi"));
        panel.add(txtLokasi);

        panel.add(new JLabel("Jenis"));
        panel.add(txtJenis);

        panel.add(new JLabel("Gaji Min"));
        panel.add(txtGajiMin);

        panel.add(new JLabel("Gaji Max"));
        panel.add(txtGajiMax);

        panel.add(new JLabel("Tanggal Tutup (yyyy-MM-dd)"));
        panel.add(txtTanggalTutup);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Lowongan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                int perusahaanId = Integer.parseInt(txtPerusahaanId.getText());

                boolean success = controller.addLowongan(
                        perusahaanId,
                        txtJudul.getText(),
                        txtDeskripsi.getText(),
                        txtKualifikasi.getText(),
                        txtLokasi.getText(),
                        txtJenis.getText(),
                        txtGajiMin.getText(),
                        txtGajiMax.getText(),
                        txtTanggalTutup.getText());

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data lowongan berhasil ditambahkan!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Perusahaan ID dan Gaji harus berupa angka!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage());
            }
        }
    }

    private void editData() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!");

            return;
        }

        int id = Integer.parseInt(
                tableModel.getValueAt(row, 0).toString());

        JTextField txtJudul = new JTextField(
                tableModel.getValueAt(row, 2).toString());

        JTextField txtDeskripsi = new JTextField(
                tableModel.getValueAt(row, 3).toString());

        JTextField txtKualifikasi = new JTextField(
                tableModel.getValueAt(row, 4).toString());

        JTextField txtLokasi = new JTextField(
                tableModel.getValueAt(row, 5).toString());

        JTextField txtJenis = new JTextField(
                tableModel.getValueAt(row, 6).toString());

        JTextField txtGajiMin = new JTextField(
                tableModel.getValueAt(row, 7).toString());

        JTextField txtGajiMax = new JTextField(
                tableModel.getValueAt(row, 8).toString());

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Judul"));
        panel.add(txtJudul);

        panel.add(new JLabel("Deskripsi"));
        panel.add(txtDeskripsi);

        panel.add(new JLabel("Kualifikasi"));
        panel.add(txtKualifikasi);

        panel.add(new JLabel("Lokasi"));
        panel.add(txtLokasi);

        panel.add(new JLabel("Jenis"));
        panel.add(txtJenis);

        panel.add(new JLabel("Gaji Min"));
        panel.add(txtGajiMin);

        panel.add(new JLabel("Gaji Max"));
        panel.add(txtGajiMax);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Lowongan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                boolean success = controller.editLowongan(
                        id,
                        txtJudul.getText(),
                        txtDeskripsi.getText(),
                        txtKualifikasi.getText(),
                        txtLokasi.getText(),
                        txtJenis.getText(),
                        txtGajiMin.getText(),
                        txtGajiMax.getText());

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data lowongan berhasil diupdate!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gaji harus berupa angka!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage());
            }
        }
    }

    private void deleteData() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!");

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(
                    tableModel.getValueAt(row, 0).toString());

            boolean success = controller.deleteLowongan(id);

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data lowongan berhasil dihapus!");
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
            }
        }
    }
}
