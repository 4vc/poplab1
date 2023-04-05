using System;

public class pMain
{
    public static void Main()
    {
        int threadCount = 8;
        int step = 2;
        int timeout = 5000; // час очікування (у мілісекундах) на завершення роботи потоків
        Breaker breaker = new Breaker(threadCount);
        ThreadController threadController = new ThreadController(threadCount, breaker, step);
        // запускаємо керуючий потік
        new System.Threading.Thread(() =>
        {
            try
            {
                System.Threading.Thread.Sleep(timeout);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
            }
            breaker.BreakAll(); // встановлюємо флаг завершення для всіх потоків
        }).Start();
    }

    public class ThreadController
    {
        public ThreadController(int threadCount, Breaker breaker, int step)
        {
            for (int i = 0; i < threadCount; i++)
            {
                new System.Threading.Thread(new MyThread(i, breaker, step).Run).Start();
            }
        }
    }

    public class Breaker
    {
        private readonly bool[] _canBreak;

        public Breaker(int threadCount)
        {
            _canBreak = new bool[threadCount];
        }

        public bool IsCanBreak(int id)
        {
            return _canBreak[id];
        }

        public void BreakThread(int id)
        {
            _canBreak[id] = true;
        }

        public void BreakAll()
        {
            for (int i = 0; i < _canBreak.Length; i++)
            {
                _canBreak[i] = true;
            }
        }
    }

    public class MyThread
    {
        private readonly int _id;
        private readonly Breaker _breaker;
        private readonly int _step;

        public MyThread(int id, Breaker breaker, int step)
        {
            _id = id;
            _breaker = breaker;
            _step = step;
        }

        public void Run()
        {
            long sum = 0;
            long additions = 0;
            int current = 0;
            do
            {
                sum += current;
                current += _step;
                additions++;
            } while (!_breaker.IsCanBreak(_id));
            Console.WriteLine(_id + " - " + sum + ", Loop Count: " + additions + ';');
        }
    }
}
