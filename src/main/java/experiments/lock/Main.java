package experiments.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CustomLock lock = new CustomLock();
        ExecutorService executorService = Executors.newFixedThreadPool(10, (runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        });
        Callable<Void> callable = () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        };
        Thread.sleep(1000);
        for (int i = 0; i < 10; ++i) {
            executorService.submit(callable);
            executorService.submit(callable);
            executorService.submit(callable);
        }
        System.out.println(executorService.awaitTermination(40000, TimeUnit.MILLISECONDS));
    }
}
