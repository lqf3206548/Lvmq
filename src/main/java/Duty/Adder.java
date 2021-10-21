package Duty;

import Entry.InsertMessage;
import Entry.ProtocolStructure;
import MessageQueue.MessageQueueRouteManageServer;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;

public class Adder {
    private MessageQueueRouteManageServer messageQueueRouteManageServer = MessageQueueRouteManageServer.getInstance();
    private static final Adder instance = new Adder();
    public static Adder getInstance(){
        return instance;
    }


   public ProtocolStructure addMessage(String message, ChannelHandlerContext ctx) throws Exception {
        InsertMessage insertMessage = JSON.parseObject(message, InsertMessage.class);
        return messageQueueRouteManageServer.addMessage(insertMessage,ctx);
    }

}
