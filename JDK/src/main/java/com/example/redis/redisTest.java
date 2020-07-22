package com.example.redis;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class redisTest {
    static  long[][] arr,brr;
    public static void main(String[] args)
    {//考虑一般缓存行大小是64字节, 一个 long 类型占8字节

        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[7];
            for (int j = 0; j < 7; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L,sum1= 0L;
        long marked = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i+=1) {
            for(int j =0; j< 7;j++){
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        brr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            brr[i] = new long[7];
            for (int j = 0; j < 7; j++) {
                brr[i][j] = 0L;
            }
        }
        marked = System.currentTimeMillis();
        for (int i = 0; i < 7; i+=1) {
            for(int j =0; j< 1024 * 1024;j++){
                sum = brr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }


}
