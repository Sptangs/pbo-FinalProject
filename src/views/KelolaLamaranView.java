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
        btnProcess.setBackground(buttonColor);
        btnAccept.setBackground(buttonColor);
        btnReject.setBackground(buttonColor);
        btnRefresh.setBackground(buttonColor);

        btnProcess.setForeground(Color.WHITE);
        btnAccept.setForeground(Color.WHITE);
        btnReject.setForeground(Color.WHITE);
        btnRefresh.setForeground(Color.WHITE);

        btnProcess.setFocusPainted(false);
        btnAccept.setFocusPainted(false);
        btnReject.setFocusPainted(false);
        btnRefresh.setFocusPainted(false);

        topPanel.add(btnProcess);
        topPanel.add(btnAccept);
        topPanel.add(btnReject);
        topPanel.add(btnRefresh);
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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        btnProcess.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
            else JOptionPane.showMessageDialog(this, "Proses lamaran (simulasi)");
        });
        btnAccept.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
            else JOptionPane.showMessageDialog(this, "Lamaran diterima (simulasi)");
        });
        btnReject.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) JOptionPane.showMessageDialog(this, "Pilih lamaran terlebih dahulu!");
            else JOptionPane.showMessageDialog(this, "Lamaran ditolak (simulasi)");
        });
        btnRefresh.addActionListener(e -> {
            loadData();
        });
        
        // Load data awal
        loadData();
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