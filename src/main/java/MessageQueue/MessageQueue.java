package MessageQueue;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;

class MessageQueue {
    public MessageQueue() {
    }
    /*
     * 消费队列路由标志
     */
    private String queueTag;

    /*
    * 消息缓存队列
    * */
    private ConcurrentLinkedQueue<Message> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    /*
    * 能否被写入消息
    * */
    private volatile boolean canWirte;

    
    /**
     * @Description: 判断是否可以插入后，插入消息至队列中
     * @Param: [message]
     * @return: boolean
     * @Author: Ricardo.M.Lvqf
     * @Date: 2021/10/17
     */
    public boolean insertMessage(Message message){
        
        if (canWirte){
            return concurrentLinkedQueue.offer(message);
        }else{
            return false;
        }
    }

    public boolean SoftClose(){
        canWirte = false;
        return true;
    }

    public boolean RudeClose(){
        MessageQueueRoute messageQueueRoute = MessageQueueRouteManageServer.getInstance().getMessageQueueRoute(queueTag);
        Message message;
        while ((message = concurrentLinkedQueue.poll()) != null){

        }
    }


}
