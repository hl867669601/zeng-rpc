package club.xiaozeng.common.enums;

/**
 * @time: 2022/11/29 20:07
 * @author: zengh
 * @description:
 */
public enum  SerializationEnum {
    KYRO((byte)0x01,"kyro"),
    PROTOSTUFF((byte)0x02,"protostuff"),
    HESSIAN((byte)0x03,"hessian");

    private byte code;
    private String name;

    SerializationEnum(byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
