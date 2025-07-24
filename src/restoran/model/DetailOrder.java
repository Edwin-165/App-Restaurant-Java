package restoran.model;

public class DetailOrder {
    private int idOrder;
    private int idMenu;
    private int jumlah;
    private double subtotal;

    // Constructor kosong
    public DetailOrder() {}

    // Constructor lengkap
    public DetailOrder(int idOrder, int idMenu, int jumlah, double subtotal) {
        this.idOrder = idOrder;
        this.idMenu = idMenu;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    // Getter dan Setter
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
