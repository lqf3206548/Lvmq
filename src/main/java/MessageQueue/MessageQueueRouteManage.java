package MessageQueue;

import java.util.concurrent.ConcurrentHashMap;
class  MessageQueueRouteManage {

    private ConcurrentHashMap<String, MessageQueueRoute> MessageQueueManageMap = new ConcurrentHashMap<String, MessageQueueRoute>();

    public MessageQueueRouteManage() {

    }

    public boolean put(String key, MessageQueueRoute value){
        MessageQueueManageMap.put(key,value);
        return true;
    }

    public MessageQueueRoute get(String key){
        return MessageQueueManageMap.get(key);
    }

}
