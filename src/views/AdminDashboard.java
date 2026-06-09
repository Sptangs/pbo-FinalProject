package views;

import controllers.LamaranController;
import controllers.LowonganPekerjaanController;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton btnUser;
    private JButton btnPekerja;
    private JButton btnPerusahaan;
    private JButton btnLowongan;
    private JButton btnLowonganPrioritas;
    private JButton btnLamaran;

    private CardLayout cardLayout;

    private LamaranController lamaranController;

    public AdminDashboard() {

        setTitle("Admin Dashboard - CariKerja");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        LowonganPekerjaanController lowonganController = new LowonganPekerjaanController();

        lamaranController = new LamaranController(lowonganController);

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(52, 73, 94));
        sidebarPanel.setPreferredSize(new Dimension(220, 0));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("ADMIN PANEL");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebarPanel.add(titleLabel);
        sidebarPanel.add(Box.createVerticalStrut(30));

        btnUser = createSidebarButton("👤 User");
        btnUser.addActionListener(e -> showPanel("user"));
        sidebarPanel.add(btnUser);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnPekerja = createSidebarButton("👨‍💼 Pekerja");
        btnPekerja.addActionListener(e -> showPanel("pekerja"));
        sidebarPanel.add(btnPekerja);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnPerusahaan = createSidebarButton("🏢 Perusahaan");
        btnPerusahaan.addActionListener(e -> showPanel("perusahaan"));
        sidebarPanel.add(btnPerusahaan);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnLowongan = createSidebarButton("📋 Lowongan");
        btnLowongan.addActionListener(e -> showPanel("lowongan"));
        sidebarPanel.add(btnLowongan);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnLowonganPrioritas = createSidebarButton("⭐ Lowongan Prioritas");
        btnLowonganPrioritas.addActionListener(e -> showPanel("lowongan_prioritas"));
        sidebarPanel.add(btnLowonganPrioritas);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnLamaran = createSidebarButton("📄 Lamaran");
        btnLamaran.addActionListener(e -> showPanel("lamaran"));
        sidebarPanel.add(btnLamaran);

        sidebarPanel.add(Box.createVerticalGlue());

        JButton btnLogout = createSidebarButton("🚪 Logout");
        btnLogout.addActionListener(e -> logout());
        sidebarPanel.add(btnLogout);

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(236, 240, 241));

        contentPanel.add(new UserManagementPanel(), "user");
        contentPanel.add(new PekerjaManagementPanel(), "pekerja");
        contentPanel.add(new PerusahaanManagementPanel(), "perusahaan");

        contentPanel.add(new LowonganManagementPanel(), "lowongan");

        contentPanel.add(new LowonganPrioritasPanel(), "lowongan_prioritas");

        contentPanel.add(new KelolaLamaranView(lamaranController), "lamaran");

        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        showPanel("user");

        setVisible(true);
    }

    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);

        resetButtonColor();

        switch (name) {
            case "user":
                btnUser.setBackground(new Color(41, 128, 185));
                break;

            case "pekerja":
                btnPekerja.setBackground(new Color(41, 128, 185));
                break;

            case "perusahaan":
                btnPerusahaan.setBackground(new Color(41, 128, 185));
                break;

            case "lowongan":
                btnLowongan.setBackground(new Color(41, 128, 185));
                break;

            case "lowongan_prioritas":
                btnLowonganPrioritas.setBackground(new Color(41, 128, 185));
                break;

            case "lamaran":
                btnLamaran.setBackground(new Color(41, 128, 185));
                break;
        }
    }

    private void resetButtonColor() {

        Color normal = new Color(44, 62, 80);

        btnUser.setBackground(normal);
        btnPekerja.setBackground(normal);
        btnPerusahaan.setBackground(normal);
        btnLowongan.setBackground(normal);
        btnLowonganPrioritas.setBackground(normal);
        btnLamaran.setBackground(normal);
    }

    private JButton createSidebarButton(String text) {

        JButton btn = new JButton(text);

        btn.setMaximumSize(new Dimension(200, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(new Color(44, 62, 80));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(52, 73, 94));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (btn.getBackground().getRGB() != new Color(41, 128, 185).getRGB()) {
                    btn.setBackground(new Color(44, 62, 80));
                }
            }
        });

        return btn;
    }

    private void logout() {

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Anda yakin ingin logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}