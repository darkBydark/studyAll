package com.example.study;

public class ThreadTest {
    private int count = 0;
    private final Object lock = new Object();

    public void turning() throws InterruptedException {
        Thread even = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("偶数: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        Thread odd = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("奇数: " + count++);
                    lock.notifyAll();
                    try {
                        // 如果还没有结束，则让出当前的锁并休眠
                        if (count <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        even.start();
        // 确保偶数线程线先获取到锁
        Thread.sleep(1);
        odd.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest().turning();
    }

}
