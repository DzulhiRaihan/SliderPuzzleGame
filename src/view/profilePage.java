package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import API.Profile;

import java.awt.Color;
import java.sql.SQLException;

import component.MyButton;
import component.PixelFont;

public class profilePage {

    private String username;
    private int move;

    JFrame frame = new JFrame();
    JLabel backGround = new JLabel(new ImageIcon("src/assets/Mainmenu.jpg"), JLabel.CENTER);

    JLabel yourProfile = new JLabel("YOUR PROFILE");
    JLabel usernameLabel = new JLabel("USERNAME");

    JLabel userName = new JLabel();
    
    JLabel totalMoveLabel = new JLabel("TOTAL MOVE ");
    JLabel totalMove = new JLabel();

    MyButton backBtn = new MyButton("Back");
    
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

    int minutes = 0;
    int seconds = 0;

    JLabel timeLabel = new JLabel("TIME ");
    public profilePage() throws SQLException {
        username = Profile.getUserName();
        move = Profile.getTotalMove();

        frame.setSize(300, 338);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);

        backGround.setBounds(0, 0, 300, 300);
        frame.add(backGround);

        yourProfile.setBounds(80, 20, 160, 20);
        backGround.add(yourProfile);
        yourProfile.setFont(PixelFont.getFont(1, 20));

        usernameLabel.setBounds(114, 50, 160, 20);
        backGround.add(usernameLabel);
        usernameLabel.setFont(PixelFont.getFont(1, 15));

        userName.setBounds(60, 80, 170, 25);
        backGround.add(userName);
        userName.setFont(PixelFont.getFont(1, 15));
        userName.setBorder(border);
        userName.setBackground(Color.WHITE);
        userName.setOpaque(true);
        userName.setHorizontalAlignment(JLabel.CENTER);
        userName.setText(username);

        totalMoveLabel.setBounds(105, 120, 160, 20);
        backGround.add(totalMoveLabel);
        totalMoveLabel.setFont(PixelFont.getFont(1, 15));

        totalMove.setBounds(60, 150, 170, 25);
        backGround.add(totalMove);
        totalMove.setFont(PixelFont.getFont(1, 15));
        totalMove.setBorder(border);
        totalMove.setBackground(Color.WHITE);
        totalMove.setOpaque(true);
        totalMove.setText(String.valueOf(move));
        totalMove.setHorizontalAlignment(JLabel.CENTER);

        backBtn.setBounds(70, 190, 150, 30);
        backGround.add(backBtn);
        backBtn.setRadius(10);
        backBtn.setFont(PixelFont.getFont(1, 12));

        backBtn.addActionListener(e -> {
            new Menu();
            frame.dispose();
        });
        
    }
    
}