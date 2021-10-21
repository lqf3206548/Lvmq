package CacheQueue;

import Entry.InsertMessage;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CacheQueue {
    private final static ConcurrentLinkedQueue<InsertMessage> chachequeue = new ConcurrentLinkedQueue<InsertMessage>();

    public static void offer(InsertMessage insertMessage){
        chachequeue.offer(insertMessage);
        System.out.println("PendingQueue.offer:(O)V--"+insertMessage);
    }

    public static InsertMessage poll(){
        return chachequeue.poll();
    }


}
