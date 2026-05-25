// ===============================
// FILE: models/StatusLamaran.java
// ===============================

package models;

public enum StatusLamaran {

    DIAJUKAN("Diajukan"),
    DIREVIEW("Direview"),
    DITERIMA("Diterima"),
    DITOLAK("Ditolak"),
    DIBATALKAN("Dibatalkan");

    private final String label;

    StatusLamaran(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean bisaDiubah() {
        return this == DIAJUKAN;
    }

    public boolean bisaDibatalkan() {
        return this == DIAJUKAN
                || this == DIREVIEW;
    }

    public boolean isFinalState() {
        return this == DITERIMA
                || this == DITOLAK
                || this == DIBATALKAN;
    }
}