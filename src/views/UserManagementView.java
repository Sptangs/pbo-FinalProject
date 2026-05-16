package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class UserManagementView extends JFrame {
    private JTextField txtName, txtEmail, txtAlamat;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public UserManagementView() {
        setTitle("Manajemen User - CariKerja");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //Panel atas
        JPanel formPanel = new JPanel(new GridLayout(3,2,10,10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Data User"));
        formPanel.add(new JLabel("Nama: "));
        txtName = new JTextField();
        formPanel.add(txtName);
        formPanel.add(new JLabel("Email: "));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("ALamat: "));
        txtAlamat = new JTextField();
        formPanel.add(txtAlamat);


        //panel tengah
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Bersihkan");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserManagementView();
    }
}