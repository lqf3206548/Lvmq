import Handle.DispatchHandle;
import denCoder.Message_decoder;
import denCoder.Message_encoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class Server extends Thread{
    //ossGroup 和 workerGroup 是两个线程池, 它们默认线程数为 CPU 核心数乘以 2，bossGroup 用于接收客户端传过来的请求，接收到请求后将后续操作交由 workerGroup 处理。
    EventLoopGroup boss_group = new NioEventLoopGroup(1);
    EventLoopGroup work_group = new NioEventLoopGroup(100);
    ServerBootstrap bootstrap = new ServerBootstrap();

    @Override
    public void run() {
        super.run();
        bootstrap.group(boss_group,work_group).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,100)
                .childHandler(new ChannelInitializer<Channel>() {
                    //当客户端建立连接时，会调用initChannel方法，
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(2048,0,2));
                        channel.pipeline().addLast(new LengthFieldPrepender(2));
                        channel.pipeline().addLast(new Message_encoder());
                        channel.pipeline().addLast(new Message_decoder());
                        channel.pipeline().addLast(new DispatchHandle());



                    }
                });
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.bind(6000).sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            channelFuture.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        boss_group.shutdownGracefully();
        work_group.shutdownGracefully();
    }
}
