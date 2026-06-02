package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import controllers.LowonganPekerjaanController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.SpinnerDateModel;

public class LowonganManagementPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;

    private JTable table;
    private DefaultTableModel tableModel;

    private LowonganPekerjaanController controller;

    public LowonganManagementPanel() {

        controller = new LowonganPekerjaanController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.setBackground(Color.WHITE);

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Lowongan Pekerjaan");

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 10));

        topPanel.setBackground(Color.WHITE);

        btnAdd = createButton("Tambah");
        btnEdit = createButton("Edit");
        btnDelete = createButton("Hapus");

        topPanel.add(btnAdd);
        topPanel.add(btnEdit);
        topPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Perusahaan",
                        "Judul",
                        "Deskripsi",
                        "Kualifikasi",
                        "Lokasi",
                        "Jenis",
                        "Gaji Min",
                        "Gaji Max",
                        "Tanggal Tutup"
                },
                0);

        table = new JTable(tableModel);

        table.setRowHeight(32);

        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13));

        table.getTableHeader().setPreferredSize(
                new Dimension(0, 38));

        table.setSelectionBackground(
                new Color(52, 152, 219));

        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();

        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.setBackground(Color.WHITE);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshTable();

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

    private void refreshTable() {

        tableModel.setRowCount(0);

        List<Object[]> dataList = controller.getLowonganDataForTable();

        for (Object[] row : dataList) {
            tableModel.addRow(row);
        }
    }

    private class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            
            if (string.matches("\\d+")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                           AttributeSet attrs) throws BadLocationException {
            if (text == null) return;
            
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    private void showFormDialog() {

        JTextField txtPerusahaanNama = new JTextField();
        JTextField txtJudul = new JTextField();
        JTextArea txtDeskripsi = new JTextArea(4, 20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setWrapStyleWord(true);
        JScrollPane scrollDeskripsi = new JScrollPane(txtDeskripsi);
        
        JTextField txtKualifikasi = new JTextField();
        JTextField txtLokasi = new JTextField();
        JTextField txtJenis = new JTextField();
        
        JTextField txtGajiMin = new JTextField();
        JTextField txtGajiMax = new JTextField();
        
        ((AbstractDocument) txtGajiMin.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((AbstractDocument) txtGajiMax.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);
        dateSpinner.setPreferredSize(new Dimension(200, 25));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Perusahaan"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtPerusahaanNama, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Judul"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtJudul, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(new JLabel("Deskripsi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(scrollDeskripsi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Kualifikasi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtKualifikasi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Lokasi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtLokasi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Jenis"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtJenis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Gaji Min (angka saja)"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtGajiMin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Gaji Max (angka saja)"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtGajiMax, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Tanggal Tutup"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(dateSpinner, gbc);

        panel.setPreferredSize(new Dimension(600, 450));

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Lowongan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                String perusahaanNama = txtPerusahaanNama.getText();
                
                String tanggalTutup = "";
                Date selectedDate = (Date) dateSpinner.getValue();
                if (selectedDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    tanggalTutup = sdf.format(selectedDate);
                }

                boolean success = controller.addLowongan(
                        perusahaanNama,
                        txtJudul.getText(),
                        txtDeskripsi.getText(),
                        txtKualifikasi.getText(),
                        txtLokasi.getText(),
                        txtJenis.getText(),
                        txtGajiMin.getText(),
                        txtGajiMax.getText(),
                        tanggalTutup);

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data lowongan berhasil ditambahkan!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage());
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

        int id = Integer.parseInt(
                tableModel.getValueAt(row, 0).toString());

        JTextField txtJudul = new JTextField(
                tableModel.getValueAt(row, 2).toString());

        JTextArea txtDeskripsi = new JTextArea(
                tableModel.getValueAt(row, 3).toString(), 4, 20);
        txtDeskripsi.setLineWrap(true);
        txtDeskripsi.setWrapStyleWord(true);
        JScrollPane scrollDeskripsi = new JScrollPane(txtDeskripsi);

        JTextField txtKualifikasi = new JTextField(
                tableModel.getValueAt(row, 4).toString());

        JTextField txtLokasi = new JTextField(
                tableModel.getValueAt(row, 5).toString());

        JTextField txtJenis = new JTextField(
                tableModel.getValueAt(row, 6).toString());

        JTextField txtGajiMin = new JTextField(
                tableModel.getValueAt(row, 7).toString());

        JTextField txtGajiMax = new JTextField(
                tableModel.getValueAt(row, 8).toString());

        ((AbstractDocument) txtGajiMin.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((AbstractDocument) txtGajiMax.getDocument()).setDocumentFilter(new NumberOnlyFilter());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Judul"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtJudul, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(new JLabel("Deskripsi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(scrollDeskripsi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Kualifikasi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtKualifikasi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Lokasi"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtLokasi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Jenis"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtJenis, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Gaji Min (angka saja)"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtGajiMin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.3;
        panel.add(new JLabel("Gaji Max (angka saja)"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(txtGajiMax, gbc);

        panel.setPreferredSize(new Dimension(600, 400));

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Lowongan",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            try {
                boolean success = controller.editLowongan(
                        id,
                        txtJudul.getText(),
                        txtDeskripsi.getText(),
                        txtKualifikasi.getText(),
                        txtLokasi.getText(),
                        txtJenis.getText(),
                        txtGajiMin.getText(),
                        txtGajiMax.getText());

                if (success) {

                    refreshTable();

                    JOptionPane.showMessageDialog(
                            this,
                            "Data lowongan berhasil diupdate!");
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            controller.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gaji harus berupa angka!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage());
            }
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

            int id = Integer.parseInt(
                    tableModel.getValueAt(row, 0).toString());

            boolean success = controller.deleteLowongan(id);

            if (success) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Data lowongan berhasil dihapus!");
            } else {

                JOptionPane.showMessageDialog(
                        this,
                        controller.getMessage());
            }
        }
    }
}