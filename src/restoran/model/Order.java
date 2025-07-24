package restoran.model;

import java.util.Date;

public class Order {
    private int id;
    private Date tanggalOrder;
    private String status;
    private double totalHarga;

    // Constructor kosong
    public Order() {}

    // Constructor lengkap
    public Order(int id, Date tanggalOrder, String status, double totalHarga) {
        this.id = id;
        this.tanggalOrder = tanggalOrder;
        this.status = status;
        this.totalHarga = totalHarga;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTanggalOrder() {
        return tanggalOrder;
    }

    public void setTanggalOrder(Date tanggalOrder) {
        this.tanggalOrder = tanggalOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }
}
