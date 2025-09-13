import javax.swing.*;
import java.util.Random;

public class Enemy {
    public static final int size = 30;
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int oldDx;
    private int oldDy;
    private static final Random random = new Random();
    private final JLabel label;
    public static int freeze = 0;

    public Enemy(ImageIcon icon, int[] spawnPoint) {
        dx = 0;
        dy = -3;
        this.x = spawnPoint[0] * size;
        this.y = spawnPoint[1] * size;
        this.label = new JLabel(icon);
        this.label.setBounds(x, y, size, size);
    }

    public static int getRandomDirection() {
        int[] directions = {0, 3 - freeze, -3 + freeze};
        return directions[random.nextInt(directions.length)];
    }
    public void move() {
        oldDx = dx;
        oldDy = dy;
        int newRequestedX = x + dx;
        int newRequestedY = y + dy;
        int reqLeft = newRequestedX / size;
        int reqRight = (newRequestedX + size - 1) / size;
        int reqTop = newRequestedY / size;
        int reqBottom = (newRequestedY + size - 1) / size;

        if (!Map.isWall(reqLeft, reqTop) && !Map.isWall(reqRight, reqTop) && !Map.isWall(reqLeft, reqBottom) && !Map.isWall(reqRight, reqBottom)) {
            x += dx;
            y += dy;
        } else {
            do {
                dx = getRandomDirection();
                dy = getRandomDirection();
            } while ((dx == 0 && dy == 0) || (dx != 0 && dy != 0) || (dx == -oldDx && dy == -oldDy));
        }
        label.setLocation(x, y);
    }
    public JLabel getLabel() {
        return label;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
