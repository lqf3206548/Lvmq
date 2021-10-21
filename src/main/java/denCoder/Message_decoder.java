package denCoder;

import Entry.ProtocolStructure;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Message_decoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.readerIndex(2);
        int protocolandVersionLenght = byteBuf.readInt();
        byte[] protocolandVersionbyte = new byte[protocolandVersionLenght];
        byteBuf.readBytes(protocolandVersionbyte);
        String protocolandVersion = new String(protocolandVersionbyte,"UTF-8");

        int type = byteBuf.readInt();

        int code = byteBuf.readInt();

        int MessageTypeLenght = byteBuf.readInt();
        byte[] MessageTypebyte = new byte[MessageTypeLenght];
        byteBuf.readBytes(MessageTypebyte);
        String MessageType = new String(MessageTypebyte,"UTF-8");

        int MessageLenght = byteBuf.readInt();
        byte[] Messagebyte = new byte[MessageLenght];
        byteBuf.readBytes(Messagebyte);
        String Message = new String(Messagebyte,"UTF-8");

        ProtocolStructure lvMqRequest = new ProtocolStructure(
                protocolandVersionLenght,
                protocolandVersion,
                type,
                code,
                MessageTypeLenght,
                MessageType,
                MessageLenght,
                Message
        );
        list.add(lvMqRequest);
    }
}
