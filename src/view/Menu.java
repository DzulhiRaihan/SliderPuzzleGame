package view;

import java.awt.Color;
import component.MyButton;

import javax.swing.*;
import component.PixelFont;

public class Menu {
    JFrame jframe = new JFrame();   

    MyButton playBtn = new MyButton("Play");
    MyButton profileBtn = new MyButton("Profile");
    MyButton LeaderboardBtn = new MyButton("Leaderboard");

    JLabel welcomeLabel = new JLabel("WELCOME");
    JLabel optionLabel = new JLabel("Select an option Below");

    JLabel background = new JLabel(new ImageIcon("src/assets/Mainmenu.jpg"), JLabel.CENTER);

    JPanel layer = new JPanel();

    public Menu(){
        jframe.setSize(300, 338);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        background.setBounds(0, 0, 300, 300);
        jframe.add(background);

        layer.setBackground(new Color(0,0,0,80));
        layer.setBounds(0, 0, 300, 300);
        jframe.add(layer);
        
        
        playBtn.setBounds(75, 70, 150, 30);
        background.add(playBtn);
        playBtn.setRadius(10);
        playBtn.setFont(PixelFont.getFont(1, 12));
        
        profileBtn.setBounds(75, 130, 150, 30);
        background.add(profileBtn);
        profileBtn.setRadius(10);
        profileBtn.setFont(PixelFont.getFont(1, 12));
        
        LeaderboardBtn.setBounds(75, 190, 150, 30);
        background.add(LeaderboardBtn);
        LeaderboardBtn.setRadius(10);
        LeaderboardBtn.setFont(PixelFont.getFont(1, 12));
        
        welcomeLabel.setBounds(93, 10, 170, 30);
        welcomeLabel.setFont(PixelFont.getFont(1,30));
        welcomeLabel.setForeground(Color.BLUE);
        background.add(welcomeLabel);
        
        optionLabel.setBounds(62, 35, 190, 30);
        optionLabel.setFont(PixelFont.getFont(1,15));
        background.add(optionLabel);
    
        
        playBtn.addActionListener(e -> {
            new Level();
            jframe.dispose();
        });

        profileBtn.addActionListener(e -> {
            try {
                new profilePage();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            jframe.dispose();
        });

        LeaderboardBtn.addActionListener(e -> {
            try {
                new leaderboardPage();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            jframe.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu();
        });
    }
}
