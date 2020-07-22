package com.example.redis;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.StringUtil;
import lombok.extern.flogger.Flogger;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.*;

public class test {

     class taskTest implements Callable<String>{

        @Override
        public String call() throws Exception {
            throw new Exception("time out");
        }
    }public static String zero(String dividend){
        if(StringUtil.isNullOrEmpty(dividend))
            return null;
        if(!dividend.contains("."))
            return dividend;
        int index=dividend.length()-1;
        for(int i=dividend.length()-1;i>=0;i--)
        {
            if(dividend.charAt(i)=='0')
                index = i-1;
            else if(dividend.charAt(i)=='.'){
                index--;
                break;
            }else
                break;
        }
        return dividend.substring(0,index+1);
    }
    public int findLucky(int[] arr) {
        if(arr.length<=0)
            return -1;
        Map<Integer,Integer> cou = new HashMap<Integer,Integer>();
        for(int i=0;i<arr.length;i++){
            if(cou.containsKey(i))
                cou.put(i,cou.get(i)+1);
            else
                cou.put(i,1);
        }
        int res = -1;
        for (Map.Entry<Integer, Integer> s : cou.entrySet()) {
            if(s.getKey()==s.getValue())
                res = Math.max(s.getKey(),s.getValue());
        }
        return res;

    }


    public static void main(String[] args) {
        /*软引用对象中指向了一个长度为300000000个元素的整形数组*/
        ThreadLocal<Integer[]> threadLocal = new ThreadLocal<Integer[]>();

        Thread thread = new Thread(() -> {
            threadLocal.set(new Integer[300000000]);
            System.out.println(threadLocal.get());

            System.gc();
            System.out.println(threadLocal.get());
        });
        thread.start();

//        FastThreadLocal
    }
}
