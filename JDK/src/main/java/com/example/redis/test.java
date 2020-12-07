package com.example.redis;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.StringUtil;
import lombok.extern.flogger.Flogger;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

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
        Config config = new Config(); config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("anyLock");
        lock.lock();

    }
}
