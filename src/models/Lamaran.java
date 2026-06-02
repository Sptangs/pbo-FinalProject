package models;

import java.util.Date;
import java.util.Objects;

public class Lamaran extends Entitas {

    private LowonganPekerjaan jobPosting;

    private Pekerja worker;

    private String cvPath;

    private String coverLetter;

    private Date applicationDate;

    private StatusLamaran status;

    private String hrNote;

    private Date processedDate;

    public Lamaran(
            LowonganPekerjaan jobPosting,
            Pekerja worker,
            String cvPath,
            String coverLetter
    ) {

        super();

        if (jobPosting == null) {
            throw new IllegalArgumentException(
                    "Job posting cannot be null");
        }

        if (worker == null) {
            throw new IllegalArgumentException(
                    "Worker cannot be null");
        }

        this.jobPosting = jobPosting;

        this.worker = worker;

        this.cvPath = cvPath;

        this.coverLetter = coverLetter;

        this.applicationDate = new Date();

        this.status = StatusLamaran.DIAJUKAN;
    }


    public LowonganPekerjaan getJobPosting() {
        return jobPosting;
    }

    public LowonganPekerjaan getLowongan() {
        return jobPosting;
    }

    public Pekerja getWorker() {
        return worker;
    }

    public Pekerja getPekerja() {
        return worker;
    }

    public String getCvPath() {
        return cvPath;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public String getSuratLamaran() {
        return coverLetter;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public Date getTanggalLamaran() {
        return applicationDate;
    }

    public StatusLamaran getStatus() {
        return status;
    }

    public String getHrNote() {
        return hrNote;
    }

    public String getCatatanHR() {
        return hrNote;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public Date getTanggalDiproses() {
        return processedDate;
    }

    public void processApplication() {
        status = StatusLamaran.DIREVIEW;
        this.processedDate = new Date();
    }

    public void prosesLamaran() {
        processApplication();
    }

    public void acceptApplication() {
        status = StatusLamaran.DITERIMA;
        this.processedDate = new Date();
    }

    public void terimaLamaran() {
        acceptApplication();
    }

    public void rejectApplication(String reason) {
        this.hrNote = reason;
        status = StatusLamaran.DITOLAK;
        this.processedDate = new Date();
    }

    public void tolakLamaran(String alasan) {
        rejectApplication(alasan);
    }

    public boolean canBeEdited() {
        return status == StatusLamaran.DIAJUKAN 
            || status == StatusLamaran.DIREVIEW;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public void setSuratLamaran(String suratLamaran) {
        this.coverLetter = suratLamaran;
    }

    @Override
    public String toString() {

        return worker.getNama()
                + " melamar "
                + jobPosting.getJudul();
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