package ru.geekbrains.hw3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    public static void main(String[] args) {
        Player player = new Player();
        Lock lock = new ReentrantLock();
        while (player.getCounter() <= 20) {
            Thread thread1 = new Thread(() -> {
                lock.lock();
                player.printPing();
                lock.unlock();
            });
            Thread thread2 = new Thread(() -> {
                lock.lock();
                player.printPong();
                lock.unlock();
            });
            thread1.start();
            thread2.start();
        }
    }
}
