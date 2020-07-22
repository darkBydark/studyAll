package com.example.JUC;

import io.netty.util.concurrent.FastThreadLocal;

import java.util.Calendar;

public class multThreadLocal {
    //netty threadLocal
    public static void main(String[] args) {
//        FastThreadLocal<Integer> fastThreadLocal = new FastThreadLocal<>();
//        new Thread(()->{
//            fastThreadLocal.set(1);
//            System.out.println(Thread.currentThread().toString()+"--"+fastThreadLocal.get());
//        }).start();
        Calendar calendar= Calendar.getInstance();
        //设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
        System.out.println(calendar.getTimeInMillis());
    }
}
