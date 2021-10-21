package denCoder;

import Entry.ProtocolStructure;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Message_encoder extends MessageToByteEncoder<ProtocolStructure> {

//    @Override
//    protected void encode(ChannelHandlerContext channelHandlerContext, MessageEntry messageEntry, ByteBuf byteBuf) throws Exception {
//
//        byteBuf.writeInt(messageEntry.getAgreementId());
//        byteBuf.writeInt(messageEntry.getQueueTag());
//        byteBuf.writeInt(messageEntry.getDelayTime());
//        byteBuf.writeBoolean(messageEntry.isPersistenceType());
//        byteBuf.writeInt(messageEntry.getConsumption());
//        byteBuf.writeInt(messageEntry.getMessageLenght());
//        byte[] byteMessage = messageEntry.getMessage().getBytes("UTF-8");
//        byteBuf.writeBytes(byteMessage,0,byteMessage.length);
//        System.out.println("Message_encoder.encode:V(O,O)-"+Thread.currentThread());
//
//    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ProtocolStructure lvmqrresponse, ByteBuf byteBuf) throws Exception {

        byteBuf.writeInt(lvmqrresponse.headers.getProtocolandVersionLenght());
        byteBuf.writeBytes(lvmqrresponse.headers.getProtocolandVersion().getBytes("UTF-8"));

        byteBuf.writeInt(lvmqrresponse.headers.getType());

        byteBuf.writeInt(lvmqrresponse.headers.getCode());

        byteBuf.writeInt(lvmqrresponse.headers.getMessageTypeLenght());
        byteBuf.writeBytes(lvmqrresponse.headers.getMessageType().getBytes("UTF-8"));

        byteBuf.writeInt(lvmqrresponse.body.getMessageLenght());
        byteBuf.writeBytes(lvmqrresponse.body.getMessage().getBytes("UTF-8"));

    }
}
