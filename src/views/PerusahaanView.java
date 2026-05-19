package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controllers.PerusahaanController;

public class PerusahaanView extends JFrame {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;  

    private JTable table;
    private DefaultTableModel tableModel;

    private PerusahaanController controller;

    public PerusahaanView() {
        controller = new PerusahaanController();

        setTitle("Manajemen Perusahaan - CariKerja");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Tambah Data");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Hapus");

        Dimension btnSize = new Dimension(130, 45);
        btnAdd.setPreferredSize(btnSize);
        btnEdit.setPreferredSize(btnSize);
        btnDelete.setPreferredSize(btnSize);

        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        btnAdd.setFont(btnFont);
        btnEdit.setFont(btnFont);
        btnDelete.setFont(btnFont);

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Nama Perusahaan",
                        "Alamat",
                        "Email",
                        "Telepon",
                        "Bidang Usaha"
                }, 0
        );

        table = new JTable(tableModel);

        //tampilan tabel
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setRowHeight(35);

        //eader bold, center, border bawah
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(new Font("Segoe UI", Font.BOLD, 15));
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
                return this;
            }
        };
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //data di tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nama Perusahaan
        table.getColumnModel().getColumn(2).setPreferredWidth(180);  // Alamat
        table.getColumnModel().getColumn(3).setPreferredWidth(200);  // Email
        table.getColumnModel().getColumn(4).setPreferredWidth(120);  // Telepon
        table.getColumnModel().getColumn(5).setPreferredWidth(150);  // Bidang Usaha

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(table);

        //bungkus tabel ke margin
        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createEmptyBorder(10, 100, 100, 100));
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(tableWrapper, BorderLayout.CENTER);

        loadDataFromTxt();

        setVisible(true);

        //tombol
        btnAdd.addActionListener(e -> showFormDialog());
        btnEdit.addActionListener(e -> editData());
        btnDelete.addActionListener(e -> deleteData());
    }

    //data dari perusahaan.txt
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
                if (data.length == 6) {
                    tableModel.addRow(new Object[]{
                        data[0], data[1], data[2], data[3], data[4], data[5]
                    });
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //form tambah data
    private void showFormDialog() {
        JTextField txtId = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelepon = new JTextField();
        JTextField txtBidang = new JTextField();

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
        panel.add(new JLabel("Telepon"));
        panel.add(txtTelepon);
        panel.add(new JLabel("Bidang Usaha"));
        panel.add(txtBidang);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Perusahaan", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            tableModel.addRow(new Object[]{
                txtId.getText(),
                txtNama.getText(),
                txtAlamat.getText(),
                txtEmail.getText(),
                txtTelepon.getText(),
                txtBidang.getText()
            });
            saveTableToTxt();
            controller.addPerusahaan(
                Integer.parseInt(txtId.getText()),
                txtNama.getText(),
                txtAlamat.getText(),
                txtEmail.getText(),
                txtTelepon.getText(),
                txtBidang.getText()
            );
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        }
    }

    //edit 
    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        JTextField txtId = new JTextField(tableModel.getValueAt(row, 0).toString());
        JTextField txtNama = new JTextField(tableModel.getValueAt(row, 1).toString());
        JTextField txtAlamat = new JTextField(tableModel.getValueAt(row, 2).toString());
        JTextField txtEmail = new JTextField(tableModel.getValueAt(row, 3).toString());
        JTextField txtTelepon = new JTextField(tableModel.getValueAt(row, 4).toString());
        JTextField txtBidang = new JTextField(tableModel.getValueAt(row, 5).toString());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID"));
        panel.add(txtId);
        panel.add(new JLabel("Nama Perusahaan"));
        panel.add(txtNama);
        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);
        panel.add(new JLabel("Email"));
        panel.add(txtEmail);
        panel.add(new JLabel("Telepon"));
        panel.add(txtTelepon);
        panel.add(new JLabel("Bidang Usaha"));
        panel.add(txtBidang);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Data", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            tableModel.setValueAt(txtId.getText(), row, 0);
            tableModel.setValueAt(txtNama.getText(), row, 1);
            tableModel.setValueAt(txtAlamat.getText(), row, 2);
            tableModel.setValueAt(txtEmail.getText(), row, 3);
            tableModel.setValueAt(txtTelepon.getText(), row, 4);
            tableModel.setValueAt(txtBidang.getText(), row, 5);
            saveTableToTxt();
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        }
    }

    //hapus
    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            saveTableToTxt();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        }
    }

    //simpan ke txt
    private void saveTableToTxt() {
        try {
            File file = new File("data/perusahaan.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(
                    tableModel.getValueAt(i, 0) + "," +
                    tableModel.getValueAt(i, 1) + "," +
                    tableModel.getValueAt(i, 2) + "," +
                    tableModel.getValueAt(i, 3) + "," +
                    tableModel.getValueAt(i, 4) + "," +
                    tableModel.getValueAt(i, 5)
                );
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new PerusahaanView());
    }
}
