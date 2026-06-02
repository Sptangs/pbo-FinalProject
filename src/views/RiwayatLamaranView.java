package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RiwayatLamaranView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRefresh;

    public RiwayatLamaranView() {
        setTitle("Riwayat Lamaran Saya");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(new Color(41, 128, 185));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFocusPainted(false);
        topPanel.add(btnRefresh);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Nama Lowongan", "Perusahaan", "Tanggal Lamaran", "Status", "CV"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.getTableHeader().setDefaultRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        btnRefresh.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Data akan dimuat dari database (simulasi)");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RiwayatLamaranView());
    }
}