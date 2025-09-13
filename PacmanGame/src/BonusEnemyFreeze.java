import javax.swing.*;

public class BonusEnemyFreeze extends Bonus{
    BonusEnemyFreeze(ImageIcon icon, int x, int y){
        super(icon, x, y);
    }

    @Override
    public void effect() {
        Enemy.freeze = 1;
        System.out.println("enemy freeze bonus active");
    }
}
