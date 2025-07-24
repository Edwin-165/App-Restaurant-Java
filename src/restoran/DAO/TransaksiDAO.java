package restoran.dao;

import restoran.model.Transaksi;
import restoran.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    private Connection connection;

    public TransaksiDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public List<Transaksi> getPendingOrders() {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT o.id AS order_id, o.total_harga, o.status, t.id, t.tanggal_transaksi " +
                     "FROM `order` o " +
                     "LEFT JOIN transaksi t ON o.id = t.id_order " +
                     "WHERE o.status = 'Unpaid'";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                transaksiList.add(new Transaksi(
                        rs.getInt("id"),
                        rs.getInt("order_id"),
                        rs.getDouble("total_harga"),
                        rs.getDate("tanggal_transaksi"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaksiList;
    }

    public void createTransaction(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi (id_order, total_pembayaran, tanggal_transaksi, metode_pembayaran) " +
                     "VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transaksi.getIdOrder());
            ps.setDouble(2, transaksi.getTotalPembayaran());
            ps.setString(3, transaksi.getMetodePembayaran());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderStatusToPaid(Transaksi transaksi) {
        String sql = "UPDATE `order` SET status = 'Paid' WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transaksi.getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
