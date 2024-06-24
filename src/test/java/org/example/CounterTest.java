package org.example;

import junit.framework.TestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CounterTest extends TestCase {

    private static final long TIMEOUT = 3L;

    private ExecutorService executorService;
    private Counter counter;

    @Override
    protected void setUp() {
        counter = new Counter();
    }

    public void testIncrementNonSynchronizedNonVolatileSingleThread() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(1);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> counter.increment()));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertEquals(expected, counter.i);
    }

    public void testIncrementNonSynchronizedNonVolatileConcurrently() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(2);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> counter.increment()));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertFalse(expected == counter.i);
    }

    public void testIncrementNonSynchronizedVolatileConcurrently() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(2);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> counter.incrementVolatile()));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertFalse(expected == counter.j);
    }

    public void testIncrementSynchronizedNonVolatileConcurrently() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(2);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> counter.incrementSynchronized()));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertEquals(expected, counter.i);
    }

    public void testIncrementUsingNonVolatileLock() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(2);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> {
                            counter.incrementWithNonVolatileLock();
                        }));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertFalse(expected == counter.i);
    }

    public void testIncrementUsingVolatileLock() throws InterruptedException {
        // when
        executorService = Executors.newFixedThreadPool(2);
        final var numOfExecutions = 10000;
        final var expected = 10000;

        // when
        IntStream.range(0, numOfExecutions)
                        .forEach(i -> executorService.execute(() -> {
                            counter.incrementWithVolatileLock();
                        }));
        executorService.awaitTermination(TIMEOUT, TimeUnit.SECONDS);

        // then
        assertEquals(expected, counter.i);
    }


}