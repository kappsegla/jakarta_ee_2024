package org.fungover.demo.examples;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceCondition {
    private AtomicInteger counter = new AtomicInteger(0);
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        var raceCondition = new RaceCondition();

//        Thread t1 = new Thread(raceCondition::updateCount);
//        Thread t2 = new Thread(raceCondition::updateCount);
        //t1.start();
        //t2.start();
        Thread t1 = Thread.ofVirtual().start(raceCondition::updateCount);
        Thread t2 = Thread.ofVirtual().start(raceCondition::updateCount);
        t1.join();
        t2.join();
        System.out.println(raceCondition.count);
    }

    public void updateCounter() {
        for (int i = 0; i < 10000; i++) {
            counter.addAndGet(1);
        }
    }

    public void updateCount() {
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try {
                count = count + 1;
            } finally {
                lock.unlock();
            }
        }
    }
}
