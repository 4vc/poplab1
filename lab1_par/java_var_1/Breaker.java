public class Breaker {
    private final boolean[] canBreak;
    public Breaker(int threadCount) {
        this.canBreak = new boolean[threadCount];
    }

    synchronized public boolean isCanBreak(int id) {
        return canBreak[id];
    }

    synchronized public void breakThread(int id) {
        canBreak[id] = true;
    }

    synchronized public void breakAll() {
        for (int i = 0; i < canBreak.length; i++) {
            canBreak[i] = true;
        }
    }
}