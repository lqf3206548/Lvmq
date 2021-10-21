package Test;

import denCoder.Message_decoder;
import denCoder.Message_encoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Clinet extends Thread{
    EventLoopGroup work_group = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();
    ChannelFuture future = null;

    @Override
    public void run() {
        super.run();
        bootstrap.group(work_group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(2048,0,2));
                channel.pipeline().addLast(new LengthFieldPrepender(2));
                channel.pipeline().addLast(new Message_encoder());
                channel.pipeline().addLast(new Message_decoder());
                channel.pipeline().addLast(new Client_handle());
            }
        });
        try {
            future = bootstrap.connect(InetAddress.getLocalHost(),6000);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        future.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("连接成功");
                }else if(!future.isSuccess()){
                    System.out.println("连接失败");
                }
            }
        });
    }
}
