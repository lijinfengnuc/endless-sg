package org.endless.sg.core.enums;

public enum ProtocolEnum {

    /**
     * http
     */
    HTTP("http", "http"),
    /**
     * https
     */
    HTTPS("https", "https")
    ;


    /**
     * 协议编码
     */
    private final String code;
    /**
     * 协议描述
     */
    private final String desc;


    ProtocolEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
