import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
public class MapPanel extends JPanel implements KeyListener, Runnable {
    private final JFrame parentFrame;
    private boolean running;
    private final int[][] maze;
    private final int cellSize;
    private Pacman pacman;
    private final int startX;
    private final int startY;
    private List<Enemy> enemies;
    private List<Candy> candies;
    private List<Bonus> bonuses;
    private int numOfEnemy;
    public static int pointCounter;
    public static int lifeCounter;


    public MapPanel(int[][] maze, int numOfEnemy, int startX, int startY, JFrame parentFrame) {
        this.maze = maze;
        this.startX = startX;
        this.startY = startY;
        this.numOfEnemy = numOfEnemy;
        this.parentFrame = parentFrame;
        pointCounter = 0;
        lifeCounter = 2;
        cellSize = Pacman.size;

        this.running = true;

        spawnPacman();
        this.enemies = new ArrayList<>();
        spawnEnemies();
        this.candies = new ArrayList<>();
        spawnCandies();
        this.bonuses = new ArrayList<>();
        spawnBonuses();

        int width = maze[0].length * Pacman.size;
        int height = maze.length * Pacman.size;
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        drawMaze();

        addKeyListener(this);
        setFocusable(true);
        parentFrame.setResizable(false);
    }

    public void spawnPacman() {
        this.pacman = new Pacman(new ImageIcon("src/files/pacman_right.png"), startX, startY);
        add(pacman.getLabel());
        revalidate();
        repaint();
    }

    public void spawnEnemies() {
        ImageIcon enemyIcon = new ImageIcon("src/files/enemy.png");

        System.out.println("Spawning enemies...");
        for (int i = 0; i < numOfEnemy; i++) {
            Enemy enemy = new Enemy(enemyIcon, findSpawnPoint());
            enemies.add(enemy);
            add(enemy.getLabel());
        }
    }

    private void spawnCandies() {
        ImageIcon candyIcon = new ImageIcon("src/files/point.png");

        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == 0) {
                    Candy candy = new Candy(candyIcon, x * cellSize, y * cellSize);
                    candies.add(candy);
                    add(candy.getLabel());
                }
            }
        }
    }
    private void spawnBonuses() {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == 3) {
                    ImageIcon bonusIcon = new ImageIcon("src/files/boost.png");
                    Bonus bonus = new BonusSpeedBoost(bonusIcon, x * cellSize, y * cellSize);
                    bonuses.add(bonus);
                    add(bonus.getLabel());
                }
                if (maze[y][x] == 4) {
                    ImageIcon bonusIcon = new ImageIcon("src/files/BonusLife.png");
                    Bonus bonus = new BonusLife(bonusIcon, x * cellSize, y * cellSize);
                    bonuses.add(bonus);
                    add(bonus.getLabel());
                }
                if (maze[y][x] == 5) {
                    ImageIcon bonusIcon = new ImageIcon("src/files/freeze.jpg");
                    Bonus bonus = new BonusEnemyFreeze(bonusIcon, x * cellSize, y * cellSize);
                    bonuses.add(bonus);
                    add(bonus.getLabel());
                }
                if (maze[y][x] == 6) {
                    ImageIcon bonusIcon = new ImageIcon("src/files/plusXP.png");
                    Bonus bonus = new BonusPlusXP(bonusIcon, x * cellSize, y * cellSize);
                    bonuses.add(bonus);
                    add(bonus.getLabel());
                }
                if (maze[y][x] == 7) {
                    ImageIcon bonusIcon = new ImageIcon("src/files/doubleXP.png");
                    Bonus bonus = new BonusDoubleXP(bonusIcon, x * cellSize, y * cellSize);
                    bonuses.add(bonus);
                    add(bonus.getLabel());
                }
            }
        }
    }

    private void drawMaze() {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                JLabel label = new JLabel();
                if (maze[y][x] == 1) {
                    label.setBackground(Color.BLACK);
                } else {
                    label.setBackground(Color.WHITE);
                }
                label.setOpaque(true);
                label.setBounds(x * cellSize, y * cellSize, cellSize, cellSize);
                add(label);
            }
        }
    }

    @Override
    public void run() {
        while (running) {

            pacman.move();

            for (Enemy enemy : enemies) {
                enemy.move();
                if (pacman.getLabel().getBounds().intersects(enemy.getLabel().getBounds())) {
                    if (lifeCounter <= 1) {
                        System.out.println("Stop");
                        stopGame();
                    } else {
                        lifeCounter--;
                        Map.updateLifeLabel(lifeCounter);
                        System.out.println("minus life");
                        replaceMapPanel(this, maze, numOfEnemy, startX, startY, parentFrame, pointCounter, lifeCounter);
                    }
                }
            }

            for (Candy candy : candies) {
                if (candy.getLabel().isVisible() && pacman.getLabel().getBounds().intersects(candy.getLabel().getBounds())) {
                    candy.getLabel().setVisible(false);
                    pointCounter++;
                    Map.updateScoreLabel(pointCounter);
                }
            }
            for (Bonus bonus : bonuses) {
                if (bonus.getLabel().isVisible() && pacman.getLabel().getBounds().intersects(bonus.getLabel().getBounds())) {
                    bonus.getLabel().setVisible(false);
                    bonus.effect();
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void replaceMapPanel(MapPanel oldMapPanel, int[][] maze, int numOfEnemy, int startX, int startY, JFrame parentFrame, int pointCounter, int lifeCounter) {
        oldMapPanel.running = false;
        MapPanel newMapPanel = new MapPanel(maze, numOfEnemy, startX, startY, parentFrame);
        newMapPanel.pointCounter = pointCounter;
        newMapPanel.lifeCounter = lifeCounter;

        parentFrame.remove(oldMapPanel);
        parentFrame.add(newMapPanel, BorderLayout.CENTER);

        parentFrame.revalidate();
        parentFrame.repaint();

        newMapPanel.startGame();
        newMapPanel.setFocusable(true);
        newMapPanel.requestFocusInWindow();
    }

    private int[] findSpawnPoint() { //spawn point of enemy
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == 9) {
                    return new int[]{x, y};
                }
            }
        }
        return new int[]{0, 0};
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                pacman.setDirection(0, -Pacman.speed);
                break;
            case KeyEvent.VK_DOWN:
                pacman.setDirection(0, Pacman.speed);
                break;
            case KeyEvent.VK_LEFT:
                pacman.setDirection(-Pacman.speed, 0);
                break;
            case KeyEvent.VK_RIGHT:
                pacman.setDirection(Pacman.speed, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void startGame() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame() {
        System.out.println("game ended");
        running = false;
        parentFrame.dispose();

        String playerName = JOptionPane.showInputDialog(parentFrame, "Enter your name for high scores:");
        Score newScore = new Score(playerName, pointCounter);
        HighScoreManager.saveHighScore(newScore);

        pointCounter = 0;
        lifeCounter = 2;

        MenuScreen menuScreen = new MenuScreen();
        menuScreen.setVisible(true);
    }
}
