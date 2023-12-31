package API;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Profile {
    public static String getUserName() throws SQLException {
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

    public static int getTotalMove() throws SQLException {
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

    public static int getScore() throws SQLException {
        String query = "SELECT score FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        int score = 0;
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                score = rs.getInt("score");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }

    public static int updateTotalTime(int minute, int second) throws SQLException {
        String updateQuery;
        String checkQuery = "SELECT minute, second FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        int result = 0;
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(checkQuery);
            if (rs.next()) {
                int rsMinute = rs.getInt("minute");
                int rsSecond = rs.getInt("second");

                if (rsMinute > minute || rsMinute == 0) {
                    updateQuery = "UPDATE \"score\" SET minute = " + minute + " WHERE user_id = '" + JDBC.getUser_id()
                            + "'";
                } else {
                    updateQuery = "UPDATE \"score\" SET minute = " + rsMinute + " WHERE user_id = '" + JDBC.getUser_id()
                            + "'";
                }
                result = stmt.executeUpdate(updateQuery);

                if (rsSecond > second || rsSecond == 0) {
                    updateQuery = "UPDATE \"score\" SET second = " + second + " WHERE user_id = '" + JDBC.getUser_id()
                            + "'";
                } else {
                    updateQuery = "UPDATE \"score\" SET second = " + rsSecond + " WHERE user_id = '" + JDBC.getUser_id()
                            + "'";
                }
                result = stmt.executeUpdate(updateQuery);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int updateTotalMoveAndScore(int totalMove, int score) {
        String updateQuery;
        String checkQuery = "SELECT total_move, score FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
        int result = 0;
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(checkQuery);
            if (rs.next()) {
                int currentTotalMove = rs.getInt("total_move");
                int currentScore = rs.getInt("score");

                if (currentTotalMove > totalMove) {
                    currentTotalMove = totalMove;
                }

                if (currentScore < score) {
                    currentScore = score;
                }

                updateQuery = "UPDATE \"score\" SET total_move = " + currentTotalMove + ", score = " + currentScore
                        + " WHERE user_id = '" + JDBC.getUser_id() + "'";
                result = stmt.executeUpdate(updateQuery);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // public static int updateTotalMove(int totalMove) throws SQLException {
    //         String updateQuery;
    //         String checkQuery = "SELECT total_move, score FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
    //         int result = 0;
    //         try (Statement stmt = JDBC.client.createStatement()) {
    //             ResultSet rs = stmt.executeQuery(checkQuery);
    //             if (rs.next()) {
    //                 int currentTotalMove = rs.getInt("total_move");
    //                 int currentScore = rs.getInt("score");

    //                 if (currentTotalMove > totalMove) {
    //                     updateQuery = "UPDATE \"score\" SET total_move = " + totalMove + " WHERE user_id = '"
    //                             + JDBC.getUser_id() + "'";
    //                 } else {
    //                     updateQuery = "UPDATE \"score\" SET total_move = " + currentTotalMove + " WHERE user_id = '"
    //                             + JDBC.getUser_id() + "'";
    //                 }
    //                 result = stmt.executeUpdate(updateQuery);
    //             }
    //         } catch (SQLException e) {
    //             System.out.println(e.getMessage());
    //         }
    //         return result;
    //     }

    // public static int updateScore(int score) throws SQLException{
    //     String updateQuery;
    //     String checkQuery = "SELECT score FROM \"score\" WHERE user_id = '" + JDBC.getUser_id() + "'";
    //     int result = 0;
    //     try (Statement stmt = JDBC.client.createStatement()) {
    //         ResultSet rs = stmt.executeQuery(checkQuery);
    //         if (rs.next()) {
    //             if (rs.getInt("score") > score || rs.getInt("score") == 0) {
    //                 updateQuery = "UPDATE \"score\" SET score = " + score + " WHERE user_id = '"
    //                         + JDBC.getUser_id() + "'";
    //             } else {
    //                 updateQuery = "UPDATE \"score\" SET score = " + rs.getInt("score") + " WHERE user_id = '"
    //                         + JDBC.getUser_id() + "'";
    //             }
    //             result = stmt.executeUpdate(updateQuery);
    //         }
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     }
    //     return result;
    // }

    public static ResultSet getLeaderboard() throws SQLException {
        String query = "SELECT username, total_move, score FROM \"user\" JOIN \"score\" USING (user_id) ORDER BY score DESC, total_move ASC LIMIT 10";
        PreparedStatement preparedStatement = JDBC.client.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

}
