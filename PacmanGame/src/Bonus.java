import javax.swing.*;

public abstract class Bonus implements Effect{
    private final JLabel label;
    public ImageIcon icon;
    public Bonus(ImageIcon icon, int x, int y) {
        this.icon = icon;
        this.label = new JLabel(icon);
        this.label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
    }
    public JLabel getLabel() {
        return label;
    }
    public abstract void effect();
}
