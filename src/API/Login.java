package API;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    public static boolean isLogin = false;

    public static void login(String username, String password) throws SQLException {
        String query = "SELECT * FROM \"user\" WHERE username = '" + username + "'";

        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                JDBC.setUser_id(rs.getString("user_id"));
                String passDB = rs.getString("password");

                if (password.equals(passDB)) {
                    System.out.println("Login success.");
                    JOptionPane.showMessageDialog(null, "Login success");
                    isLogin = true;
                } else {
                    System.out.println("Login failed.");
                    JOptionPane.showMessageDialog(null, "Login failed");
                }
            } else {
                System.out.println("Login failed.");
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        // new JDBC();
        login("raihan", "123");
    }
}
