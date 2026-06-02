package views;

import controllers.LamaranController;
import controllers.LowonganPekerjaanController;
// import controllers.PekerjaController;
import models.Pekerja;
import models.LowonganPekerjaan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FormLamaranView extends JFrame {
    private LowonganPekerjaanController lowonganController;
    private LamaranController lamaranController;
    // private PekerjaController pekerjaController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnSubmitLamaran;

    public FormLamaranView() {
        lowonganController = new LowonganPekerjaanController();
        lamaranController = new LamaranController(lowonganController);
        // pekerjaController = new PekerjaController();

        setTitle("Cari dan Lamar Lowongan Pekerjaan");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Daftar Lowongan Tersedia");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(Color.WHITE);
        btnSubmitLamaran = new JButton("Submit Lamaran untuk Lowongan Terpilih");
        btnSubmitLamaran.setBackground(new Color(52, 152, 219));
        btnSubmitLamaran.setForeground(Color.WHITE);
        btnSubmitLamaran.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSubmitLamaran.setFocusPainted(false);
        btnSubmitLamaran.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmitLamaran.setPreferredSize(new Dimension(280, 35));
        topPanel.add(btnSubmitLamaran);

        String[] columns = {
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
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(32);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setPreferredSize(new Dimension(0, 38));
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        refreshTable();

        btnSubmitLamaran.addActionListener(e -> submitLamaran());

        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Object[]> dataList = lowonganController.getLowonganDataForTable();
        for (Object[] row : dataList) {
            tableModel.addRow(row);
        }
    }

    private void submitLamaran() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih lowongan terlebih dahulu!");
            return;
        }

        int lowonganId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        LowonganPekerjaan lowongan = lowonganController.getAllLowongan().stream()
                .filter(l -> l.getId() == lowonganId)
                .findFirst()
                .orElse(null);

        if (lowongan == null) {
            JOptionPane.showMessageDialog(this, "Lowongan tidak ditemukan!");
            return;
        }

        showLamaranForm(lowongan);
    }

    private void showLamaranForm(LowonganPekerjaan lowongan) {
        JDialog dialog = new JDialog(this, "Submit Lamaran", true);
        dialog.setSize(700, 600);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        dialog.add(mainPanel, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Lowongan:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField txtLowongan = new JTextField(lowongan.getJudul() + " - " + lowongan.getPerusahaanNama());
        txtLowongan.setEditable(false);
        formPanel.add(txtLowongan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Nama Pekerja:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField txtNamaPekerja = new JTextField();
        formPanel.add(txtNamaPekerja, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(new JLabel("Upload CV:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JPanel cvPanel = new JPanel(new BorderLayout(5, 0));
        cvPanel.setBackground(Color.WHITE);
        JTextField txtCvPath = new JTextField();
        txtCvPath.setEditable(false);
        JButton btnPilihCv = new JButton("Pilih File");
        btnPilihCv.setBackground(new Color(41, 128, 185));
        btnPilihCv.setForeground(Color.WHITE);
        btnPilihCv.setFocusPainted(false);
        cvPanel.add(txtCvPath, BorderLayout.CENTER);
        cvPanel.add(btnPilihCv, BorderLayout.EAST);
        formPanel.add(cvPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        formPanel.add(new JLabel("Surat Lamaran:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        JTextArea txtSuratLamaran = new JTextArea(5, 20);
        txtSuratLamaran.setLineWrap(true);
        txtSuratLamaran.setWrapStyleWord(true);
        JScrollPane scrollSurat = new JScrollPane(txtSuratLamaran);
        scrollSurat.setPreferredSize(new Dimension(300, 100));
        formPanel.add(scrollSurat, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        JButton btnKirim = new JButton("Kirim Lamaran");
        btnKirim.setBackground(new Color(41, 128, 185));
        btnKirim.setForeground(Color.WHITE);
        btnKirim.setFocusPainted(false);
        btnKirim.setPreferredSize(new Dimension(150, 40));

        JButton btnBatal = new JButton("Batal");
        btnBatal.setBackground(new Color(200, 200, 200));
        btnBatal.setForeground(Color.WHITE);
        btnBatal.setFocusPainted(false);
        btnBatal.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(btnKirim);
        buttonPanel.add(btnBatal);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        btnPilihCv.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                txtCvPath.setText(selectedFile.getAbsolutePath());
            }
        });

        btnKirim.addActionListener(e -> {
            String namaPekerja = txtNamaPekerja.getText().trim();
            
            if (namaPekerja.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Nama pekerja tidak boleh kosong!");
                return;
            }

            if (txtCvPath.getText().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Pilih CV terlebih dahulu!");
                return;
            }

            if (txtSuratLamaran.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Surat lamaran tidak boleh kosong!");
                return;
            }

            try {
                Pekerja pekerja = new Pekerja(
                        999,                      
                        namaPekerja,
                        30,                       
                        "pekerja@example.com",    
                        "08123456789",            
                        "Indonesia",              
                        "Umum"                    
                );

                File cvFile = new File(txtCvPath.getText());
                if (!cvFile.exists()) {
                    JOptionPane.showMessageDialog(dialog, "File CV tidak ditemukan!");
                    return;
                }

                File cvDir = new File("src/asset/cv");
                if (!cvDir.exists()) {
                    cvDir.mkdirs();
                }

                String cvFileName = namaPekerja.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis() + ".pdf";
                String cvDestPath = "src/asset/cv/" + cvFileName;
                
                Files.copy(cvFile.toPath(), new File(cvDestPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

                boolean success = lamaranController.addApplication(
                        lowongan,
                        pekerja,
                        cvDestPath,
                        txtSuratLamaran.getText()
                );

                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Lamaran berhasil disubmit untuk:\n" + namaPekerja);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Gagal submit lamaran: " + lamaranController.getMessage());
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Data tidak valid: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        btnBatal.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLamaranView());
    }
}