package API;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {

    public static void register (String username, String Password){
        String checkQuery = "SELECT * FROM \"user\" WHERE username = '" + username + "'";
        String insertQuery = "INSERT INTO \"user\" (username, password) VALUES ('" + username + "', '" + Password + "')";

        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(checkQuery);

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Username already exist");
            } else {
                stmt.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Register success");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
       
    }
}