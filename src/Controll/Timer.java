package controll;

import javax.swing.*;

public class Timer extends JLabel implements Runnable {
    private int seconds;
    private int minutes;

    public static boolean isRunning = false;
    private Thread t = new Thread(this);

    public Timer() {
        super("00:00");
    }

    public void start() {
        isRunning = true;
        t.start();
    }

    public void resume() {
        isRunning = true;
        t = new Thread(this);
        t.start();
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000);
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                String time = String.format("%02d:%02d", minutes, seconds);
                setText(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void reset() {
        stop();
        t.interrupt();
        t = new Thread(this);
        seconds = 0;
        minutes = 0;
        setText("00:00");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Timer timer = new Timer();
        frame.add(timer);
        frame.pack();
        frame.setVisible(true);
        timer.start();
    }
}
