package controll;

import javax.swing.*;

public class ScoreLabel extends JLabel implements Runnable{
    public static int score = 1000; 

    private Thread t  = new Thread(this);

    public static int calculateScore (int seconds) {
        score = score - seconds;  

        return score;
    }

    public ScoreLabel() {
        super("Score : 1000");
    }

    public void start() {
        score = 1000;
        setText("Score : 1000");
        t.start();
    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
                score = calculateScore(Timer.getSeconds());
                setText("Score : " + score);
                if (score <= 0) {
                    reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void stop(){
        t.interrupt();
    }

    public void reset(){
        t.interrupt();
        t= new Thread(this);
    }

    public void resume(){
        t = new Thread(this);
        t.start();
    }

    public int getScore() {
        return score;
    }

}