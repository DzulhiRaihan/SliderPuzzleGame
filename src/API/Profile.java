package API;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Profile {
    public static String getUserName()throws SQLException{
        String query = "SELECT username FROM \"user\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        String username = "";
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return username;
    }

    public static int getTotalMove()throws SQLException{
        String query = "SELECT total_move FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        int totalMove = 0;
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                totalMove = rs.getInt("total_move");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalMove;
    }   

    public static int updateTotalMove(int totalMove)throws SQLException{
        String updateQuery;
        String checkQuery = "SELECT total_move FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        int result = 0;
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(checkQuery);
            if (rs.next()) {
                if (rs.getInt("total_move") > totalMove){
                    updateQuery = "UPDATE \"score\" SET total_move = " + totalMove + " WHERE user_id = '" + JDBC.getUser_id() + "'";
                } else {
                    updateQuery = "UPDATE \"score\" SET total_move = " + rs.getInt("total_move") + " WHERE user_id = '" + JDBC.getUser_id() + "'";
                }
                result = stmt.executeUpdate(updateQuery);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static ResultSet getLeaderboard()throws SQLException{
        String query = "SELECT username, total_move FROM \"user\" JOIN \"score\" USING (user_id) ORDER BY total_move ASC LIMIT 10";
        PreparedStatement preparedStatement = JDBC.client.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    
}
