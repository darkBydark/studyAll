package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

//单线程Server
public class nioServer {
    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel listernChannnel = ServerSocketChannel.open();
        listernChannnel.bind(new InetSocketAddress(9999));
        listernChannnel.configureBlocking(false);

        listernChannnel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(2048);

        while (true){
            selector.select();
            Iterator<SelectionKey> keyItr = selector.selectedKeys().iterator();
            while (keyItr.hasNext()){
                SelectionKey key = keyItr.next();
                if(key.isAcceptable()) {
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);

                    System.out.println("服务端与 【"+channel.getRemoteAddress()+"】建立了链接！");
                }else if(key.isReadable()){
                    buffer.clear();

                    if(((SocketChannel)key.channel()).read(buffer) ==-1){
                        key.channel().close();
                        continue;
                    }

                    buffer.flip();
                    while(buffer.hasRemaining()){
                        byte b = buffer.get();
                        if(b==0){

                            buffer.clear();
                            buffer.put("Hello,Client!\0".getBytes());
                            buffer.flip();
                            while (buffer.hasRemaining()){
                                ((SocketChannel)key.channel()).write(buffer);
                            }
                        }else {
                            System.out.println((char) b );
                        }
                    }
                }

                keyItr.remove();
            }
        }
    }
}
