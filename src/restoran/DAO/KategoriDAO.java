package restoran.DAO;

import restoran.model.Kategori;
import restoran.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriDAO {
    private Connection connection;

    public KategoriDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    // Create
    public void create(Kategori kategori) {
        String sql = "INSERT INTO kategori (nama_kategori) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kategori.getNamaKategori());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (by ID)
    public Kategori read(int id) {
        Kategori kategori = null;
        String sql = "SELECT * FROM kategori WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kategori = new Kategori(rs.getInt("id"), rs.getString("nama_kategori"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategori;
    }

    // Read All
    public List<Kategori> readAll() {
        List<Kategori> kategoris = new ArrayList<>();
        String sql = "SELECT * FROM kategori";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                kategoris.add(new Kategori(rs.getInt("id"), rs.getString("nama_kategori")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoris;
    }

    // Update
    public void update(Kategori kategori) {
        String sql = "UPDATE kategori SET nama_kategori = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kategori.getNamaKategori());
            ps.setInt(2, kategori.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Kategori kategori) {
        String sql = "DELETE FROM kategori WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, kategori.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get ID Kategori by Nama Kategori
    public int getIdByName(Kategori kategori) {
        int idKategori = -1;
        String sql = "SELECT id FROM kategori WHERE nama_kategori = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kategori.getNamaKategori());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idKategori = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idKategori;
    }
}
