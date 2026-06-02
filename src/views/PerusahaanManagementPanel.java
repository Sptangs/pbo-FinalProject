package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.PerusahaanController;
import models.Perusahaan;

public class PerusahaanManagementPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel tableModel;

    private PerusahaanController controller;

    public PerusahaanManagementPanel() {

        controller = new PerusahaanController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Perusahaan");

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
                new String[] {
                        "ID",
                        "Nama Perusahaan",
                        "Alamat",
                        "Email",
                        "No Telepon",
                        "Bidang",
                        "Deskripsi"
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

        for (Perusahaan perusahaan : controller.getAllPerusahaan()) {

            tableModel.addRow(new Object[] {
                    perusahaan.getIdPerusahaan(),
                    perusahaan.getNamaPerusahaan(),
                    perusahaan.getAlamat(),
                    perusahaan.getEmail(),
                    perusahaan.getNoTelepon(),
                    perusahaan.getBidang(),
                    perusahaan.getDeskripsi()
            });
        }
    }

    private void showFormDialog() {

        JTextField txtNama = new JTextField();

        JTextField txtAlamat = new JTextField();

        JTextField txtEmail = new JTextField();

        JTextField txtTelepon = new JTextField();

        JTextField txtBidang = new JTextField();

        JTextArea txtDeskripsi = new JTextArea(4, 20);

        JScrollPane scrollDesc = new JScrollPane(txtDeskripsi);

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

        Dimension fieldSize = new Dimension(260, 32);

        txtNama.setPreferredSize(fieldSize);
        txtAlamat.setPreferredSize(fieldSize);
        txtEmail.setPreferredSize(fieldSize);
        txtTelepon.setPreferredSize(fieldSize);
        txtBidang.setPreferredSize(fieldSize);

        int y = 0;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblNama = new JLabel("Nama Perusahaan");

        lblNama.setFont(labelFont);

        panel.add(lblNama, gbc);

        gbc.gridx = 1;

        panel.add(txtNama, gbc);

        y++;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblAlamat = new JLabel("Alamat");

        lblAlamat.setFont(labelFont);

        panel.add(lblAlamat, gbc);

        gbc.gridx = 1;

        panel.add(txtAlamat, gbc);

        y++;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblEmail = new JLabel("Email");

        lblEmail.setFont(labelFont);

        panel.add(lblEmail, gbc);

        gbc.gridx = 1;

        panel.add(txtEmail, gbc);

        y++;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblTelepon = new JLabel("No Telepon");

        lblTelepon.setFont(labelFont);

        panel.add(lblTelepon, gbc);

        gbc.gridx = 1;

        panel.add(txtTelepon, gbc);

        y++;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblBidang = new JLabel("Bidang");

        lblBidang.setFont(labelFont);

        panel.add(lblBidang, gbc);

        gbc.gridx = 1;

        panel.add(txtBidang, gbc);

        y++;

        gbc.gridx = 0;
        gbc.gridy = y;

        JLabel lblDeskripsi = new JLabel("Deskripsi");

        lblDeskripsi.setFont(labelFont);

        panel.add(lblDeskripsi, gbc);

        gbc.gridx = 1;

        panel.add(scrollDesc, gbc);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Perusahaan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            boolean success = controller.addPerusahaan(
                    txtNama.getText(),
                    txtAlamat.getText(),
                    txtEmail.getText(),
                    txtTelepon.getText(),
                    txtBidang.getText(),
                    txtDeskripsi.getText());

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data perusahaan berhasil ditambahkan!");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
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

        JTextField txtNama = new JTextField(
                tableModel.getValueAt(row, 1).toString());

        JTextField txtAlamat = new JTextField(
                tableModel.getValueAt(row, 2).toString());

        JTextField txtEmail = new JTextField(
                tableModel.getValueAt(row, 3).toString());

        JTextField txtTelepon = new JTextField(
                tableModel.getValueAt(row, 4).toString());

        JTextField txtBidang = new JTextField(
                tableModel.getValueAt(row, 5).toString());

        JTextArea txtDeskripsi = new JTextArea(
                tableModel.getValueAt(row, 6).toString());

        JScrollPane scrollDesc = new JScrollPane(txtDeskripsi);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Nama"));
        panel.add(txtNama);

        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("No Telepon"));
        panel.add(txtTelepon);

        panel.add(new JLabel("Bidang"));
        panel.add(txtBidang);

        panel.add(new JLabel("Deskripsi"));
        panel.add(scrollDesc);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Perusahaan",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            int id = Integer.parseInt(
                    tableModel.getValueAt(row, 0).toString());

            boolean success = controller.editPerusahaan(
                    id,
                    txtNama.getText(),
                    txtAlamat.getText(),
                    txtEmail.getText(),
                    txtTelepon.getText(),
                    txtBidang.getText(),
                    txtDeskripsi.getText());

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil diupdate!");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
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

            boolean success = controller.deletePerusahaan(id);

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil dihapus!");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
            }
        }
    }
}