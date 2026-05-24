package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.PekerjaController;
import models.Pekerja;

public class PekerjaManagementPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel tableModel;

    private PekerjaController controller;

    public PekerjaManagementPanel() {

        controller = new PekerjaController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Pekerja");

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
                        "Nama",
                        "Umur",
                        "Email",
                        "No Telepon",
                        "Alamat",
                        "Keahlian"
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

        for (Pekerja p : controller.getAllPekerja()) {

            tableModel.addRow(new Object[]{
                    p.getIdPekerja(),
                    p.getNama(),
                    p.getUmur(),
                    p.getEmail(),
                    p.getNoTelepon(),
                    p.getAlamat(),
                    p.getKeahlian()
            });
        }
    }

    private void showFormDialog() {

        JTextField txtNama = new JTextField();
        JTextField txtUmur = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelepon = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtKeahlian = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Nama"));
        panel.add(txtNama);

        panel.add(new JLabel("Umur"));
        panel.add(txtUmur);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("No Telepon"));
        panel.add(txtTelepon);

        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);

        panel.add(new JLabel("Keahlian"));
        panel.add(txtKeahlian);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Pekerja",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                int idPekerja = controller.getNextId();
                int umur = Integer.parseInt(txtUmur.getText());

                boolean success = controller.addPekerja(
                        idPekerja,
                        txtNama.getText(),
                        umur,
                        txtEmail.getText(),
                        txtTelepon.getText(),
                        txtAlamat.getText(),
                        txtKeahlian.getText());

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data pekerja berhasil ditambahkan!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Umur harus berupa angka!");
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

        JTextField txtNama = new JTextField(
                tableModel.getValueAt(row, 1).toString());

        JTextField txtUmur = new JTextField(
                tableModel.getValueAt(row, 2).toString());

        JTextField txtEmail = new JTextField(
                tableModel.getValueAt(row, 3).toString());

        JTextField txtTelepon = new JTextField(
                tableModel.getValueAt(row, 4).toString());

        JTextField txtAlamat = new JTextField(
                tableModel.getValueAt(row, 5).toString());

        JTextField txtKeahlian = new JTextField(
                tableModel.getValueAt(row, 6).toString());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Nama"));
        panel.add(txtNama);

        panel.add(new JLabel("Umur"));
        panel.add(txtUmur);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("No Telepon"));
        panel.add(txtTelepon);

        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);

        panel.add(new JLabel("Keahlian"));
        panel.add(txtKeahlian);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Pekerja",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                int umur = Integer.parseInt(txtUmur.getText());

                boolean success = controller.editPekerja(
                        id,
                        txtNama.getText(),
                        umur,
                        txtEmail.getText(),
                        txtTelepon.getText(),
                        txtAlamat.getText(),
                        txtKeahlian.getText());

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data pekerja berhasil diupdate!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Umur harus berupa angka!");
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

            boolean success = controller.deletePekerja(id);

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data pekerja berhasil dihapus!");
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
            }
        }
    }
}