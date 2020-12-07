package com.example;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueneTest<E> {
    Lock lock = new ReentrantLock(true);
    Condition unfull = lock.newCondition();
    Condition unEmpty = lock.newCondition();
    int length;
    Queue<E> a = new LinkedList<>();

    public QueneTest(int capity) {
        length = capity;
    }

    void put(E e){
        lock.lock();
        try{
            while(a.size()>=length){
                unfull.await();
            }
            unEmpty.signal();
            a.add(e);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    E take(){
        lock.lock();
        try {
            while(a.size()==0){
                unEmpty.await();
            }
            unfull.signal();
            return a.poll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }finally {
            lock.unlock();

        }
        return null;
    }

    public static void main(String[] args) {
         QueneTest<String> queue = new QueneTest<>(1);
         new Thread(() -> System.out.println("消费"+queue.take())).start();
         new Thread(() -> {
             queue.put("123");
             System.out.println("生产123");
         }).start();

    }
}
