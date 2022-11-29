package club.xiaozeng.common.exception;

/**
 * @time: 2022/11/29 21:48
 * @author: zengh
 * @description:
 */
public class SerializationException extends RuntimeException{

    public SerializationException(String message,Throwable cause){
        super(message,cause);
    }


}
