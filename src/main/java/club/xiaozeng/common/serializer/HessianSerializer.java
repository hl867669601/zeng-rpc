package club.xiaozeng.common.serializer;

import club.xiaozeng.common.exception.RpcException;
import club.xiaozeng.common.exception.SerializationException;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

/**
 * @time: 2022/11/29 21:41
 * @author: zengh
 * @description:
 */
public class HessianSerializer implements Serializer{
    private static final Logger logger = LoggerFactory.getLogger(HessianSerializer.class);

    @Override
    public byte[] serialize(Object obj) {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }catch (IOException e){
            logger.error("序列化错误");
            throw new SerializationException("序列化错误",e);
        }
    }

    @Override
    public <T> T deSerialize(byte[] bytes, Class<T> clazz) {
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)){
            HessianInput hessianInput = new HessianInput(byteArrayInputStream);
            Object o = hessianInput.readObject();
            return clazz.cast(o);
        } catch (IOException e) {
            logger.error("反序列化错误");
            throw new SerializationException("反序列化错误",e);
        }
    }
}
