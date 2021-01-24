package com.fz.architect.demo03.retrofit;

import java.io.IOException;

/**
 * description:
 * author: fz on 2017/10/13 11:06
 * email: 514905121@qq.com
 * version: 1.0
 */
public interface Call<T> extends Cloneable {
    /**
     * Synchronously send the request and return its response.
     *
     * @throws IOException if a problem occurred talking to the server.
     * @throws RuntimeException (and subclasses) if an unexpected error occurs creating the request
     * or decoding the response.
     */
    Response<T> execute() throws IOException;

    /**
     * Asynchronously send the request and notify {@code callback} of its response or if an error
     * occurred talking to the server, creating the request, or processing the response.
     */
    void enqueue(Callback<T> callback);

    /**
     * Returns true if this call has been either {@linkplain #execute() executed} or {@linkplain
     * #enqueue(Callback) enqueued}. It is an error to execute or enqueue a call more than once.
     */
    boolean isExecuted();

    /**
     * Cancel this call. An attempt will be made to cancel in-flight calls, and if the call has not
     * yet been executed it never will be.
     */
    void cancel();

    /** True if {@link #cancel()} was called. */
    boolean isCanceled();

    /**
     * Create a new, identical call to this one which can be enqueued or executed even if this call
     * has already been.
     */
    Call<T> clone();
}
