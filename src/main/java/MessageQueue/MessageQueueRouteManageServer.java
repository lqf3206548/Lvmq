package MessageQueue;

import Entry.CreateMessage;
import Entry.InsertMessage;
import Entry.ProtocolStructure;
import Entry.ResponseMessage;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;

public class MessageQueueRouteManageServer {
    private static MessageQueueRouteManageServer instance = new MessageQueueRouteManageServer(new MessageQueueRouteManage());
    private MessageQueueRouteManage messageQueueRouteManage;
    public MessageQueueRouteManageServer(MessageQueueRouteManage messageQueueRouteManage) {
        this.messageQueueRouteManage = messageQueueRouteManage;
    }
    public static MessageQueueRouteManageServer getInstance(){
        return instance;
    }

    public ProtocolStructure addMessage(InsertMessage insertMessage,ChannelHandlerContext ctx) throws Exception {
        MessageQueueRoute messageQueueRoute = messageQueueRouteManage.get(insertMessage.getQueueTag());
        Message message = new Message(insertMessage.getQueueTag(),insertMessage.getDelayTime(),insertMessage.getMessage());
        if (messageQueueRoute.InsertMessage(message)){
            return new ProtocolStructure.Builder()
                    .setType(ProtocolStructure.Type.RESPONSE)
                    .setCode(ProtocolStructure.ResponseCode.SYN)
                    .setMessage(JSON.toJSONString(new ResponseMessage(200,"消息添加成功")))
                    .Builder();

        }else{
            return new ProtocolStructure.Builder()
                    .setType(ProtocolStructure.Type.RESPONSE)
                    .setCode(ProtocolStructure.ResponseCode.SYN)
                    .setMessage(JSON.toJSONString(new ResponseMessage(600,"不可能存在的错误，server.addMessage():O")))
                    .Builder();
        }
    }

    public ProtocolStructure createMessageQueue(CreateMessage createMessage) throws Exception {
        MessageQueueRoute messageQueueRoute = new MessageQueueRoute(
                createMessage.getQueueTag(),
                createMessage.getDispatchRule(),
                createMessage.isConsumption(),
                createMessage.getMaxClient());
        messageQueueRouteManage.put(createMessage.getQueueTag(),messageQueueRoute);

        return new ProtocolStructure.Builder()
                .setType(ProtocolStructure.Type.RESPONSE)
                .setCode(ProtocolStructure.ResponseCode.SYN)
                .setMessage(JSON.toJSONString(new ResponseMessage(200,"队列创建或添加成功")))
                .Builder();

    }
    public MessageQueueRoute getMessageQueueRoute(String key){
        return messageQueueRouteManage.get(key);
    }
}

