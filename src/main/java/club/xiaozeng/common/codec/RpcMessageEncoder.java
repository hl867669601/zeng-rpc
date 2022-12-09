package club.xiaozeng.common.codec;

import club.xiaozeng.common.compress.Compress;
import club.xiaozeng.common.constant.RpcConstant;
import club.xiaozeng.common.dto.RpcMessage;
import club.xiaozeng.common.enums.SerializationEnum;
import club.xiaozeng.common.serializer.Serializer;
import club.xiaozeng.common.utils.SPIUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @time: 2022/12/6 21:00
 * @author: zengh
 * @description:
 * 整个RPC:
 * 头总共16字节（魔数：4字节、版本：1字节、消息长度：4字节、消息类型：1字节
 * 压缩类型：1字节、序列化类型：1字节、requestId：4字节）
 * body：n字节
 */
public class RpcMessageDecoder extends MessageToByteEncoder<RpcMessage> {
    public static final AtomicInteger ATOMIC_INTEGER =new AtomicInteger(0);

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf byteBuf) throws Exception {
        // 写入魔数
        byteBuf.writeBytes(RpcConstant.MAGIC_NUM);

        // 写入版本
        byteBuf.writeByte(RpcConstant.VERSION);

        // 把写指针向后移4字节，预留出长度的位置
        byteBuf.writerIndex(byteBuf.writerIndex()+4);

        // 写入消息类型
        byteBuf.writeByte(rpcMessage.getMessageType());

        // 写入压缩类型
        byteBuf.writeByte(rpcMessage.getCompress());

        // 写入序列化类型
        byteBuf.writeByte(rpcMessage.getCodec());

        // 写入requestId
        byteBuf.writeInt(ATOMIC_INTEGER.getAndIncrement()) ;
        // body的字节数组
        byte[] bodyBytes;
        // 总长度初始化位头长度
        int fullLength = RpcConstant.HEAD_LENGTH;
        // 这里本来是想获取序列化方法名，然后通过反射构造出来，
        // 后面想了直接用SPI就不用指定了，那个序列化的codec就没有用了
        // 如果后面要同时使用多种不同的序列话方法的化，可以通过SPI维护一个序列化的数组
        // 然后通过枚举获得方法，然后再反射出相应的方法
        // String codecName = SerializationEnum.getName(rpcMessage.getCodec());
        Serializer serializer = SPIUtil.getExtensionObject(Serializer.class);
        bodyBytes = serializer.serialize(rpcMessage.getData());

        //这里还要加个压缩
        Compress compress = SPIUtil.getExtensionObject(Compress.class);
        bodyBytes = compress.compress(bodyBytes);

        fullLength += bodyBytes.length;
        byteBuf.writeBytes(bodyBytes);
        // 记录下最后的位置
        int writeIndex = byteBuf.writerIndex();

        // 这里writeIndex-fullLength就等于把写指针移动到开头
        // 再加魔数的长度和版本的长度1就是消息长度的位置了
        byteBuf.writerIndex(writeIndex-fullLength+RpcConstant.MAGIC_NUM.length+1);

        byteBuf.writeInt(fullLength);
        // 把指针移动回后面
        byteBuf.writerIndex(writeIndex);

    }
}
