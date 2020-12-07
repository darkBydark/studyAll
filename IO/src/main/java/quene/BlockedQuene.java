package quene;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedTransferQueue;


/**
 * 阻塞队列
 * @param <T>
 */
public class BlockedQuene<T> {
    private List<T> quene;
    private int cap;
    private volatile int used;
    private static long userdOffset;
    private static final sun.misc.Unsafe UNSAFE;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = BlockedQuene.class;
            userdOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("used"));

        } catch (Exception e) {
            throw new Error(e);
        }
    }
    public BlockedQuene(int cap) {
        quene = new ArrayList<T>();
        this.cap = cap;
        this.used = 0;
    }

    public T add(T t){
        int u = used;

        if(used!=cap){
            quene.add(t);
        }
        try{
            UNSAFE.compareAndSwapInt(this,userdOffset,used,used+1);
        }catch (Exception ignored){

        }
        return null;
    }
}
