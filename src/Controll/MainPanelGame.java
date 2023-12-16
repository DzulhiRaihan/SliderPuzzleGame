package controll;

import view.PuzzleBlock;

import javax.imageio.ImageIO;
import javax.swing.*;
import component.PixelFont;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class MainPanelGame extends JPanel {

    public MainPanelGame(int size, int dim, int mar) {

        this.size = size;
        dimension = dim;
        margin = mar;

        nbTiles = size * size - 1; // -1 because we don't count blank tile
        tiles = new int[size * size];

        // Calculate grid size and tile size
        gridSize = (dim - 2 * margin);
        tileSize = gridSize / size;

        setPreferredSize(new Dimension(dimension, dimension + margin));
        setForeground(new Color(248, 239, 186));
        setFont(PixelFont.getFont(60));

        gameOver = true;
        myMouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isTimerRun == false) {
                    timer.start();
                    isTimerRun = true;
                }

                if (gameOver) {
                    newGame();
                } else {
                    int ex = e.getX() - margin;
                    int ey = e.getY() - margin;

                    // click in the grid
                    if (ex < 0 || ex > gridSize || ey < 0 || ey > gridSize) {
                        return;
                    }

                    // get position in the grid
                    int c1 = ex / tileSize;
                    int r1 = ey / tileSize;

                    // get position of the blank cell
                    int c2 = blankPos % size;
                    int r2 = blankPos / size;

                    // we convert in the linear array
                    int clickPos = r1 * size + c1;

                    int dir = 0;

                    // we search direction for multiple tile moves
                    if (c1 == c2 && Math.abs(r1 - r2) > 0) {
                        dir = (r1 - r2) > 0 ? size : -size;
                    } else if (r1 == r2 && Math.abs(c1 - c2) > 0) {
                        dir = (c1 - c2) > 0 ? 1 : -1;
                    }

                    if (dir != 0) {
                        // we move tiles in the direction
                        do {
                            int newBlankPos = blankPos + dir;
                            tiles[blankPos] = tiles[newBlankPos];
                            blankPos = newBlankPos;
                        } while (blankPos != clickPos);

                        tiles[blankPos] = 0;
                        moveCount++;
                        setMoveLabel();
                    }

                    gameOver = isSolved();

                }
                repaint();
            }
        };
        addMouseListener(myMouseListener);
        newGame();

        try {
            backgroundImage = ImageIO.read(new File("src/assets/playingGame.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMoveLabel() {
        moveLabel.setText("Move: " + moveCount);
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void resetMoveCount() {
        moveCount = 0;
        moveLabel.setText("Move: " + moveCount);
    }

    public int getTileSize() {
        return size;
    }

    public void newGame() {
        do {
            reset();
            shuffle();
            System.out.println(Arrays.toString(tiles) + " " + isSolvable());
        } while (!isSolvable());
        gameOver = false;
    }

    public void updateSize(int newSize) {
        this.size = newSize;
    }

    private void reset() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }
        blankPos = tiles.length - 1;
    }

    public void shuffle() {
        int n = nbTiles;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
        System.out.println(Arrays.toString(tiles) + " " + isSolvable());
        repaint();
    }

    private boolean isSolvable() {
        int countInversions = 0;

        for (int i = 0; i < nbTiles; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i]) {
                    countInversions++;
                }
            }
        }

        return countInversions % 2 == 0;
    }

    boolean isSolved() {
        if (tiles[tiles.length - 1] != 0) {
            return false;
        }
        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    private void drawGrid(Graphics2D g) throws SQLException {
        for (int i = 0; i < tiles.length; i++) {
            int r = i / size;
            int c = i % size;
            int x = margin + c * tileSize;
            int y = margin + r * tileSize;

            if (tiles[i] == 0) {
                if (gameOver) {
                    gameOver = false;
                    isTimerRun = false;
                    removeMouseListener(getMouseListeners()[0]);
                    timer.stop();
                    PuzzleBlock.popUp();
                }
                continue;
            }

            g.setColor(getForeground());
            g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.GRAY);

            drawCenteredString(g, String.valueOf(tiles[i]), x, y);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g.drawString(s, x + (tileSize - fm.stringWidth(s)) / 2,
                y + (asc + (tileSize - (asc + desc)) / 2));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
        try {
            drawGrid(g2D);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainPanelGame mainPanelGame = new MainPanelGame(3, 550, 30);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Slide Puzzle");
            frame.setResizable(false);
            frame.add(mainPanelGame, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        });
    }

    private int size;
    private static int nbTiles;
    public int dimension;
    private static final Random RANDOM = new Random();
    private static int[] tiles;
    private int tileSize;
    private int blankPos;
    public int margin;
    private int gridSize;
    public boolean gameOver;
    public MouseAdapter myMouseListener;
    public boolean isTimerRun = false;
    public static Timer timer = new Timer();
    private static int moveCount = 0;
    public JLabel moveLabel = new JLabel("Move: " + moveCount);
    private Image backgroundImage;

}
