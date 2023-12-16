package view;

import java.sql.SQLException;

import javax.swing.*;

import API.Profile;
import component.Game;
import controll.MainPanelGame;
import controll.Timer;

public class PuzzleBlock implements Game {
    static JFrame jframe = new JFrame();
    static JPanel mainPanel;
    static MainPanelGame mainPanelGame;
    JButton shuffleBtn = new JButton("Shuffle");
    JButton pauseButton = new JButton("Pause");
    JButton solveButton = new JButton("Solve");

    JLabel timeLabel = new JLabel("Time: ");
    JLabel moveLabel = new JLabel("Move: ");

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
        MainPanelGame.timer.setBounds(610, 100, 100, 50);
        mainPanel.add(MainPanelGame.timer);

        mainPanelGame.moveLabel.setBounds(570, 150, 100, 50);
        mainPanel.add(mainPanelGame.moveLabel);

        shuffleBtn.addActionListener(e -> {
            mainPanelGame.newGame();
        });

        pauseButton.addActionListener(e -> {
            pauseGame();
        });

    }

    public void pauseGame() {
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
    }

    public static void popUp() throws SQLException {
        mainPanelGame.removeMouseListener(mainPanelGame.myMouseListener);

        Profile.updateTotalMove(mainPanelGame.getMoveCount());
        Profile.updateTotalTime(MainPanelGame.timer.getMinutes(), MainPanelGame.timer.getSeconds());
        JFrame frame = new JFrame();
        frame.setTitle("Slide Puzzle");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.add(panel, "Center");
        panel.setLayout(null);

        JLabel label = new JLabel("You Win!!!");
        label.setBounds(115, 20, 100, 10);
        panel.add(label);

        JLabel totalCountLabel = new JLabel("Total Move: " + mainPanelGame.getMoveCount());
        totalCountLabel.setBounds(100, 35, 100, 10);
        panel.add(totalCountLabel);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(30, 55, 100, 20);
        panel.add(playAgainButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(155, 55, 100, 20);
        panel.add(exitButton);
        frame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            mainPanelGame.addMouseListener(mainPanelGame.myMouseListener);
            mainPanelGame.gameOver = false;
            mainPanelGame.isTimerRun = false;
            mainPanelGame.newGame();
            MainPanelGame.timer.reset();
            mainPanelGame.resetMoveCount();
            frame.dispose();
        });

        exitButton.addActionListener(e -> {
            MainPanelGame.timer.reset();
            mainPanelGame.resetMoveCount();
            new Level();
            jframe.dispose();
            frame.dispose();
        });
    }

    public void start() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleBlock(3, 550, 30);
        });
    }

}
