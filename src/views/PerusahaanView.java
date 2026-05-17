package views;

import javax.swing.*;
import java.awt.*;

public class PerusahaanView extends JFrame {
    private JTextField txtId, txtNama, txtAlamat, txtEmail;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;

    public PerusahaanView() {
        setTitle("Manajemen Perusahaan - CariKerja");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //input
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Data Perusahaan"));

        //id
        formPanel.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false); //gak bisa diedit manual
        formPanel.add(txtId);
        //nama
        formPanel.add(new JLabel("Nama Perusahaan:"));
        txtNama = new JTextField();
        formPanel.add(txtNama);
        //alamat
        formPanel.add(new JLabel("Alamat:"));
        txtAlamat = new JTextField();
        formPanel.add(txtAlamat);
        //email
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        //tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Bersihkan");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        //tambah panel ke frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PerusahaanView();
    }
}

