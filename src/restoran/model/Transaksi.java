package restoran.model;

import java.util.Date;

public class Transaksi {
    private int id;
    private int idOrder;
    private Date tanggalTransaksi;
    private double totalPembayaran;
    private String metodePembayaran;

    // Constructor kosong (untuk fleksibilitas)
    public Transaksi() {}

    // Constructor lengkap
    public Transaksi(int id, int idOrder, double totalPembayaran, Date tanggalTransaksi, String metodePembayaran) {
        this.id = id;
        this.idOrder = idOrder;
        this.totalPembayaran = totalPembayaran;
        this.tanggalTransaksi = tanggalTransaksi;
        this.metodePembayaran = metodePembayaran;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }
}
