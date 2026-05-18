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

import controllers.UserController;
import models.User.Role;

public class UserManagementView extends JFrame {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;  

    private JTable table;
    private DefaultTableModel tableModel;

    private UserController controller;

    public UserManagementView() {

        controller = new UserController();

        setTitle("Manajemen User - CariKerja");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Tambah Data");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Hapus");

        //ukuran tombol
        Dimension btnSize = new Dimension(130, 45);
        btnAdd.setPreferredSize(btnSize);
        btnEdit.setPreferredSize(btnSize);
        btnDelete.setPreferredSize(btnSize);

        //font tombol
        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        btnAdd.setFont(btnFont);
        btnEdit.setFont(btnFont);
        btnDelete.setFont(btnFont);

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        // tabel
        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Nama",
                        "Age",
                        "Email",
                        "Alamat",
                        "No HP",
                        "Role"
                },                0
        );

        table = new JTable(tableModel);

        //warna garis header tabele
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);           
        table.setIntercellSpacing(new Dimension(1, 1)); //jarak antar sel, biar garis kelihatan

        //tinggu baris
        table.setRowHeight(35);

        //buat bold, tengah, border
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setFont(new Font("Segoe UI", Font.BOLD, 15));
                setHorizontalAlignment(JLabel.CENTER);
                //border bawah
                setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
                return this;
        }
        };
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //font data
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        table.getColumnModel().getColumn(0).setPreferredWidth(50);   //id
        table.getColumnModel().getColumn(1).setPreferredWidth(180);  //nama
        table.getColumnModel().getColumn(2).setPreferredWidth(60);   //umur
        table.getColumnModel().getColumn(3).setPreferredWidth(200);  //email
        table.getColumnModel().getColumn(4).setPreferredWidth(150);  //alamat
        table.getColumnModel().getColumn(5).setPreferredWidth(120);  //hp
        table.getColumnModel().getColumn(6).setPreferredWidth(100);  //role

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setPreferredSize(new Dimension(0, 35));

        JScrollPane scrollPane = new JScrollPane(table);

        //load data
        loadDataFromTxt();

        //nambah komponen
        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBorder(BorderFactory.createEmptyBorder(10, 100, 100, 100)); // atas, kiri, bawah, kanan
        tableWrapper.add(scrollPane, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(tableWrapper, BorderLayout.CENTER);

        setVisible(true);

        //button
        btnAdd.addActionListener(e -> showFormDialog());

        btnEdit.addActionListener(e -> editData());

        btnDelete.addActionListener(e -> deleteData());
    }

        //load data txt
    private void loadDataFromTxt() {

        try {
            File file = new File("data/users.txt");
            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(
                    new FileReader(file)
            );

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(new Object[]{
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[5],
                        data[6],
                        data[7]
                });
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
        JTextField txtAge = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPassword = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtNoHp = new JTextField();

        JComboBox<String> cbRole = new JComboBox<>(
                new String[]{
                        "ADMIN",
                        "STAFF",
                        "MANAGER",
                        "WORKER"
                }
        );
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID User"));
        panel.add(txtId);

        panel.add(new JLabel("Nama"));
        panel.add(txtNama);

        panel.add(new JLabel("Age"));
        panel.add(txtAge);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("Password"));
        panel.add(txtPassword);

        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);

        panel.add(new JLabel("No HP"));
        panel.add(txtNoHp);

        panel.add(new JLabel("Role"));
        panel.add(cbRole);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah User",
                JOptionPane.OK_CANCEL_OPTION
        );

        //jika oke
        if (result == JOptionPane.OK_OPTION) {
            //tambah ke tabel
            tableModel.addRow(new Object[]{
                    txtId.getText(),
                    txtNama.getText(),
                    txtAge.getText(),
                    txtEmail.getText(),
                    txtAlamat.getText(),
                    txtNoHp.getText(),
                    cbRole.getSelectedItem()
            });

        //simpan ke txt
            controller.addUser(
                    Integer.parseInt(txtId.getText()),
                    txtNama.getText(),
                    Integer.parseInt(txtAge.getText()),
                    txtEmail.getText(),
                    txtPassword.getText(),
                    txtAlamat.getText(),
                    txtNoHp.getText(),
                    Role.valueOf(cbRole.getSelectedItem().toString())
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil ditambahkan!"
            );
        }
    }

        //edit data
    private void editData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data di tabel terlebih dahulu!"
            );
            return;
        }

        JTextField txtId = new JTextField(
                tableModel.getValueAt(row, 0).toString()
        );

        JTextField txtNama = new JTextField(
                tableModel.getValueAt(row, 1).toString()
        );

        JTextField txtAge = new JTextField(
                tableModel.getValueAt(row, 2).toString()
        );

        JTextField txtEmail = new JTextField(
                tableModel.getValueAt(row, 3).toString()
        );

        JTextField txtAlamat = new JTextField(
                tableModel.getValueAt(row, 4).toString()
        );

        JTextField txtNoHp = new JTextField(
                tableModel.getValueAt(row, 5).toString()
        );

        JComboBox<String> cbRole = new JComboBox<>(
                new String[]{
                        "ADMIN",
                        "STAFF",
                        "MANAGER",
                        "WORKER"
                }
        );

        cbRole.setSelectedItem(
                tableModel.getValueAt(row, 6).toString()
        );

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID"));
        panel.add(txtId);

        panel.add(new JLabel("Nama"));
        panel.add(txtNama);

        panel.add(new JLabel("Age"));
        panel.add(txtAge);

        panel.add(new JLabel("Email"));
        panel.add(txtEmail);

        panel.add(new JLabel("Alamat"));
        panel.add(txtAlamat);

        panel.add(new JLabel("No HP"));
        panel.add(txtNoHp);

        panel.add(new JLabel("Role"));
        panel.add(cbRole);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Data",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (result == JOptionPane.OK_OPTION) {
            tableModel.setValueAt(txtId.getText(), row, 0);
            tableModel.setValueAt(txtNama.getText(), row, 1);
            tableModel.setValueAt(txtAge.getText(), row, 2);
            tableModel.setValueAt(txtEmail.getText(), row, 3);
            tableModel.setValueAt(txtAlamat.getText(), row, 4);
            tableModel.setValueAt(txtNoHp.getText(), row, 5);
            tableModel.setValueAt(cbRole.getSelectedItem(), row, 6);

            saveTableToTxt();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil diupdate!"
            );
        }
    }

        //hapus data
    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data di tabel terlebih dahulu!"
            );
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus data ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(row);
            saveTableToTxt();
            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus!"
            );
        }
    }

        //simpan ke txt
    private void saveTableToTxt() {

        try {
            File file = new File("data/users.txt");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file)
            );
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(
                        tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2) + "," +
                        tableModel.getValueAt(i, 3) + "," +
                        "-" + "," +
                        tableModel.getValueAt(i, 4) + "," +
                        tableModel.getValueAt(i, 5) + "," +
                        tableModel.getValueAt(i, 6)
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

        SwingUtilities.invokeLater(() -> {
            new UserManagementView();
        });
    }
}