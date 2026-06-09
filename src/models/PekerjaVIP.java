package models;

public class PekerjaVIP extends Pekerja {
    
    private String tingkatVIP;
    private double bonusLayanan;

    public PekerjaVIP(int idPekerja, 
                      String nama, 
                      int umur, 
                      String email, 
                      String noTelepon, 
                      String alamat, 
                      String keahlian, 
                      String tingkatVIP, 
                      double bonusLayanan) {
        
        super(idPekerja, nama, umur, email, noTelepon, alamat, keahlian);
        
        setTingkatVIP(tingkatVIP);
        setBonusLayanan(bonusLayanan);
    }

    public String getTingkatVIP() {
        return tingkatVIP;
    }

    public void setTingkatVIP(String tingkatVIP) {
        if (tingkatVIP == null || tingkatVIP.trim().isEmpty()) {
            throw new IllegalArgumentException("[DEBUG] Tingkat VIP kosong");
        }
        this.tingkatVIP = tingkatVIP.trim();
    }

    public double getBonusLayanan() {
        return bonusLayanan;
    }

    public void setBonusLayanan(double bonusLayanan) {
        if (bonusLayanan < 0) {
            throw new IllegalArgumentException("[DEBUG] Bonus layanan tidak boleh minus");
        }
        this.bonusLayanan = bonusLayanan;
    }

    @Override
    public String getInfo() {

        return super.getInfo() + 
               " | Status: VIP (" + tingkatVIP + ")" + 
               " | Bonus Layanan: Rp" + bonusLayanan;
    }
}