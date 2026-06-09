package views;

import controllers.LowonganPrioritasController;
import models.LowonganPrioritas;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class LowonganPrioritasPanel extends JPanel {

    private JButton btnAdd;
    private JButton btnFilter;
    private JButton btnRefresh;

    private JTable table;
    private DefaultTableModel tableModel;

    private final LowonganPrioritasController controller;

    public LowonganPrioritasPanel() {

        controller = new LowonganPrioritasController();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(236, 240, 241));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Manajemen Lowongan Prioritas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        add(headerPanel, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(Color.WHITE);

        btnAdd = createButton("Tambah");
        btnFilter = createButton("Prioritas Tinggi");
        btnRefresh = createButton("Refresh");

        topPanel.add(btnAdd);
        topPanel.add(btnFilter);
        topPanel.add(btnRefresh);

        // TABLE
        tableModel = new DefaultTableModel(new String[]{
                "ID", "Perusahaan", "Judul", "Level", "Gaji Min", "Gaji Max"
        }, 0);

        table = new JTable(tableModel);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ACTION
        btnAdd.addActionListener(e -> showFormDialog());
        btnFilter.addActionListener(e -> filterPrioritas());
        btnRefresh.addActionListener(e -> refreshTable());

        refreshTable();
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(52, 152, 219));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(160, 35));
        return btn;
    }

    private void styleTable(JTable table) {
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
    }

    // ================= TABLE =================
    private void refreshTable() {

        tableModel.setRowCount(0);

        List<LowonganPrioritas> list = controller.getLowonganPrioritas();

        for (LowonganPrioritas lp : list) {
            tableModel.addRow(new Object[]{
                    lp.getId(),
                    lp.getPerusahaanNama(),
                    lp.getJudul(),
                    lp.getLevelPrioritas(),
                    lp.getGajiMin(),
                    lp.getGajiMax()
            });
        }
    }

    // ================= FILTER =================
    private void filterPrioritas() {

        tableModel.setRowCount(0);

        List<LowonganPrioritas> list = controller.filterPrioritasTinggi();

        for (LowonganPrioritas lp : list) {
            tableModel.addRow(new Object[]{
                    lp.getId(),
                    lp.getPerusahaanNama(),
                    lp.getJudul(),
                    lp.getLevelPrioritas(),
                    lp.getGajiMin(),
                    lp.getGajiMax()
            });
        }
    }

    // ================= ADD FORM =================
    private void showFormDialog() {

        JTextField txtPerusahaan = new JTextField();
        JTextField txtJudul = new JTextField();
        JTextArea txtDeskripsi = new JTextArea(3, 20);

        JTextField txtKualifikasi = new JTextField();
        JTextField txtLokasi = new JTextField();
        JTextField txtJenis = new JTextField();

        JTextField txtGajiMin = new JTextField();
        JTextField txtGajiMax = new JTextField();

        JTextField txtLevel = new JTextField();
        JTextField txtBiaya = new JTextField();

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        panel.add(new JLabel("Perusahaan"));
        panel.add(txtPerusahaan);

        panel.add(new JLabel("Judul"));
        panel.add(txtJudul);

        panel.add(new JLabel("Deskripsi"));
        panel.add(new JScrollPane(txtDeskripsi));

        panel.add(new JLabel("Kualifikasi"));
        panel.add(txtKualifikasi);

        panel.add(new JLabel("Lokasi"));
        panel.add(txtLokasi);

        panel.add(new JLabel("Jenis"));
        panel.add(txtJenis);

        panel.add(new JLabel("Gaji Min"));
        panel.add(txtGajiMin);

        panel.add(new JLabel("Gaji Max"));
        panel.add(txtGajiMax);

        panel.add(new JLabel("Tanggal"));
        panel.add(dateSpinner);

        panel.add(new JLabel("Level Prioritas"));
        panel.add(txtLevel);

        panel.add(new JLabel("Biaya"));
        panel.add(txtBiaya);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Tambah Lowongan Prioritas",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {

                Date date = (Date) dateSpinner.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                controller.tambahLowonganPrioritas(
                        txtPerusahaan.getText(),
                        txtJudul.getText(),
                        txtDeskripsi.getText(),
                        txtKualifikasi.getText(),
                        txtLokasi.getText(),
                        txtJenis.getText(),
                        Integer.parseInt(txtGajiMin.getText()),
                        Integer.parseInt(txtGajiMax.getText()),
                        LocalDate.parse(sdf.format(date)),
                        txtLevel.getText(),
                        Integer.parseInt(txtBiaya.getText()),
                        true
                );

                JOptionPane.showMessageDialog(this, "Berhasil ditambahkan!");
                refreshTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}