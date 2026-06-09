package views;

import controllers.LamaranController;
import models.Lamaran;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KelolaLamaranView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnProcess, btnAccept, btnReject, btnRefresh;
    private LamaranController lamaranController;

    public KelolaLamaranView(LamaranController lamaranController) {
        this.lamaranController = lamaranController;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnProcess = new JButton("Proses");
        btnAccept = new JButton("Terima");
        btnReject = new JButton("Tolak");
        btnRefresh = new JButton("Refresh");

        Color buttonColor = new Color(41, 128, 185);

        JButton[] buttons = {btnProcess, btnAccept, btnReject, btnRefresh};
        for (JButton btn : buttons) {
            btn.setBackground(buttonColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            topPanel.add(btn);
        }

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Nama Pelamar", "Lowongan", "Status", "Tanggal", "CV"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.getTableHeader().setDefaultRenderer(centerRenderer);

        add(new JScrollPane(table), BorderLayout.CENTER);

        btnProcess.addActionListener(e -> {
            Lamaran lamaran = getSelectedLamaran();
            if (lamaran == null) {
                JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
                return;
            }

            boolean success = lamaranController.processApplication(lamaran);
            JOptionPane.showMessageDialog(this, lamaranController.getMessage());

            if (success) loadData();
        });

        btnAccept.addActionListener(e -> {
            Lamaran lamaran = getSelectedLamaran();
            if (lamaran == null) {
                JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
                return;
            }

            boolean success = lamaranController.acceptApplication(lamaran);
            JOptionPane.showMessageDialog(this, lamaranController.getMessage());

            if (success) loadData();
        });

        btnReject.addActionListener(e -> {
            Lamaran lamaran = getSelectedLamaran();
            if (lamaran == null) {
                JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
                return;
            }

            String reason = JOptionPane.showInputDialog(this, "Alasan penolakan:");
            if (reason == null || reason.trim().isEmpty()) return;

            boolean success = lamaranController.rejectApplication(lamaran, reason);
            JOptionPane.showMessageDialog(this, lamaranController.getMessage());

            if (success) loadData();
        });

        btnRefresh.addActionListener(e -> loadData());

        loadData();
    }

    private Lamaran getSelectedLamaran() {
        int row = table.getSelectedRow();
        if (row == -1) return null;

        int id = (int) tableModel.getValueAt(row, 0);
        return lamaranController.getApplicationById(id);
    }

    private void loadData() {
        tableModel.setRowCount(0);

        List<Lamaran> lamaranList = lamaranController.getAllApplications();

        for (Lamaran lamaran : lamaranList) {
            tableModel.addRow(new Object[]{
                    lamaran.getId(),
                    lamaran.getPekerja().getNama(),
                    lamaran.getLowongan().getJudul(),
                    lamaran.getStatus(),
                    lamaran.getTanggalLamaran(),
                    lamaran.getCvPath()
            });
        }
    }
}