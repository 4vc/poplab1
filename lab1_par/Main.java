import java.util.Scanner;

public class Main {
    static volatile boolean canStop = false;

    static class BreakThread extends Thread {
        private int timeBreak;

        public BreakThread(int timeBreak) {
            this.timeBreak = timeBreak;
        }

        public void run() {
            try {
                Thread.sleep(timeBreak * 1000L);
            } catch (InterruptedException e) {
                
            }
            canStop = true;
        }
    }

    static class MainThread extends Thread {
        private int id;
        private long step;

        public MainThread(int id, long step) {
            this.id = id;
            this.step = step;
        }

        public void run() {
            long sum = 0;
            long cntSteps = 0;
            while (!canStop) {
                sum += step;
                cntSteps++;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                
            }
            System.out.println("id: " + id + " sum: " + sum + " Steps Num: " + cntSteps + " Step: " + step);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of threads: ");
        int threadsCnt = scanner.nextInt();
        System.out.print("Enter number of step: ");
        long step = scanner.nextLong();
        System.out.print("Enter number of time break: ");
        int timeBreak = scanner.nextInt();

        BreakThread breakThread = new BreakThread(timeBreak);
        breakThread.start();

        MainThread[] threads = new MainThread[threadsCnt];
        for (int i = 0; i < threadsCnt; i++) {
            threads[i] = new MainThread(i, step);
            threads[i].start();
        }
    }
}
