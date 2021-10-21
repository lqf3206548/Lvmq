package Test;

import Entry.InsertMessage;
import Entry.ProtocolStructure;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class Client_handle extends SimpleChannelInboundHandler<ProtocolStructure> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        InsertMessage sss = new InsertMessage(1, 2, 3,  3, "sss");
        String A = JSON.toJSONString(sss);
        ProtocolStructure lvMqRequest = new ProtocolStructure(
                "eee".getBytes("UTF-8").length
                ,"eee",1,0,
                "32".getBytes("UTF-8").length,"32",
                A.getBytes("UTF-8").length
                ,A);
        ctx.writeAndFlush(lvMqRequest);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ProtocolStructure lvMqRequest) throws Exception {
        System.out.println("Client_handle.channelRead0:V(O,O)-"+lvMqRequest);
    }

}
