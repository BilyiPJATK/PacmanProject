import javax.swing.*;

public class BonusSpeedBoost extends Bonus{
    BonusSpeedBoost(ImageIcon icon, int x, int y){
        super(icon, x, y);
    }

    @Override
    public void effect() {
        Pacman.speed = 3;
        System.out.println("speed boost active");
    }
}
