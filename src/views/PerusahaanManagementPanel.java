package views;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.PerusahaanController;

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
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Perusahaan");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        btnAdd = createButton("Tambah");
        btnEdit = createButton("Edit");
        btnDelete = createButton("Hapus");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
                new String[] {
                        "ID Perusahaan",
                        "Nama Perusahaan",
                        "Alamat",
                        "Email",
                        "No Telepon",
                        "Bidang",
                        "Deskripsi"
                }, 0);

        table = new JTable(tableModel);

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13));

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 40));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centerRenderer);
        }

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.getColumnModel().getColumn(6).setPreferredWidth(250);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(Color.WHITE);
        tableWrapper.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 20, 15));

        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        loadDataFromTxt();

        add(topPanel, BorderLayout.CENTER);
        add(tableWrapper, BorderLayout.SOUTH);

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
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void loadDataFromTxt() {
        try {
            File file = new File("data/perusahaan.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 7) {
                    tableModel.addRow(new Object[] {
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            data[4],
                            data[5],
                            data[6]
                    });
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFormDialog() {
        JTextField txtId = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelepon = new JTextField();
        JTextField txtBidang = new JTextField();
        JTextArea txtDeskripsi = new JTextArea(4, 20);

        JScrollPane descScroll = new JScrollPane(txtDeskripsi);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID Perusahaan"));
        panel.add(txtId);

        panel.add(new JLabel("Nama Perusahaan"));
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
        panel.add(descScroll);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Perusahaan",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                tableModel.addRow(new Object[] {
                        txtId.getText(),
                        txtNama.getText(),
                        txtAlamat.getText(),
                        txtEmail.getText(),
                        txtTelepon.getText(),
                        txtBidang.getText(),
                        txtDeskripsi.getText()
                });

                saveTableToTxt();

                controller.addPerusahaan(
                        Integer.parseInt(txtId.getText()),
                        txtNama.getText(),
                        txtAlamat.getText(),
                        txtEmail.getText(),
                        txtTelepon.getText(),
                        txtBidang.getText(),
                        txtDeskripsi.getText());

                JOptionPane.showMessageDialog(
                        this,
                        "Data perusahaan berhasil ditambahkan!");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + e.getMessage());
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

        JTextField txtId = new JTextField(tableModel.getValueAt(row, 0).toString());

        JTextField txtNama = new JTextField(tableModel.getValueAt(row, 1).toString());

        JTextField txtAlamat = new JTextField(tableModel.getValueAt(row, 2).toString());

        JTextField txtEmail = new JTextField(tableModel.getValueAt(row, 3).toString());

        JTextField txtTelepon = new JTextField(tableModel.getValueAt(row, 4).toString());

        JTextField txtBidang = new JTextField(tableModel.getValueAt(row, 5).toString());

        JTextArea txtDeskripsi = new JTextArea(tableModel.getValueAt(row, 6).toString());

        JScrollPane descScroll = new JScrollPane(txtDeskripsi);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID Perusahaan"));
        panel.add(txtId);

        panel.add(new JLabel("Nama Perusahaan"));
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
        panel.add(descScroll);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Perusahaan",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            tableModel.setValueAt(txtId.getText(), row, 0);
            tableModel.setValueAt(txtNama.getText(), row, 1);
            tableModel.setValueAt(txtAlamat.getText(), row, 2);
            tableModel.setValueAt(txtEmail.getText(), row, 3);
            tableModel.setValueAt(txtTelepon.getText(), row, 4);
            tableModel.setValueAt(txtBidang.getText(), row, 5);
            tableModel.setValueAt(txtDeskripsi.getText(), row, 6);

            saveTableToTxt();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil diupdate!");
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
            tableModel.removeRow(row);
            saveTableToTxt();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus!");
        }
    }

    private void saveTableToTxt() {
        try {
            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, "perusahaan.txt");

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(
                        tableModel.getValueAt(i, 0) + "," +
                                tableModel.getValueAt(i, 1) + "," +
                                tableModel.getValueAt(i, 2) + "," +
                                tableModel.getValueAt(i, 3) + "," +
                                tableModel.getValueAt(i, 4) + "," +
                                tableModel.getValueAt(i, 5) + "," +
                                tableModel.getValueAt(i, 6));

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
