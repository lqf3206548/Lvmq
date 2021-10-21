package Entry;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;

public class ProtocolStructure {
    public Headers headers;
    public Body body;
    public ProtocolStructure(int protocolandVersionLenght,
                             String protocolandVersion,
                             int type,int code, int messageTypeLenght,
                             String messageType,int messageLenght, String message) {
        this.headers = new Headers(protocolandVersionLenght, protocolandVersion,
                type, code, messageTypeLenght, messageType);
        this.body = new Body(messageLenght, message);

    }
    public ProtocolStructure(
            String protocolandVersion,
            int type,int code,
            String messageType, String message) {
        this.headers = new Headers(protocolandVersion.getBytes(StandardCharsets.UTF_8).length
                , protocolandVersion, type, code,
                messageType.getBytes(StandardCharsets.UTF_8).length, messageType);
        this.body = new Body(message.getBytes(StandardCharsets.UTF_8).length, message);

    }
    @AllArgsConstructor
    @Data
    public class Headers{
        /*
         * 传输协议和版本信息长度
         * */
        private int protocolandVersionLenght;
        /*
         * 传输协议和版本
         * */
        private String protocolandVersion;
        /*
         * 请求方向
         *  1 - Request -请求
         *  0 - Response -响应
         * */
        private int type;
        /*
         * 请求类型
         * 请求：
         *  1 - CREATE -创建Queue
         *  0 - INSERT -消息插入Queue
         *  -1 - GET -获取消息
         *  2  -SYN - 结果确认
         * 响应：c
         *  1 - SYN - 结果确认
         *  2 - ERR - 结果错误
         * */
        private int code;
        /*
         * 消息格式信息长度
         * */
        private int messageTypeLenght;


        /*
         * 消息格式
         * */
        private String messageType;



    }
    @AllArgsConstructor
    @Data
   public class Body{
        /*
         * 消息长度
         * */
        private int messageLenght;

        /*
         * 消息
         * */
        private String message;
    }
    public static class Builder{
        private String protocolandVersion = "LVMQHP/1.0";
        private int type = -64;
        private int code = -64;
        private String messageType = "text/UTF-8";
        private String message;

        public Builder() {
        }

        public Builder setProtocolandVersion(String protocolandVersion) {
            this.protocolandVersion = protocolandVersion;
            return this;
        }
        public Builder setType(int type) {
            this.type = type;
            return this;
        }
        public Builder setCode(int code) {
            this.code = code;
            return this;
        }
        public Builder setMessageType(String messageType) {
            this.messageType = messageType;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public ProtocolStructure Builder() throws Exception {
            if (message == null){
                throw new Exception("构建ProtocolStructure对象时，缺少Message关键参数");}
            if (type == -64){
                throw new Exception("构建ProtocolStructure对象时，缺少type关键参数");}
            if (code == -64){
                throw new Exception("构建ProtocolStructure对象时，缺少code关键参数");}
            return new ProtocolStructure(
                    protocolandVersion,type,code,messageType,message);
        }

    }
    public static class RequestCode{
        public final static int CREATE = 1;
        public final static int INSERT = 0;
        public final static int GET = -1;
    }
    public static class ResponseCode{
        public final static int SYN = 1;
        public final static int ERR = 0;
    }
    public static class Type{
        public final static int REQUEST = 1;
        public final static int RESPONSE = 0;
    }

}
