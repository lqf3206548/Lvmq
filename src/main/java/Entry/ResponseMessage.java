package Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class ResponseMessage {
    public ResponseMessage(int code, String message) {
        this.code = code;
        this.messageLenght = message.getBytes(StandardCharsets.UTF_8).length;
        this.message = message;
    }

    /*
    * 结果状态码
    * */
    private int code;

    /*
    * 结果消息长度
    * */
    private int messageLenght;

    /*
    * 结果消息
    * */
    private String message;

}
