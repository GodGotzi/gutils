package at.gotzi.api;

import java.io.IOException;

public abstract class GotziRunnable {

    private Thread thread;
    private boolean stop;

    public abstract void run() throws Exception;

    public void runTaskAsync() {
        this.thread = new Thread(() -> {
            if (stop)
                return;
            try {
                this.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.thread.start();
    }

    public void runTaskLater(int milliseconds) {
        this.thread = new Thread(() -> {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stop)
                return;
            try {
                this.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.thread.start();
    }

    public void cancel() {
        this.stop = true;
    }

    public Thread getThread() {
        return this.thread;
    }

    public void runRepeatingTaskAsync(int milliseconds) {
        this.thread = new Thread(() -> {
            while(true) {
                if (stop)
                    return;
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    this.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.thread.start();
    }
}
