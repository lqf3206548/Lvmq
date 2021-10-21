package MessageQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

class MessageQueueRoute {
    public MessageQueueRoute(String queueTag, int dispatchRule, boolean consumption,int maxClient) {
        this.queueTag = queueTag;
        this.dispatchRule = dispatchRule;
        this.consumption = consumption;
        this.maxClient = maxClient;
    }

    /*
    * 缓存在没有消费者注册的情况下发过来的消息
    * */
    private ConcurrentLinkedQueue<Message> messageCache = new ConcurrentLinkedQueue<>();

    /*
    *消费者注册后生成的消息队列存
    * */
    private List<MessageQueue> messageQueues = new CopyOnWriteArrayList<MessageQueue>();

    /*
     * 消费队列标志
     */
    private String queueTag;

    /*
     * 消费队列分发
     * 1.可以多次消费
     * 0.只能一次消费
     */
    private int dispatchRule;

    /*
     * 是否持久化
     */
    private boolean consumption;

    /*
    * 轮询算法
    * */
    private volatile int index = 0;

    /*
    * 消息队列允许的最大连接数
    * */
    private int maxClient;

/**
 * @Description: 插入消息方法
 * @Param: [message]
 * @return: boolean
 * @Author: Ricardo.M.Lvqf
 * @Date: 2021/10/18
 */
    public boolean InsertMessage(Message message){
        if (messageQueues.size() == 0){
            messageCache.offer(message);
            return true;
        }
        switch (dispatchRule){
            case 1:
                messageQueues.stream().forEach((i)->{i.insertMessage(message);});
                break;
            case 0:
                if (index == Integer.MAX_VALUE){index = 0;}
                MessageQueue messageQueue = messageQueues.get( index++ % messageQueues.size());
                messageQueue.insertMessage(message);
                break;
            default:
                return false;
        }
        return true;

    }



}
