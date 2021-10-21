package Duty;

import Entry.CreateMessage;
import Entry.ProtocolStructure;
import Entry.ResponseMessage;
import MessageQueue.MessageQueueRouteManageServer;
import com.alibaba.fastjson.JSON;

public class Creator {
    private MessageQueueRouteManageServer messageQueueRouteManageServer = MessageQueueRouteManageServer.getInstance();
    private static final Creator instance = new Creator();
    public static Creator getInstance(){
        return instance;
    }

    public ProtocolStructure createMessageQueue(String createMessageS) throws Exception {
        try {
            CreateMessage createMessageO = JSON.parseObject(createMessageS, CreateMessage.class);
            return messageQueueRouteManageServer.createMessageQueue(createMessageO);

        }catch (Exception e){
            return new ProtocolStructure.Builder()
                    .setType(ProtocolStructure.Type.RESPONSE)
                    .setCode(ProtocolStructure.ResponseCode.ERR)
                    .setMessage(JSON.toJSONString(new ResponseMessage(500,"客户端错误，消息解析错误")))
                    .Builder();
        }

    }
}
