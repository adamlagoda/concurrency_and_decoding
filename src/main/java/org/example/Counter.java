package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    int i = 0;
    volatile int j = 0;
    volatile boolean volatileFlag = true;

    AtomicInteger k = new AtomicInteger(0);

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
        while (!volatileFlag) {
            Thread.onSpinWait();
        }
        volatileFlag = false;
        increment();
        volatileFlag = true;
    }
}
