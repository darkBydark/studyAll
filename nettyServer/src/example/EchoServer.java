package example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class EchoServer {
    private final int port;
    public EchoServer(int port){
        this.port=port;
    }

    public static void main(String[] args) {
//        if(args.length!=1){
//            System.err.println(
//                    "Usage: "+EchoServer.class.getSimpleName()
//                    + " <port>");
//
//        }
//        int port = Integer.parseInt(args[0]);
        try {
            ReentrantLock lock = new ReentrantLock();
            lock.tryLock()
            new EchoServer(8090).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void start() throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new IdleStateHandler(5,0,0, TimeUnit.SECONDS))
                                    .addLast(new EchoServerHandler())
                            .addLast(new HealthHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
