package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PerusahaanView extends JFrame {
    private JTextField txtId, txtNama, txtAlamat, txtEmail;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;

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
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    //getter controller
    public JTextField getTxtId() { return txtId; }
    public JTextField getTxtNama() { return txtNama; }
    public JTextField getTxtAlamat() { return txtAlamat; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnCLear() { return btnClear; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    
    public static void main(String[] args) {
        new PerusahaanView();
    }
}

