import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuScreen extends JFrame {
    MenuScreen() {
        setTitle("Pacman Game");
        setSize(650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(Box.createHorizontalGlue());

        JButton buttonStart = createButton("START");
        JButton buttonScore = createButton("SCORE");
        JButton buttonExit = createButton("EXIT");

        panel.add(buttonStart);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(buttonScore);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(buttonExit);
        panel.add(Box.createHorizontalGlue());

        add(panel);
        setVisible(true);


    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(150, 50));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        button.addActionListener(e -> {
            switch (text) {
                case "START":
                    System.out.println("Start button pressed");
                    new MapChoiceWindow(this);
                    break;
                case "SCORE":
                    System.out.println("Score button pressed");
                    displayHighScores();
                    break;
                case "EXIT":
                    System.out.println("Exit button pressed");
                    System.exit(0);
                    break;
            }
        });
        return button;
    }
    private void displayHighScores() {
        List<Score> highScores = HighScoreManager.getHighScores();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Score score : highScores) {
            if (score.getPlayerName() != null && !score.getPlayerName().isEmpty())
            listModel.addElement(score.getPlayerName() + ": " + score.getScore());
            else listModel.addElement("Anonymous : " + score.getScore());
        }

        JList<String> scoreList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(scoreList);

        JFrame highScoreFrame = new JFrame("High Scores");
        highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highScoreFrame.setSize(300, 400);
        highScoreFrame.setLocationRelativeTo(this);
        highScoreFrame.add(scrollPane);
        highScoreFrame.setVisible(true);
    }
}