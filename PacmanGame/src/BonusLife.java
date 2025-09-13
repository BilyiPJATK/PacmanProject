import javax.swing.*;

public class BonusLife extends Bonus{
    BonusLife(ImageIcon icon, int x, int y){
        super(icon, x, y);
    }

    @Override
    public void effect() {
        MapPanel.lifeCounter++;
        Map.updateLifeLabel(MapPanel.lifeCounter);
        System.out.println("life bonus active");
    }
}
