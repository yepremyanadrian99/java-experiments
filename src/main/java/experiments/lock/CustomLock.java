package experiments.lock;

import lombok.Getter;

public class CustomLock {

    @Getter
    private volatile boolean isLocked;

    private volatile String threadName;

    public synchronized void lock() throws InterruptedException {
        if (Thread.currentThread().getName().equals(threadName)) {
            return;
        }
        while (isLocked) {
            wait();
        }
        isLocked = true;
        threadName = Thread.currentThread().getName();
    }

    public synchronized void unlock() {
        if (!isLocked || !Thread.currentThread().getName().equals(threadName)) {
            return;
        }
        isLocked = false;
        threadName = null;
        notify();
    }
}
