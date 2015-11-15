package mr.robotto.engine.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 12/11/2015.
 */
public class MrThreadPool {
    private ThreadPoolExecutor mPoolExecutor;
    private int mNumTasks;
    private CountDownLatch mCountDownLatch;

    public MrThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        mPoolExecutor = new LatchedThreadPool(this, cores + 1, cores + 1, 2, TimeUnit.SECONDS, queue);
        mNumTasks = 0;
    }

    private void incrementNumTasks() {
        mNumTasks++;
    }

    private void decrementNumTaks() {
        mNumTasks--;
    }

    public void execute(Runnable command) {
        mPoolExecutor.execute(command);
    }

    public void awaitAll() throws InterruptedException {
        synchronized (this) {
            mCountDownLatch = new CountDownLatch(mNumTasks);
        }
        mCountDownLatch.await();
        mCountDownLatch = null;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return mPoolExecutor.awaitTermination(timeout, unit);
    }

    public boolean remove(Runnable task) {
        return mPoolExecutor.remove(task);
    }

    public Future<?> submit(Runnable task) {
        return mPoolExecutor.submit(task);
    }

    public void shutdown() {
        mPoolExecutor.shutdown();
    }

    private class LatchedThreadPool extends ThreadPoolExecutor {
        private final MrThreadPool mPool;

        public LatchedThreadPool(MrThreadPool pool, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            mPool = pool;
        }

        @Override
        public void execute(Runnable command) {
            synchronized (mPool) {
                mPool.incrementNumTasks();
            }
            super.execute(command);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            synchronized (mPool) {
                mPool.decrementNumTaks();
            }
            //TODO: Check if syncronization is necesary
            if (mCountDownLatch != null) {
                mCountDownLatch.countDown();
            }
        }
    }
}
