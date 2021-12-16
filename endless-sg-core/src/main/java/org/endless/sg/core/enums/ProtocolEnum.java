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
     * 协议名称
     */
    private final String name;


    ProtocolEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
