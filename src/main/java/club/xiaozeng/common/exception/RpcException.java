package club.xiaozeng.common.exception;

import club.xiaozeng.common.enums.RpcErrorEnum;
import lombok.Data;

/**
 * @time: 2022/11/29 19:25
 * @author: zengh
 * @description:
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorEnum rpcErrorEnum,String detail){
        super(rpcErrorEnum.getMessage()+":"+detail);
    }

    public RpcException(String message,Throwable cause){
        super(message,cause);
    }

    public RpcException(RpcErrorEnum rpcErrorEnum){
        super(rpcErrorEnum.getMessage());
    }

}
