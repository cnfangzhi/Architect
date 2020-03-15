package com.fz.architect.demo03.okhttp3;

/**
 * description:
 * author: fz on 2017/10/9 15:49
 * email: 514905121@qq.com
 * version: 1.0
 */
/**
 * Runnable implementation which always sets its thread name.
 */
public abstract class NamedRunnable implements Runnable {
    protected final String name;

    public NamedRunnable(String format, Object... args) {
        this.name = Util.format(format, args);
    }

    @Override
    public final void run() {
        String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(name);
        try {
            execute();
        } finally {
            Thread.currentThread().setName(oldName);
        }
    }

    protected abstract void execute();
}
