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
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
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
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        loadDataFromTxt();

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

                if (data.length >= 8) {

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
            }

            reader.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void showFormDialog() {

        JTextField txtNama = new JTextField();
        JTextField txtAge = new JTextField();
        JTextField txtEmail = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JTextField txtAlamat = new JTextField();
        JTextField txtNoHp = new JTextField();

        JComboBox<Role> comboRole = new JComboBox<>(Role.values());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Dimension fieldSize = new Dimension(250, 32);

        txtNama.setPreferredSize(fieldSize);
        txtAge.setPreferredSize(fieldSize);
        txtEmail.setPreferredSize(fieldSize);
        txtPassword.setPreferredSize(fieldSize);
        txtAlamat.setPreferredSize(fieldSize);
        txtNoHp.setPreferredSize(fieldSize);
        comboRole.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel lblNama = new JLabel("Nama");
        lblNama.setFont(labelFont);
        panel.add(lblNama, gbc);

        gbc.gridx = 1;
        panel.add(txtNama, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(labelFont);
        panel.add(lblAge, gbc);

        gbc.gridx = 1;
        panel.add(txtAge, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(labelFont);
        panel.add(lblEmail, gbc);

        gbc.gridx = 1;
        panel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(labelFont);
        panel.add(lblPassword, gbc);

        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setFont(labelFont);
        panel.add(lblAlamat, gbc);

        gbc.gridx = 1;
        panel.add(txtAlamat, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setFont(labelFont);
        panel.add(lblNoHp, gbc);

        gbc.gridx = 1;
        panel.add(txtNoHp, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel lblRole = new JLabel("Role");
        lblRole.setFont(labelFont);
        panel.add(lblRole, gbc);

        gbc.gridx = 1;
        panel.add(comboRole, gbc);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah User",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {

                String nama = txtNama.getText();
                int age = Integer.parseInt(txtAge.getText());
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());
                String alamat = txtAlamat.getText();
                String noHp = txtNoHp.getText();

                Role role = (Role) comboRole.getSelectedItem();

                boolean success = controller.addUser(
                        nama,
                        age,
                        email,
                        password,
                        alamat,
                        noHp,
                        role);

                if (success) {

                    int lastId = tableModel.getRowCount() + 1;

                    tableModel.addRow(new Object[] {
                            lastId,
                            nama,
                            age,
                            email,
                            alamat,
                            noHp,
                            role
                    });

                    saveDataToTxt();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data berhasil ditambahkan!");

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Data gagal ditambahkan!\nCek email atau nomor HP.");
                }

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age harus berupa angka!");
            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error : " + e.getMessage());
            }
        }
    }

    private void editData() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!");

            return;
        }

        JTextField txtNama = new JTextField(
                tableModel.getValueAt(selectedRow, 1).toString());

        JTextField txtAge = new JTextField(
                tableModel.getValueAt(selectedRow, 2).toString());

        JTextField txtEmail = new JTextField(
                tableModel.getValueAt(selectedRow, 3).toString());

        JTextField txtAlamat = new JTextField(
                tableModel.getValueAt(selectedRow, 4).toString());

        JTextField txtNoHp = new JTextField(
                tableModel.getValueAt(selectedRow, 5).toString());

        JComboBox<Role> comboRole = new JComboBox<>(Role.values());

        comboRole.setSelectedItem(
                tableModel.getValueAt(selectedRow, 6));

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

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
        panel.add(comboRole);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit User",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            try {

                int id = Integer.parseInt(
                        tableModel.getValueAt(selectedRow, 0).toString());

                controller.updateUser(
                        id,
                        txtNama.getText(),
                        Integer.parseInt(txtAge.getText()),
                        txtEmail.getText(),
                        "123456",
                        txtAlamat.getText(),
                        txtNoHp.getText(),
                        (Role) comboRole.getSelectedItem());

                tableModel.setValueAt(txtNama.getText(), selectedRow, 1);
                tableModel.setValueAt(txtAge.getText(), selectedRow, 2);
                tableModel.setValueAt(txtEmail.getText(), selectedRow, 3);
                tableModel.setValueAt(txtAlamat.getText(), selectedRow, 4);
                tableModel.setValueAt(txtNoHp.getText(), selectedRow, 5);
                tableModel.setValueAt(comboRole.getSelectedItem(), selectedRow, 6);

                saveDataToTxt();

                JOptionPane.showMessageDialog(
                        this,
                        "Data berhasil diupdate!");
//
            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error : " + e.getMessage());
            }
        }
    }

    private void deleteData() {

        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {

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
                    tableModel.getValueAt(selectedRow, 0).toString());

            controller.deleteUser(id);

            tableModel.removeRow(selectedRow);

            saveDataToTxt();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus!");
        }
    }

    private void saveDataToTxt() {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("data/users.txt"));

            for (int i = 0; i < tableModel.getRowCount(); i++) {

                writer.write(
                        tableModel.getValueAt(i, 0) + "," +
                                tableModel.getValueAt(i, 1) + "," +
                                tableModel.getValueAt(i, 2) + "," +
                                tableModel.getValueAt(i, 3) + "," +
                                "password," +
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