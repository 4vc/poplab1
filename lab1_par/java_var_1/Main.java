public class Main {
    public static void main(String[] args) {
        int threadCount = 2;
        int step = 2;
        int timeout = 5000; // час очікування (у мілісекундах) на завершення роботи потоків
        Breaker breaker = new Breaker(threadCount);
        ThreadController threadController = new ThreadController(threadCount, breaker, step);
        // запускаємо керуючий потік
        new Thread(() -> {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            breaker.breakAll(); // встановлюємо флаг завершення для всіх потоків
        }).start();
    }
}
