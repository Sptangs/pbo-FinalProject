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

//B-Untuk testing fitur PekerjaVIP.
    public static void main(String[] args) {
        System.out.println("=== UJI COBA FITUR PEKERJA VIP (VERSI 2.0) ===");

        try {
//Buat menambahkan data.
            PekerjaVIP pekerjaTest = new PekerjaVIP(
                    999,                     
                    "Budi Santoso",             
                    25,                        
                    "budi.premium@email.com",   
                    "081234567890",             
                    "Jl. Pahlawan No. 10",      
                    "Full-Stack Developer",     
                    "Platinum",                
                    1500000.0                  
            );

            System.out.println("Data Berhasil Dibuat!");
            System.out.println(pekerjaTest.getInfo());
            System.out.println("--------------------------------------------------");

            System.out.println("Menguji Exception (Bonus Layanan Minus):");
            pekerjaTest.setBonusLayanan(-50000); 

        } catch (IllegalArgumentException e) {
            System.out.println("Penanganan Error Berhasil Ditangkap: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error Umum: " + e.getMessage());
        }
    }