package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserManagementView extends JFrame {
    private JTextField txtName, txtEmail, txtAlamat;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;

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

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Email", "Alamat"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    //getter controller
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtAlamat() { return txtAlamat; }
    public JButton geBtnAdd() { return btnAdd; }
    public JButton geBtnUpdate() { return btnUpdate; }
    public JButton geBtnDelete() { return btnDelete; }
    public JButton geBtnCLear() { return btnClear; }
    public JTable getTable() { return table; }
    public DefaultTableModel geTableModel() { return tableModel; }

    public static void main(String[] args) {
        new UserManagementView();
    }
}