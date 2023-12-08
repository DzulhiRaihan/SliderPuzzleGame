import javax.swing.*;

public class Level {
    public void level1(){
        MainPanelGame mainPanelGame = new MainPanelGame(2, 550, 30);
        PuzzleBlock.mainPanelGame = mainPanelGame;
        PuzzleBlock.mainPanelGame.newGame();
    }

    public void level2(){
        MainPanelGame mainPanelGame = new MainPanelGame(3, 550, 30);
        PuzzleBlock.mainPanelGame = mainPanelGame;
        PuzzleBlock.mainPanelGame.newGame();
    }

    public void level3(){
        MainPanelGame mainPanelGame = new MainPanelGame(4, 550, 30);
        PuzzleBlock.mainPanelGame = mainPanelGame;
        PuzzleBlock.mainPanelGame.newGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleBlock();
        });
    }
}