import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Map extends JFrame {
    private static int[][] maze;
    private static JLabel scoreLabel;
    private JLabel timeLabel;
    private int timeCounter = 0;
    private static JLabel lifeLabel;


    public Map(int[][] maze, int x, int y, int numOfEnemy) {
        this.maze = maze;

        setTitle("Pacman Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        MapPanel mapPanel = new MapPanel(maze, numOfEnemy, x, y, this);

        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.LIGHT_GRAY);
        scoreLabel = new JLabel("Score: " + MapPanel.pointCounter );
        timeLabel = new JLabel("Time: " + timeCounter);
        lifeLabel = new JLabel("life: " + MapPanel.lifeCounter);
        scorePanel.add(scoreLabel);
        scorePanel.add(timeLabel);
        scorePanel.add(lifeLabel);

        add(mapPanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.SOUTH);

        pack(); //adjust size to maze size
        setLocationRelativeTo(null); // Centering the window

        TimeCounter timeCounterThread = new TimeCounter(this);
        timeCounterThread.start();

        mapPanel.startGame();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        Map.this,
                        "Are you sure you want to exit the game?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    mapPanel.stopGame();
                }
            }
        });
    }

    public static boolean isWall(int x, int y) {
        return maze[y][x] == 1;
    }
    public static void updateScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }
    public static void updateLifeLabel(int lifeCount) {
        lifeLabel.setText("Life: " + lifeCount);
    }
    public void incrementTimeCounter() {
        timeCounter++;
        timeLabel.setText("Time: " + timeCounter);
    }
}
