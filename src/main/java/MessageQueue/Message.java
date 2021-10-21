package MessageQueue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    /*
     * 消费队列标志
     */
    private String queueTag;

    /*
     * 延迟时间
     * 单位毫秒
     */
    private int delayTime;

    /*
     * 消息主体
     */
    private String message;
}
