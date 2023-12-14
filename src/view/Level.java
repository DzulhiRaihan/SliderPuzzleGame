package view;

import javax.swing.*;
import java.awt.*;

public class Level {

    JFrame jframe = new JFrame();
    JPanel mainPanel = new JPanel();
    JButton level1Btn = new JButton("2x2");
    JButton level2Btn = new JButton("3x3");
    JButton level3Btn = new JButton("4x4");
    JLabel label = new JLabel("CHOOSE A LEVEL");

    public Level(){
        jframe.setSize(300, 300);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(mainPanel, "Center");
        mainPanel.setLayout(null);
        jframe.setVisible(true);
        jframe.setLocationRelativeTo(null);

        label.setBounds(80, 0, 150, 20);
        label.setFont(new java.awt.Font("Poppins", Font.BOLD, 15));
        mainPanel.add(label);

        level1Btn.setBounds(95, 40, 100, 50);
        mainPanel.add(level1Btn);

        level2Btn.setBounds(95, 100, 100, 50);
        mainPanel.add(level2Btn);

        level3Btn.setBounds(95, 160, 100, 50);
        mainPanel.add(level3Btn);

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Level();
        });
    }
}