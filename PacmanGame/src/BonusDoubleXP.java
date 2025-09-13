import javax.swing.*;

public class BonusDoubleXP extends Bonus{
    BonusDoubleXP(ImageIcon icon, int x, int y){
        super(icon, x, y);
    }

    @Override
    public void effect() {
        MapPanel.pointCounter *= 2;
        Map.updateScoreLabel(MapPanel.pointCounter);
        System.out.println("Plus xp bonus active");
    }
}
