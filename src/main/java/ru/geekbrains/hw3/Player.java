package ru.geekbrains.hw3;

import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private volatile boolean state = true;
    private AtomicInteger counter;

    public Player() {
        this.counter = new AtomicInteger();
        counter.set(0);
    }

    public void printPing() {
        if (state) {
            counter.incrementAndGet();
            System.out.println("ping");
            state = false;
        }
    }

    public void printPong() {
        if (!state) {
            counter.incrementAndGet();
            System.out.println("   pong");
            state = true;
        }
    }

    public int getCounter() {
        return counter.get();
    }
}
