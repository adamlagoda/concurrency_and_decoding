package org.example;

public class Counter {
    int i = 0;
    volatile int j = 0;
    volatile boolean volatileLock = true;
    boolean nonVolatileLock = true;

    public void increment() {
        ++i;
    }

    public void incrementVolatile() {
        ++j;
    }

    public synchronized void incrementSynchronized() {
        ++i;
    }

    public void incrementWithVolatileLock() {
        while (!volatileLock) {
        }
        volatileLock = false;
        increment();
        volatileLock = true;
    }

    public void incrementWithNonVolatileLock() {
        while (!nonVolatileLock) {
        }
        nonVolatileLock = false;
        increment();
        nonVolatileLock = true;
    }
}
