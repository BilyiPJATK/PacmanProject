import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "src/files/highScores.dat";

    public static List<Score> getHighScores() {
        List<Score> scores = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            scores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(scores, (s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));

        return scores;
    }

    public static void saveHighScore(Score newScore) {
        List<Score> scores = getHighScores();
        scores.add(newScore);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
