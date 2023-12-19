package view;
import java.sql.SQLException;

import javax.swing.*;

import API.Profile;
import component.Game;
import controll.MainPanelGame;
import controll.Timer;
import component.MyButton;
import component.PixelFont;

public class PuzzleBlock implements Game {
    static JFrame jframe = new JFrame();
    static JPanel mainPanel;
    static MainPanelGame mainPanelGame;
    MyButton shuffleBtn = new MyButton("Shuffle");
    MyButton pauseButton = new MyButton("Pause");
    
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

        shuffleBtn.setBounds(570, 20, 120, 30);
        mainPanel.add(shuffleBtn);
        shuffleBtn.setFont(PixelFont.getFont(15));

        pauseButton.setBounds(730, 20, 120, 30);
        mainPanel.add(pauseButton);
        pauseButton.setFont(PixelFont.getFont(15));

        timeLabel.setBounds(570, 60, 100, 50);
        timeLabel.setFont(PixelFont.getFont(15));
        mainPanel.add(timeLabel);
        
        MainPanelGame.timer.setBounds(615, 60, 100, 50);
        MainPanelGame.timer.setFont(PixelFont.getFont(15));
        mainPanel.add(MainPanelGame.timer);

        MainPanelGame.scoreLabel.setBounds(570, 100, 300, 50);
        MainPanelGame.scoreLabel.setFont(PixelFont.getFont(15));
        mainPanel.add(MainPanelGame.scoreLabel);

        mainPanelGame.moveLabel.setBounds(735, 60, 100, 50);
        mainPanelGame.moveLabel.setFont(PixelFont.getFont(15));
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
            MainPanelGame.scoreLabel.stop();
            pauseButton.setText("Resume");
            mainPanelGame.removeMouseListener(mainPanelGame.myMouseListener);
        } else {
            if (mainPanelGame.isTimerRun == true) {
                MainPanelGame.timer.resume();
                MainPanelGame.scoreLabel.resume();
                pauseButton.setText("Pause");
                mainPanelGame.addMouseListener(mainPanelGame.myMouseListener);
            }
        }
    }

    public static void popUp() throws SQLException {
        mainPanelGame.removeMouseListener(mainPanelGame.myMouseListener);

        Profile.updateTotalMoveAndScore(MainPanelGame.getMoveCount(),MainPanelGame.scoreLabel.getScore());
        Profile.updateTotalTime(MainPanelGame.timer.getMinutes(), Timer.getSeconds());
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

        JLabel totalCountLabel = new JLabel("Total Move: " + MainPanelGame.getMoveCount());
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
            MainPanelGame.scoreLabel.reset();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleBlock(3, 550, 30);
        });
    }

}
