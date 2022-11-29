package club.xiaozeng.common.enums;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @time: 2022/11/29 19:26
 * @author: zengh
 * @description:
 */
public enum  RpcErrorEnum {

    CLIENT_CONNECT_FAILED("客户端失败"),
    SERVER_ERROR("服务器内部错误");
    private final String message;
    RpcErrorEnum(String message) {
        this.message = message;
    }
}
