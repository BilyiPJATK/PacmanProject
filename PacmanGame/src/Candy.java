import javax.swing.*;

public class Candy {
    private final JLabel label;
    public Candy(ImageIcon icon, int x, int y) {
        this.label = new JLabel(icon);
        this.label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
    }
    public JLabel getLabel() {
        return label;
    }
}
