import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

public class MainPanelGame extends JPanel {
    private int size;
    private static int nbTiles;
    public int dimension;
    private static final Color FOREGROUND_COLOR = new Color(239, 83, 80); // For tiles
    private static final Random RANDOM = new Random();
    private static int[] tiles;
    private int tileSize;
    private int blankPos;
    public int margin;
    private int gridSize;
    boolean gameOver;

    public MouseAdapter myMouseListener;
    private static final JButton exitButton = new JButton("Exit");
    private boolean isNextLevelButtonPressed = false;


    public MainPanelGame(int size, int dim, int mar){
        this.size = size;
        dimension = dim;
        margin = mar;

        nbTiles = size * size - 1; // -1 because we don't count blank tile
        tiles = new int[size * size];

        // Calculate grid size and tile size
        gridSize = (dim - 2 * margin);
        tileSize = gridSize / size;

        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(Color.WHITE);
        setForeground(FOREGROUND_COLOR);
        setFont(new Font("SansSerif", Font.BOLD, 60));

        gameOver = true;

        myMouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(gameOver){
                    newGame();
                }else{
                    int ex = e.getX() - margin;
                    int ey = e.getY() - margin;

                    // click in the grid
                    if(ex < 0 || ex > gridSize || ey < 0 || ey > gridSize){
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
                    if(c1 == c2 && Math.abs(r1 - r2) > 0){
                        dir = (r1 - r2) > 0 ? size : -size;
                    }else if(r1 == r2 && Math.abs(c1 - c2) > 0){
                        dir = (c1 - c2) > 0 ? 1 : -1;
                    }

                    if(dir != 0){
                        // we move tiles in the direction
                        do{
                            int newBlankPos = blankPos + dir;
                            tiles[blankPos] = tiles[newBlankPos];
                            blankPos = newBlankPos;
                        }while(blankPos != clickPos);

                        tiles[blankPos] = 0;
                    }

                    gameOver = isSolved();
                }
                repaint();
            }
        };
        addMouseListener(myMouseListener);
        newGame();
    }

    public int getTileSize(){
        return size;
    }

    public void newGame(){
        do{
           reset();
           shuffle();
            System.out.println(Arrays.toString(tiles) + " " + isSolvable());
            System.out.println();
            System.out.println(getTileSize());
        }while(!isSolvable());
        gameOver = false;
    }

    public void updateSize(int newSize) {
        this.size = newSize;
    }

    private void reset(){
        for(int i = 0; i < tiles.length; i++){
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

    public void popUp(){
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

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(155, 45, 100, 20);
        panel.add(exitButton);
        frame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            addMouseListener(myMouseListener);
//            gameOver = false;
            newGame();
            frame.dispose();
        });

        exitButton.addActionListener(e -> {
            new Level();
            frame.dispose();
        });
    }

    private boolean isSolvable(){
        int countInversions = 0;

        for(int i = 0; i < nbTiles; i++){
            for(int j = 0; j < i; j++){
                if(tiles[j] > tiles[i]){
                    countInversions++;
                }
            }
        }

        return countInversions % 2 == 0;
    }

    boolean isSolved(){
        if(tiles[tiles.length - 1] != 0){
            return false;
        }
        for(int i = nbTiles - 1; i >= 0; i--){
            if(tiles[i] != i + 1){
                return false;
            }
        }
        return true;
    }

    private void drawGrid(Graphics2D g){
        for(int i = 0; i < tiles.length; i++){
            int r = i / size;
            int c = i % size;
            int x = margin + c * tileSize;
            int y = margin + r * tileSize;

            if(tiles[i] == 0){
                if(gameOver){
                    gameOver = false;
                    removeMouseListener(getMouseListeners()[0]);
                    popUp();
                }
                continue;
            }

            g.setColor(getForeground());
            g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.BLACK);
            g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(tiles[i]), x, y);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y){
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int desc = fm.getDescent();
        g.drawString(s, x + (tileSize - fm.stringWidth(s)) / 2,
                y + (asc + (tileSize - (asc + desc)) / 2));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2D);
    }

    public static void main(String[] args) {
        MainPanelGame mainPanelGame = new MainPanelGame(2, 550, 30);
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



}
