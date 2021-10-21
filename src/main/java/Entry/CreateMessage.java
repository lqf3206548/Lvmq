package Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateMessage {

    /*
     * 消费队列标志
     */
    private String queueTag;

    /*
     * 消费队列分发
     * 1.只能被指定消费者消费
     * 2.能被多个消费者消费，但只能一次
     * 3.能被多个消费者消费，可以多次消费
     */
    private int DispatchRule;

    /*
     * 是否持久化
     */
    private boolean consumption;

    /*
     * 消息队列允许的最大连接数
     * */
    private int maxClient;

}
