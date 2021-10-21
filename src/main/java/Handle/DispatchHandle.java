package Handle;

import Duty.Adder;
import Duty.Creator;
import Entry.InsertMessage;
import Entry.ProtocolStructure;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DispatchHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ProtocolStructure lvMqRequest = (ProtocolStructure) msg;

        switch (lvMqRequest.headers.getCode()){
            case ProtocolStructure.RequestCode.CREATE:
                ProtocolStructure messageQueue = Creator.getInstance().createMessageQueue(lvMqRequest.body.getMessage());
                ctx.writeAndFlush(messageQueue);
                break;
            case ProtocolStructure.RequestCode.GET:

                break;
            case ProtocolStructure.RequestCode.INSERT:
                Adder.getInstance().addMessage(lvMqRequest.body.getMessage(),ctx);
                break;
        }
        super.channelRead(ctx, msg);

    }
}
