public class ThreadController {
    public ThreadController(int threadCount, Breaker breaker, int step) {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new MyThread(i, breaker, step)).start();
        }
    }
}