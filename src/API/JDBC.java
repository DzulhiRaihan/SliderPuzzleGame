package API;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    static Connection client;
    static String user_id;

    public JDBC() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
        try {
            String url = "jdbc:postgresql://db.ingwwupmzptwgnsqxhjg.supabase.co:5432/postgres?user=postgres&password=Kelompok10_";
            client = DriverManager.getConnection(url);
            System.out.println("Connection success.");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void setUser_id(String user_id) {
        JDBC.user_id = user_id;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static void main(String[] args) {
        new JDBC();
    }
}
