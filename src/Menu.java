import javax.swing.*;

public class Menu {
    JFrame jframe = new JFrame();
    JPanel mainPanel = new JPanel();

    JButton playBtn = new JButton("Play");
    JButton profileBtn = new JButton("Profile");
    JButton LeaderboardBtn = new JButton("Leaderboard");

    public Menu(){
        jframe.setSize(300, 300);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(mainPanel, "Center");
        mainPanel.setLayout(null);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);

        playBtn.setBounds(75, 40, 150, 30);
        mainPanel.add(playBtn);

        profileBtn.setBounds(75, 100, 150, 30);
        mainPanel.add(profileBtn);

        LeaderboardBtn.setBounds(75, 160, 150, 30);
        mainPanel.add(LeaderboardBtn);

        playBtn.addActionListener(e -> {
            new Level();
            jframe.dispose();
        });

        profileBtn.addActionListener(e -> {
//            new Profile();
            jframe.dispose();
        });

        LeaderboardBtn.addActionListener(e -> {
//            new Leaderboard();
            jframe.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu();
        });
    }
}
