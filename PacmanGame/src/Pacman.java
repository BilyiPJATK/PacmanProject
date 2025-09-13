import javax.swing.*;

public class Pacman {
    public int x;
    public int y;
    public static final int size = 30;
    private int dx;
    private int dy;
    private int currentDx;
    private int currentDy;
    private final JLabel label;
    public static int speed;

    private final ImageIcon iconUp;
    private final ImageIcon iconDown;
    private final ImageIcon iconLeft;
    private final ImageIcon iconRight;

    public Pacman(ImageIcon icon, int x, int y) {
        this.x = x * size;
        this.y = y * size;
        speed = 2;

        this.label = new JLabel(icon);
        this.label.setBounds(this.x, this.y, size, size);

        iconUp = new ImageIcon(("src/files/pacman_up.png"));
        iconDown = new ImageIcon(("src/files/pacman_down.png"));
        iconLeft = new ImageIcon(("src/files/pacman_left.png"));
        iconRight = new ImageIcon(("src/files/pacman_right.png"));
    }

    private void updateIcon() {
        if (dx == -Pacman.speed) {
            label.setIcon(iconLeft);
        } else if (dx == Pacman.speed) {
            label.setIcon(iconRight);
        } else if (dy == -Pacman.speed) {
            label.setIcon(iconUp);
        } else if (dy == Pacman.speed) {
            label.setIcon(iconDown);
        }
    }
    public void setDirection(int dx, int dy) {
        currentDx = this.dx;
        currentDy = this.dy;
        this.dx = dx;
        this.dy = dy;
    }
    public void move() {
        int newRequestedX = x + dx;
        int newRequestedY = y + dy;
        int reqLeft = newRequestedX / size;
        int reqRight = (newRequestedX + size - 1) / size;
        int reqTop = newRequestedY / size;
        int reqBottom = (newRequestedY + size - 1) / size;

        if (!Map.isWall(reqLeft, reqTop) && !Map.isWall(reqRight, reqTop) && !Map.isWall(reqLeft, reqBottom) && !Map.isWall(reqRight, reqBottom)) {
            currentDx = dx;
            currentDy = dy;
            updateIcon();

        }

        int newX = x + currentDx;
        int newY = y + currentDy;
        int left = newX / size;
        int right = (newX + size - 1) / size;
        int top = newY / size;
        int bottom = (newY + size - 1) / size;

        if (!Map.isWall(left, top) && !Map.isWall(right, top) && !Map.isWall(left, bottom) && !Map.isWall(right, bottom)) {
            x = newX;
            y = newY;
            label.setLocation(x, y);
        } else {
            currentDx = 0;
            currentDy = 0;
        }
    }
    public JLabel getLabel() {
        return label;
    }
}
