package com.example;

public class ProductAndConcumer<E> {

    private E e;
    private int capacity;
    private final Object o = new Object();
    public ProductAndConcumer(int capacity){
        this.capacity = capacity;
    }

    public void put(E e1) throws InterruptedException {
        synchronized (o) {
            while (this.e != null) {
                o.wait();
            }
            o.notify();
            this.e = e1;

        }
    }

    public E take() throws InterruptedException {
        E res;
        synchronized (o) {
            while (this.e == null) {
                o.wait();
            }
            o.notify();
            res = e;
            e = null;
        }
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        ProductAndConcumer<Object> queue = new ProductAndConcumer<>(1);

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put("123");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
