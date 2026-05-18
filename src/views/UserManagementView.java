package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
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

        // =========================
        // TOP PANEL
        // =========================
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Tambah Data");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Hapus");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        // =========================
        // TABLE
        // =========================
        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Nama",
                        "Age",
                        "Email",
                        "Alamat",
                        "No HP",
                        "Role"
                },
                0
        );

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        // =========================
        // LOAD DATA
        // =========================
        loadDataFromTxt();

        // =========================
        // ADD COMPONENT
        // =========================
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        // =========================
        // EVENT BUTTON
        // =========================
        btnAdd.addActionListener(e -> showFormDialog());

        btnEdit.addActionListener(e -> editData());

        btnDelete.addActionListener(e -> deleteData());
    }

    // =========================
    // LOAD DATA TXT
    // =========================
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

    // =========================
    // FORM TAMBAH DATA
    // =========================
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

        // =========================
        // JIKA OK
        // =========================
        if (result == JOptionPane.OK_OPTION) {

            // Tambah ke tabel
            tableModel.addRow(new Object[]{
                    txtId.getText(),
                    txtNama.getText(),
                    txtAge.getText(),
                    txtEmail.getText(),
                    txtAlamat.getText(),
                    txtNoHp.getText(),
                    cbRole.getSelectedItem()
            });

            // Simpan ke txt
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

    // =========================
    // EDIT DATA
    // =========================
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

    // =========================
    // DELETE DATA
    // =========================
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

    // =========================
    // SAVE TABLE TO TXT
    // =========================
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

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new UserManagementView();
        });

    }
}