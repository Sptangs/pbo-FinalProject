package views;

import javax.swing.*;

public class PerusahaanView extends JFrame {
    public PerusahaanView() {
        setTitle("Manajemen Perusahaan - CariKerja");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PerusahaanView();
    }
}

