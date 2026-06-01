package views;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton btnUser;
    private JButton btnPekerja;
    private JButton btnPerusahaan;
    private JButton btnLowongan;
    private JButton btnLamaran;
    private CardLayout cardLayout;

    public AdminDashboard() {
        setTitle("Admin Dashboard - CariKerja");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(52, 73, 94));
        sidebarPanel.setPreferredSize(new Dimension(200, 0));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel titleLabel = new JLabel("ADMIN PANEL");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebarPanel.add(titleLabel);
        sidebarPanel.add(Box.createVerticalStrut(30));

        btnUser = createSidebarButton("👤 User");
        btnUser.addActionListener(e -> showUserPanel());
        sidebarPanel.add(btnUser);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnPekerja = createSidebarButton("👨‍💼 Pekerja");
        btnPekerja.addActionListener(e -> showPekerjaPanel());
        sidebarPanel.add(btnPekerja);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnPerusahaan = createSidebarButton("🏢 Perusahaan");
        btnPerusahaan.addActionListener(e -> showPerusahaanPanel());
        sidebarPanel.add(btnPerusahaan);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnLowongan = createSidebarButton("📋 Lowongan");
        btnLowongan.addActionListener(e -> showLowonganPanel());
        sidebarPanel.add(btnLowongan);
        sidebarPanel.add(Box.createVerticalStrut(10));

        btnLamaran = createSidebarButton("📄 Lamaran");
        btnLamaran.addActionListener(e -> showLamaranPanel());
        sidebarPanel.add(btnLamaran);
        sidebarPanel.add(Box.createVerticalStrut(10));


        JButton btnLogout = createSidebarButton("🚪 Logout");
        btnLogout.addActionListener(e -> logout());
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(btnLogout);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(236, 240, 241));

        contentPanel.add(new UserManagementPanel(), "user");

        contentPanel.add(new PekerjaManagementPanel(), "pekerja");

        contentPanel.add(new PerusahaanManagementPanel(), "perusahaan");

        contentPanel.add(new LowonganManagementPanel(), "lowongan");

        contentPanel.add(new KelolaLamaranView(), "lamaran");

        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        cardLayout.show(contentPanel, "user");
        btnUser.setBackground(new Color(41, 128, 185));

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(180, 50));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(new Color(44, 62, 80));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(52, 73, 94));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!btn.getBackground().equals(new Color(41, 128, 185))) {
                    btn.setBackground(new Color(44, 62, 80));
                }
            }
        });

        return btn;
    }

    private void showUserPanel() {
        cardLayout.show(contentPanel, "user");
        btnUser.setBackground(new Color(41, 128, 185));
        btnPekerja.setBackground(new Color(44, 62, 80));
        btnPerusahaan.setBackground(new Color(44, 62, 80));
        btnLowongan.setBackground(new Color(44, 62, 80));
        btnLamaran.setBackground(new Color(44, 62, 80));
    }

    private void showPekerjaPanel() {
        cardLayout.show(contentPanel, "pekerja");
        btnPekerja.setBackground(new Color(41, 128, 185));
        btnUser.setBackground(new Color(44, 62, 80));
        btnPerusahaan.setBackground(new Color(44, 62, 80));
        btnLowongan.setBackground(new Color(44, 62, 80));
        btnLamaran.setBackground(new Color(44, 62, 80));
    }

    private void showPerusahaanPanel() {
        cardLayout.show(contentPanel, "perusahaan");
        btnPerusahaan.setBackground(new Color(41, 128, 185));
        btnUser.setBackground(new Color(44, 62, 80));
        btnPekerja.setBackground(new Color(44, 62, 80));
        btnLowongan.setBackground(new Color(44, 62, 80));
        btnLamaran.setBackground(new Color(44, 62, 80));
    }

    private void showLowonganPanel() {
        cardLayout.show(contentPanel, "lowongan");
        btnLowongan.setBackground(new Color(41, 128, 185));
        btnUser.setBackground(new Color(44, 62, 80));
        btnPekerja.setBackground(new Color(44, 62, 80));
        btnPerusahaan.setBackground(new Color(44, 62, 80));
        btnLamaran.setBackground(new Color(44, 62, 80));
    }

    private void showLamaranPanel() {
        cardLayout.show(contentPanel, "lamaran");
        btnLamaran.setBackground(new Color(41, 128, 185));
        btnUser.setBackground(new Color(44, 62, 80));
        btnPekerja.setBackground(new Color(44, 62, 80));
        btnPerusahaan.setBackground(new Color(44, 62, 80));
        btnLowongan.setBackground(new Color(44, 62, 80));
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
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}
