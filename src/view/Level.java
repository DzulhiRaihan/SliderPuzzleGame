package view;

import javax.swing.*;
import java.awt.*;

import component.MyButton;
import component.PixelFont;
public class Level {

    JFrame jframe = new JFrame();
    MyButton level1Btn = new MyButton("2x2");
    MyButton level2Btn = new MyButton("3x3");
    MyButton level3Btn = new MyButton("4x4");
    MyButton backToMenu = new MyButton("Back to Menu");
    JLabel letsPlayLabel = new JLabel("LET'S PLAY!!!");
    JLabel chooseLevelLabel = new JLabel("Choose Your Limits!");

    JLabel background = new JLabel(new ImageIcon("src/assets/level.jpg"), JLabel.CENTER);
    JPanel layer = new JPanel();


    public Level(){
        jframe.setSize(300, 338);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setLayout(null);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        background.setBounds(0, 0, 300, 300);
        jframe.add(background);

        letsPlayLabel.setBounds(63, 10, 190, 20);
        letsPlayLabel.setFont(PixelFont.getFont(1, 25));
        letsPlayLabel.setForeground(Color.BLUE);
        background.add(letsPlayLabel);

        chooseLevelLabel.setBounds(70, 40, 190, 20);
        chooseLevelLabel.setFont(PixelFont.getFont(1, 15));
        background.add(chooseLevelLabel);

        level1Btn.setBounds(75, 70, 150, 30);
        background.add(level1Btn);
        level1Btn.setRadius(10);
        level1Btn.setFont(PixelFont.getFont(1, 12));

        level2Btn.setBounds(75, 120, 150, 30);
        background.add(level2Btn);
        level2Btn.setRadius(10);
        level2Btn.setFont(PixelFont.getFont(1, 12));

        level3Btn.setBounds(75, 180, 150, 30);
        background.add(level3Btn);
        level3Btn.setRadius(10);
        level3Btn.setFont(PixelFont.getFont(1, 12));

        backToMenu.setBounds(75, 230, 150, 30);
        background.add(backToMenu);
        backToMenu.setRadius(10);
        backToMenu.setFont(PixelFont.getFont(1, 12));

        layer.setBackground(new Color(0,0,0,80));
        layer.setBounds(0, 0, 300, 300);
        jframe.add(layer);

        level1Btn.addActionListener(e -> {
            new PuzzleBlock(2, 550, 20);
            jframe.dispose();
        });

        level2Btn.addActionListener(e -> {
            new PuzzleBlock(3, 550, 20);
            jframe.dispose();
        });

        level3Btn.addActionListener(e -> {
            new PuzzleBlock(4, 550, 20);
            jframe.dispose();
        });

        backToMenu.addActionListener(e -> {
            new MenuPage();
            jframe.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Level();
        });
    }
}