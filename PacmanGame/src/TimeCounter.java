class TimeCounter extends Thread {
    private Map mapInstance;

    public TimeCounter(Map mapInstance) {
        this.mapInstance = mapInstance;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                mapInstance.incrementTimeCounter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}