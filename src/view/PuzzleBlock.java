package view;

import javax.swing.*;

import controll.MainPanelGame;
import controll.Timer;

public class PuzzleBlock {
    static JFrame jframe = new JFrame();
    static JPanel mainPanel;
    static MainPanelGame mainPanelGame;
    JButton shuffleBtn = new JButton("Shuffle");
    JButton pauseButton = new JButton("Pause");
    JLabel timeLabel = new JLabel("Time: ");

    public PuzzleBlock(int size, int dim, int mar) {
        mainPanel = new JPanel();

        jframe.setSize(880, 590);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(mainPanel, "Center");
        mainPanel.setLayout(null);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);

        mainPanelGame = new MainPanelGame(size, dim, mar);
        mainPanelGame.setBounds(0, 0, 550, 590);
        mainPanel.add(mainPanelGame);

        shuffleBtn.setBounds(570, 20, 100, 50);
        mainPanel.add(shuffleBtn);

        pauseButton.setBounds(690, 20, 100, 50);
        mainPanel.add(pauseButton);

        timeLabel.setBounds(570, 100, 100, 50);
        mainPanel.add(timeLabel);

        MainPanelGame.timer.setBounds(570, 150, 100, 50);
        mainPanel.add(MainPanelGame.timer);

        shuffleBtn.addActionListener(e -> {
            mainPanelGame.newGame();
        });

        pauseButton.addActionListener(e -> {
            if (Timer.isRunning) {
                MainPanelGame.timer.stop();
                pauseButton.setText("Resume");
                mainPanelGame.removeMouseListener(mainPanelGame.myMouseListener);
            } else {
                if (mainPanelGame.isTimerRun == true) {
                    MainPanelGame.timer.resume();
                    pauseButton.setText("Pause");
                    mainPanelGame.addMouseListener(mainPanelGame.myMouseListener);
                }
            }
        });

    }

    public static void popUp() {
        mainPanelGame.removeMouseListener(mainPanelGame.myMouseListener);
        JFrame frame = new JFrame();
        frame.setTitle("Slide Puzzle");
        frame.setSize(300, 150);
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

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(155, 45, 100, 20);
        panel.add(exitButton);
        frame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            mainPanelGame.addMouseListener(mainPanelGame.myMouseListener);
            mainPanelGame.gameOver = false;
            mainPanelGame.isTimerRun = false;
            MainPanelGame.timer.reset();
            mainPanelGame.newGame();
            frame.dispose();
        });

        exitButton.addActionListener(e -> {
            MainPanelGame.timer.reset();
            new Level();
            jframe.dispose();
            frame.dispose();
        });
    }

    public void start() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleBlock(2, 550, 30);
        });
    }

}
