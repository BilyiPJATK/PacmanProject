import javax.swing.*;

public class BonusPlusXP extends Bonus{
    BonusPlusXP(ImageIcon icon, int x, int y){
        super(icon, x, y);
    }

    @Override
    public void effect() {
        MapPanel.pointCounter += 40;
        Map.updateScoreLabel(MapPanel.pointCounter);
        System.out.println("Plus xp bonus active");
    }
}
