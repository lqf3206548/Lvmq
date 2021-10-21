package CacheQueue;

import Entry.InsertMessage;
import lombok.SneakyThrows;

public class CacheQueueDispatch implements Runnable {
    /*
    *休眠时间
     */
    private int sleepTime;
    /*
    * 时间增长量
    * */
    private int increase;
    /*
    * 最大休眠时间
    * */
    private int maxSleepTime;

    public CacheQueueDispatch(int sleepTime, int increase, int maxSleepTime) {
        this.sleepTime = sleepTime;
        this.increase = increase;
        this.maxSleepTime = maxSleepTime - increase;

    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            InsertMessage poll = CacheQueue.poll();
            if (poll == null) {
                Thread.sleep(sleepTime);
                sleepTime = sleepTime + increase * ((maxSleepTime / sleepTime) / (maxSleepTime / sleepTime));
                continue;
            }


        }
    }
}
