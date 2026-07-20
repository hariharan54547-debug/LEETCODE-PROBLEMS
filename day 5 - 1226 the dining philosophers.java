import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    // One lock per fork
    private final ReentrantLock[] forks = new ReentrantLock[5];
    // Only allow 4 philosophers to attempt picking up forks at once,
    // which guarantees at least one can always get both forks (breaks deadlock)
    private final Semaphore permits = new Semaphore(4);

    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                            Runnable pickLeftFork,
                            Runnable pickRightFork,
                            Runnable eat,
                            Runnable putLeftFork,
                            Runnable putRightFork) throws InterruptedException {

        int leftFork = philosopher;
        int rightFork = (philosopher + 4) % 5;

        permits.acquire();
        try {
            forks[leftFork].lock();
            forks[rightFork].lock();
            try {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            } finally {
                forks[leftFork].unlock();
                forks[rightFork].unlock();
            }
        } finally {
            permits.release();
        }
    }
}
