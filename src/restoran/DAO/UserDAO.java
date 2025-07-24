package restoran.DAO;

import restoran.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class UserDAO {
    
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    // Login User
    public boolean login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Pastikan password disimpan aman (hashing)

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Login berhasil jika ada hasil
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean register(String username, String password, String confirm){
        if (!password.equals(confirm)){
            System.out.println("Password dadn confirmasi tidak cocok");
            return false;
        }
        
        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        
        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            int rowsInserted = stmt.executeUpdate();
            
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
