package club.xiaozeng.common.serializer;

import club.xiaozeng.common.utils.SPI;

/**
 * @time: 2022/11/29 21:18
 * @author: zengh
 * @description:
 */
@SPI
public interface Serializer {

    /**
     * 将对象序列化
     * @param obj 被序列化的对象
     * @return byte数组
     */
    byte[] serialize(Object obj);


    /**
     * 反序列化
     * @param bytes 字节数组
     * @param clazz 对象类型
     * @param <T> 返回的对象类型
     * @return 对象
     */
    <T> T deSerialize(byte[] bytes,Class<T> clazz);

}
