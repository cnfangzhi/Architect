package com.fz.architect.demo03.okhttp3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description: 网络请求类
 * author: fz on 2017/10/9 14:45
 * email: 514905121@qq.com
 * version: 1.0
 */
public class Dispatcher {
    private int maxRequests = 64;
    private Runnable idleCallback;

    /**
     * Executes calls. Created lazily.
     */
    private ExecutorService executorService;

    /**
     * Ready async calls in the order they'll be run.
     */
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<>();

    /**
     * Running asynchronous calls. Includes canceled calls that haven't finished yet.
     */
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<>();

    /**
     * Running synchronous calls. Includes canceled calls that haven't finished yet.
     */
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque<>();

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Dispatcher() {
    }

    /**
     * Set a callback to be invoked each time the dispatcher becomes idle (when the number of running
     * calls returns to zero).
     *
     * <p>Note: The time at which a {@linkplain Call call} is considered idle is different depending
     * on whether it was run {@linkplain Call#enqueue(Callback) asynchronously} or
     * {@linkplain Call#execute() synchronously}. Asynchronous calls become idle after the
     * {@link Callback#onResponse onResponse} or {@link Callback#onFailure onFailure} callback has
     * returned. Synchronous calls become idle once {@link Call#execute() execute()} returns. This
     * means that if you are doing synchronous calls the network layer will not truly be idle until
     * every returned {@link Response} has been closed.
     */
    public synchronized void setIdleCallback(Runnable idleCallback) {
        this.idleCallback = idleCallback;
    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }

    /**
     * Used by {@code Call#execute} to signal it is in-flight.
     */
    synchronized void executed(RealCall call) {
        runningSyncCalls.add(call);
    }

    /**
     * Used by {@code Call#execute} to signal completion.
     */
    void finished(RealCall call) {
        finished(runningSyncCalls, call, false);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        int runningCallsCount;
        Runnable idleCallback;
        synchronized (this) {
            if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
            if (promoteCalls) promoteCalls();
            runningCallsCount = runningCallsCount();
            idleCallback = this.idleCallback;
        }

        if (runningCallsCount == 0 && idleCallback != null) {
            idleCallback.run();
        }
    }

    private void promoteCalls() {
        if (runningAsyncCalls.size() >= maxRequests) return; // Already running max capacity.
        if (readyAsyncCalls.isEmpty()) return; // No ready calls to promote.

        for (Iterator<RealCall.AsyncCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            RealCall.AsyncCall call = i.next();

            i.remove();
            runningAsyncCalls.add(call);
            executorService().execute(call);

            if (runningAsyncCalls.size() >= maxRequests) return; // Reached max capacity.
        }
    }

    public void finished(RealCall.AsyncCall call) {
        finished(runningAsyncCalls, call, true);
    }

    public synchronized int runningCallsCount() {
        return runningAsyncCalls.size() + runningSyncCalls.size();
    }

    public void enqueue(RealCall.AsyncCall call) {
        if (runningAsyncCalls.size() < maxRequests) {
            runningAsyncCalls.add(call);
            executorService().execute(call);
        } else {
            readyAsyncCalls.add(call);
        }
    }

    /**
     * Cancel all calls currently enqueued or executing. Includes calls executed both {@linkplain
     * Call#execute() synchronously} and {@linkplain Call#enqueue asynchronously}.
     */
    public synchronized void cancelAll() {
        for (RealCall.AsyncCall call : readyAsyncCalls) {
            call.get().cancel();
        }

        for (RealCall.AsyncCall call : runningAsyncCalls) {
            call.get().cancel();
        }

        for (RealCall call : runningSyncCalls) {
            call.cancel();
        }
    }
}
