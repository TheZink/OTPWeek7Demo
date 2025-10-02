import java.sql.*;
import javafx.scene.control.Label;

public class Database {

    private static final String URL = "jdbc:mysql://host.docker.internal:3306/javafx_mariadb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void saveTemperature(double celsius, double result, String convertTo, Label statusLabel) {
        String sql = "INSERT INTO temperature_log (celsius, convert_to, result) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, celsius);
            stmt.setString(2, convertTo);
            stmt.setDouble(3, result);
            stmt.executeUpdate();
            statusLabel.setText("Saved to database!");

        } catch (SQLException e) {
            statusLabel.setText("DB Error: " + e.getMessage());
        }
    }
}
