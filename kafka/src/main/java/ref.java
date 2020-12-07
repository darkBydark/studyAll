import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ref {
    private static Logger logger = LoggerFactory.getLogger(ref.class);
    public static void main(String[] args) throws UnsupportedEncodingException {
        Double a=null;
        Double b = null;
        ThreadPoolExecutor px = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        AtomicInteger i= new AtomicInteger();
        for(;;){

            px.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(i.getAndIncrement());
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
