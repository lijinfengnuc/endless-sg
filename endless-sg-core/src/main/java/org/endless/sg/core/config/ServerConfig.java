package org.endless.sg.core.config;

public class ServerConfig {

    /**
     * 应用名称
     * 1-50个字符，只能是数字、字母、—、_
     */
    private String appName;

    /**
     * 应用组名称
     * 1-50个字符，只能是数字、字母、—、_
     */
    private String appGroupName;

    /**
     * 服务地址列表
     * 格式：IP1:PORT1,IP2:PORT2
     */
    private String serverAddresses;

    /**
     * 客户端-服务端通信的协议
     * @see org.endless.sg.core.enums.ProtocolEnum
     */
    private String protocol;

    /**
     * 客户端-服务端通信的token
     */
    private String token;

}
