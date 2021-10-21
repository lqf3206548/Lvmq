package Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertMessage {

    /*
     * 协议ID
     */
    private int agreementId;
    
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
     * 消息主体长度
     */
    private int messageLenght;

    /*
     * 消息主体
     */
    private String message;

    @Override
    public String toString() {
        return "MessageEntry{" +
                "queueTag=" + queueTag +
                ", agreementId=" + agreementId +
                ", delayTime=" + delayTime +
                ", messageLenght=" + messageLenght +
                ", message='" + message + '\'' +
                '}';
    }
}
