package test;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientFactory {
    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        MyClassLoader mcl = new MyClassLoader();
        Class<?> clazz = Class.forName("test.person", true, mcl);
        Object obj = clazz.newInstance();

        System.out.println(obj);
        //打印出我们的自定义类加载器
        System.out.println(obj.getClass().getClassLoader());

    }
}
