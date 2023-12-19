package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.sql.ResultSet;

import API.JDBC;
import API.Profile;
import component.PixelFont;

public class leaderboardPage {
    JFrame frame = new JFrame();
    JLabel backGround = new JLabel(new ImageIcon("src/assets/Mainmenu.jpg"), JLabel.CENTER);

    JLabel yourProfile = new JLabel("LEADERBOARD");
    JLabel userName = new JLabel();

   
    JLabel rankLabel = new JLabel("RANK");
    JLabel usernameLabel = new JLabel("USERNAME");
    JLabel totalMoveLabel = new JLabel("TOTAL MOVE ");
    JLabel scoreLabel = new JLabel("SCORE");

    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

    public leaderboardPage() {
        new JDBC();
        frame.setSize(300, 338);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        backGround.setBounds(0, 0, 300, 300);
        frame.add(backGround);

        yourProfile.setBounds(80, 20, 160, 20);
        backGround.add(yourProfile);
        yourProfile.setFont(PixelFont.getFont(1, 20));

        rankLabel.setBounds(20, 48, 70, 20);
        backGround.add(rankLabel);
        rankLabel.setFont(PixelFont.getFont(1, 12));

        usernameLabel.setBounds(65, 48, 70, 20);
        backGround.add(usernameLabel);
        usernameLabel.setFont(PixelFont.getFont(1, 12));

        totalMoveLabel.setBounds(140, 48, 95, 20);
        backGround.add(totalMoveLabel);
        totalMoveLabel.setFont(PixelFont.getFont(1, 12));

        scoreLabel.setBounds(230, 48, 70, 20);
        backGround.add(scoreLabel);
        scoreLabel.setFont(PixelFont.getFont(1, 12));

        try {
            ResultSet result = Profile.getLeaderboard();
            while (result.next()) {
                String username = result.getString("username");
                int totalMove = result.getInt("total_move");
                int score = result.getInt("score");
                
                JLabel rank = new JLabel();
                rank.setText(String.valueOf(result.getRow()));
                rank.setBounds(30, 50 + (result.getRow() * 25), 70, 20);
                backGround.add(rank);
                rank.setFont(PixelFont.getFont(1, 15));

                JLabel usernameLabel = new JLabel();
                usernameLabel.setText(username);
                usernameLabel.setBounds(65, 50 + (result.getRow() * 25), 70, 20);
                backGround.add(usernameLabel);
                usernameLabel.setFont(PixelFont.getFont(1, 15));
                
                JLabel totalMoveLabel = new JLabel();
                totalMoveLabel.setText(String.valueOf(totalMove));
                totalMoveLabel.setBounds(140, 50 + (result.getRow() * 25), 95, 20);
                backGround.add(totalMoveLabel);
                totalMoveLabel.setFont(PixelFont.getFont(1, 15));

                JLabel timeLabel = new JLabel();
                timeLabel.setText(String.valueOf(score));
                timeLabel.setBounds(230, 50 + (result.getRow() * 25), 70, 20);
                backGround.add(timeLabel);
                timeLabel.setFont(PixelFont.getFont(1, 15));
                
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new leaderboardPage();
    }
}
