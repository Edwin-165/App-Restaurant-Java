package restoran.model;

public class Menu {
    private int id;
    private int idKategori;
    private String nama;
    
    private double harga;

    public Menu() {
    }

    public Menu(int id, int idKategori, String nama, double harga) {
        this.id = id;
        this.idKategori = idKategori;
        this.nama = nama;
        this.harga = harga;
    }
    
    public Menu(int idKategori, String nama, double harga) {
        this.idKategori = idKategori;
        this.nama = nama;
        this.harga = harga;
    }

    // Getter dan Setter
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) {
        this.id = id; 
    }
    
    public int getIdKategori() { 
        return idKategori; 
    }
    
    public void setIdKategori(int idKategori) { 
        this.idKategori = idKategori; 
    }
    
    public String getNama() { 
        return nama; 
    }
    public void setNama(String nama) { 
        this.nama = nama; 
    }
    
    
    
    public double getHarga() {
        return harga; 
    }
    
    public void setHarga(double harga) { 
        this.harga = harga; 
    }
}
