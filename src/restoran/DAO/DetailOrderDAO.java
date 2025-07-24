package restoran.dao;

import restoran.util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import restoran.model.DetailOrder;

public class DetailOrderDAO {
    private Connection connection;

    public DetailOrderDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public List<DetailOrder> getDetailsByOrderId(int orderId) {
        List<DetailOrder> details = new ArrayList<>();
        String sql = "SELECT * FROM detail_order WHERE id_order = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    details.add(new DetailOrder(
                            rs.getInt("id_order"),
                            rs.getInt("id_menu"),
                            rs.getInt("jumlah"),
                            rs.getDouble("subtotal")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }
    
    public void addDetail(DetailOrder detailOrder) {
        String sql = "INSERT INTO detail_order (id_order, id_menu, jumlah, subtotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, detailOrder.getIdOrder());
            ps.setInt(2, detailOrder.getIdMenu());
            ps.setInt(3, detailOrder.getJumlah());
            ps.setDouble(4, detailOrder.getSubtotal());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
