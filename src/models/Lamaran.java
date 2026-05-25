package models;

import java.util.Date;
import java.util.Objects;

public class Lamaran extends Entitas {

    private LowonganPekerjaan lowongan;

    private Pekerja pekerja;

    private String cvPath;

    private String suratLamaran;

    private Date tanggalLamaran;

    private StatusLamaran status;

    private String catatanHR;

    private Date tanggalDiproses;

    public Lamaran(
            LowonganPekerjaan lowongan,
            Pekerja pekerja,
            String cvPath,
            String suratLamaran
    ) {

        super();

        if (lowongan == null) {
            throw new IllegalArgumentException(
                    "Lowongan tidak boleh null");
        }

        if (pekerja == null) {
            throw new IllegalArgumentException(
                    "Pekerja tidak boleh null");
        }

        this.lowongan = lowongan;

        this.pekerja = pekerja;

        this.cvPath = cvPath;

        this.suratLamaran = suratLamaran;

        this.tanggalLamaran = new Date();

        this.status = StatusLamaran.DIAJUKAN;
    }


    public LowonganPekerjaan getLowongan() {
        return lowongan;
    }

    public Pekerja getPekerja() {
        return pekerja;
    }

    public String getCvPath() {
        return cvPath;
    }

    public String getSuratLamaran() {
        return suratLamaran;
    }

    public Date getTanggalLamaran() {
        return tanggalLamaran;
    }

    public StatusLamaran getStatus() {
        return status;
    }


    public void prosesLamaran() {
        status = StatusLamaran.DIREVIEW;
    }

    public void terimaLamaran() {
        status = StatusLamaran.DITERIMA;
    }

    public void tolakLamaran(String alasan) {

        this.catatanHR = alasan;

        status = StatusLamaran.DITOLAK;
    }

    @Override
    public String toString() {

        return pekerja.getNama()
                + " melamar "
                + lowongan.getJudul();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Lamaran)) return false;

        Lamaran lamaran = (Lamaran) o;

        return getId() == lamaran.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}