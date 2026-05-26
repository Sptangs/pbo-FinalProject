package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class FormLamaranView extends JFrame {
    private JComboBox<String> cbLowongan;
    private JTextField txtNamaPekerja;
    private JTextField txtCvPath;
    private JTextArea txtSuratLamaran;
    private JButton btnPilihCv;
    private JButton btnKirim;

    public FormLamaranView() {
        setTitle("Form Lamaran Pekerjaan");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel utama putih
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        // Form panel (GridBagLayout)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Lowongan
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Pilih Lowongan:"), gbc);
        cbLowongan = new JComboBox<>();
        cbLowongan.setPreferredSize(new Dimension(300, 30));
        // Data contoh, nanti bisa diisi dari database
        cbLowongan.addItem("Backend Developer");
        cbLowongan.addItem("Frontend Developer");
        cbLowongan.addItem("UI/UX Designer");
        gbc.gridx = 1;
        formPanel.add(cbLowongan, gbc);

        // Nama Pekerja
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Nama Pekerja:"), gbc);
        txtNamaPekerja = new JTextField();
        txtNamaPekerja.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        formPanel.add(txtNamaPekerja, gbc);

        // Upload CV
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Upload CV:"), gbc);
        JPanel cvPanel = new JPanel(new BorderLayout(5, 0));
        cvPanel.setBackground(Color.WHITE);
        txtCvPath = new JTextField();
        txtCvPath.setEditable(false);
        btnPilihCv = new JButton("Pilih File");
        btnPilihCv.setBackground(new Color(41, 128, 185));
        btnPilihCv.setForeground(Color.WHITE);
        btnPilihCv.setFocusPainted(false);
        cvPanel.add(txtCvPath, BorderLayout.CENTER);
        cvPanel.add(btnPilihCv, BorderLayout.EAST);
        gbc.gridx = 1;
        formPanel.add(cvPanel, gbc);

        // Surat Lamaran
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Surat Lamaran:"), gbc);
        txtSuratLamaran = new JTextArea(5, 20);
        txtSuratLamaran.setLineWrap(true);
        JScrollPane scrollSurat = new JScrollPane(txtSuratLamaran);
        scrollSurat.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 1;
        formPanel.add(scrollSurat, gbc);

        // Tombol Kirim
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnKirim = new JButton("Kirim Lamaran");
        btnKirim.setBackground(new Color(41, 128, 185));
        btnKirim.setForeground(Color.WHITE);
        btnKirim.setFocusPainted(false);
        btnKirim.setPreferredSize(new Dimension(150, 40));
        buttonPanel.add(btnKirim);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Event tombol pilih file
        btnPilihCv.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                txtCvPath.setText(selectedFile.getAbsolutePath());
            }
        });

        // Event tombol kirim (sementara simulasi)
        btnKirim.addActionListener(e -> {
            if (txtNamaPekerja.getText().trim().isEmpty() || txtCvPath.getText().isEmpty() || txtSuratLamaran.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Nanti diisi pemanggilan controller
            JOptionPane.showMessageDialog(this, "Lamaran terkirim! (simulasi)");
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormLamaranView());
    }
}