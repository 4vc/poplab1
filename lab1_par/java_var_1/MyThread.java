public class MyThread implements Runnable {
    private final int id;
    private final Breaker breaker;
    private final int step;

    public MyThread(int id, Breaker breaker, int step) {
        this.id = id;
        this.breaker = breaker;
        this.step = step;
    }

    @Override
    public void run() {
        long sum = 0;
        long additions = 0;
        int current = 0;
        do {
            sum += current;
            current += step;
            additions++;
        } while (!breaker.isCanBreak(id));
        System.out.println(id + " - " + sum + ", Loop Count: " + additions + ';');
    }

}