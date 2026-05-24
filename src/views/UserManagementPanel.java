package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.UserController;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import models.User.Role;

public class UserManagementPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel tableModel;

    private UserController controller;

    public UserManagementPanel() {
        controller = new UserController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen User");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        btnAdd = createButton("Tambah Data");
        btnEdit = createButton("Edit");
        btnDelete = createButton("Hapus");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
                new String[] {
                        "ID",
                        "Nama",
                        "Age",
                        "Email",
                        "Alamat",
                        "No HP",
                        "Role"
                },
                0);

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setBackground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setBackground(Color.WHITE);
        tableWrapper.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
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
            File file = new File("data/users.txt");

            if (!file.exists()) {
                return;
            }

            BufferedReader reader = new BufferedReader(
                    new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                tableModel.addRow(new Object[] {
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

    private void showFormDialog() {
        JTextField txtId = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPassword = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtNoHp = new JTextField();
        JComboBox<Role> comboRole = new JComboBox<>(Role.values());

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nama:"));
        panel.add(txtNama);
        panel.add(new JLabel("Age:"));
        panel.add(txtAge);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);
        panel.add(new JLabel("Alamat:"));
        panel.add(txtAlamat);
        panel.add(new JLabel("No HP:"));
        panel.add(txtNoHp);
        panel.add(new JLabel("Role:"));
        panel.add(comboRole);

        int result = JOptionPane.showConfirmDialog(this, panel, "Tambah Data User",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nama = txtNama.getText();
                int age = Integer.parseInt(txtAge.getText());
                String email = txtEmail.getText();
                String password = txtPassword.getText();
                String alamat = txtAlamat.getText();
                String noHp = txtNoHp.getText();
                Role role = (Role) comboRole.getSelectedItem();

                controller.addUser(id, nama, age, email, password, alamat, noHp, role);

                tableModel.addRow(new Object[] { id, nama, age, email, alamat, noHp, role });

                saveDataToTxt();

                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void editData() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diedit!");
            return;
        }

        JTextField txtId = new JTextField((String) tableModel.getValueAt(selectedRow, 0));
        JTextField txtNama = new JTextField((String) tableModel.getValueAt(selectedRow, 1));
        JTextField txtAge = new JTextField(String.valueOf(tableModel.getValueAt(selectedRow, 2)));
        JTextField txtEmail = new JTextField((String) tableModel.getValueAt(selectedRow, 3));
        JTextField txtAlamat = new JTextField((String) tableModel.getValueAt(selectedRow, 4));
        JTextField txtNoHp = new JTextField((String) tableModel.getValueAt(selectedRow, 5));
        JComboBox<Role> comboRole = new JComboBox<>(Role.values());
        comboRole.setSelectedItem(tableModel.getValueAt(selectedRow, 6));

        txtId.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nama:"));
        panel.add(txtNama);
        panel.add(new JLabel("Age:"));
        panel.add(txtAge);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Alamat:"));
        panel.add(txtAlamat);
        panel.add(new JLabel("No HP:"));
        panel.add(txtNoHp);
        panel.add(new JLabel("Role:"));
        panel.add(comboRole);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Data User",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                String nama = txtNama.getText();
                int age = Integer.parseInt(txtAge.getText());
                String email = txtEmail.getText();
                String alamat = txtAlamat.getText();
                String noHp = txtNoHp.getText();
                Role role = (Role) comboRole.getSelectedItem();

                controller.updateUser(id, nama, age, email, "", alamat, noHp, role);

                tableModel.setValueAt(nama, selectedRow, 1);
                tableModel.setValueAt(age, selectedRow, 2);
                tableModel.setValueAt(email, selectedRow, 3);
                tableModel.setValueAt(alamat, selectedRow, 4);
                tableModel.setValueAt(noHp, selectedRow, 5);
                tableModel.setValueAt(role, selectedRow, 6);

                saveDataToTxt();

                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }

        int id = Integer.parseInt((String) tableModel.getValueAt(selectedRow, 0));

        int confirm = JOptionPane.showConfirmDialog(this,
                "Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteUser(id);
            tableModel.removeRow(selectedRow);
            saveDataToTxt();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        }
    }

    private void saveDataToTxt() {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("data/users.txt"));

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(
                        tableModel.getValueAt(i, 0) + "," +
                                tableModel.getValueAt(i, 1) + "," +
                                tableModel.getValueAt(i, 2) + "," +
                                tableModel.getValueAt(i, 3) + "," +
                                "" + "," +
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
