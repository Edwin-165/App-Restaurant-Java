package restoran.dao;

import restoran.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restoran.model.Order;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order`";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getTimestamp("tanggal_order"),
                        rs.getString("status"),
                        rs.getDouble("total_harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public List<Order> getAllUnpaidOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE status = 'Unpaid'";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getTimestamp("tanggal_order"),
                        rs.getString("status"),
                        rs.getDouble("total_harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public List<Order> getAllPaidOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE status = 'Paid'";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("id"),
                        rs.getTimestamp("tanggal_order"),
                        rs.getString("status"),
                        rs.getDouble("total_harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM `order` WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int createNewOrder(Order order) {
        String sql = "INSERT INTO `order` (tanggal_order, status, total_harga) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, new Timestamp(order.getTanggalOrder().getTime()));
            ps.setString(2, order.getStatus());
            ps.setDouble(3, order.getTotalHarga());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return generated ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void updateTotalHarga(Order order) {
        String sql = "UPDATE `order` SET total_harga = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, order.getTotalHarga());
            ps.setInt(2, order.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
