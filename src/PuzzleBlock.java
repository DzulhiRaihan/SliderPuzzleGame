import javax.swing.*;

public class PuzzleBlock {
    JFrame jframe;
    static JPanel mainPanel;
    static MainPanelGame mainPanelGame;

    JButton shuffleBtn;
    
    public PuzzleBlock(){
        jframe = new JFrame();
        mainPanel = new JPanel();

        jframe.setSize(880, 590);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(mainPanel, "Center");
        mainPanel.setLayout(null);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);

        mainPanelGame = new MainPanelGame(2, 550, 30);
        mainPanelGame.setBounds(0, 0, 550, 590);
        mainPanel.add(mainPanelGame);

        shuffleBtn = new JButton("Shuffle");
        shuffleBtn.setBounds(570, 20, 100, 50);
        mainPanel.add(shuffleBtn);

        shuffleBtn.addActionListener(e -> {
            mainPanelGame.newGame();
        });
    }

    static void popUp(){
        JFrame frame = new JFrame();
        frame.setTitle("Slide Puzzle");
        frame.setSize(300,150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel, "Center");
        panel.setLayout(null);

        JLabel label = new JLabel("You Win!");
        label.setBounds(115, 20, 100, 10);
        panel.add(label);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(30, 45, 100, 20);
        panel.add(playAgainButton);

        JButton NextLevelButton = new JButton("Next Level");
        NextLevelButton.setBounds(155, 45, 100, 20);
        panel.add(NextLevelButton);
        frame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            mainPanelGame.addMouseListener(mainPanelGame.myMouseListener);
            mainPanelGame.newGame();
            frame.dispose();
        });

        NextLevelButton.addActionListener(e -> {
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleBlock();
        });
    }

}
