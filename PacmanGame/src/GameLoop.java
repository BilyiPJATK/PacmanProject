/*
public class GameLoop implements Runnable {
    private final MapPanel mapPanel;
    private boolean running = true;

    public GameLoop(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    @Override
    public void run() {
        while (running) {
            mapPanel.repaint(); // Redraw the map and Pacman
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}
*/
