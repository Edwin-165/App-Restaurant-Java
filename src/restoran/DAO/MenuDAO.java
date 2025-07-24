package restoran.dao;

import restoran.model.Menu;
import restoran.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private Connection connection;

    public MenuDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void create(Menu menu) {
        String sql = "INSERT INTO menu (id_kategori, nama, harga) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, menu.getIdKategori());
            ps.setString(2, menu.getNama()); 
            ps.setDouble(3, menu.getHarga());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdByName(String namaMenu, String kategori) {
        // Implementasi untuk mendapatkan ID menu berdasarkan nama dan kategori
        String query = "SELECT m.id FROM menu m "
                + "JOIN kategori k ON m.id_kategori = k.id "
                + "WHERE m.nama = ? AND k.nama_kategori = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, namaMenu);
            ps.setString(2, kategori);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // ID tidak ditemukan
    }
    
    public String getMenuNameById(int menuId) {
        String query = "SELECT nama FROM menu WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, menuId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nama");  // Mengembalikan nama menu
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Tidak Diketahui";  // Jika menu tidak ditemukan
    }

    public List<Menu> readAll() {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                menus.add(new Menu(rs.getInt("id_kategori"), rs.getString("nama"),  rs.getDouble("harga")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menus;
    }
    

    public void update(Menu menu) {
        String sql = "UPDATE menu SET id_kategori = ?, nama = ?, harga = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, menu.getIdKategori());
            ps.setString(2, menu.getNama());
            ps.setDouble(3, menu.getHarga());
            ps.setInt(4, menu.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
