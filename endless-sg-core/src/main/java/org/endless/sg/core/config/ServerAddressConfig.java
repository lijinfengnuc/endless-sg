package org.endless.sg.core.config;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

import java.util.Objects;
import java.util.regex.Pattern;

public final class ServerAddressConfig {

    /**
     * 服务端地址正则表达式
     * IP:PORT
     */
    private static final Pattern PATTERN_IP_PORT =
            Pattern.compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]):([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$");

    /**
     * 服务端地址正则表达式
     * 域名
     */
    private static final Pattern PATTERN_DOMAIN_NAME =
            Pattern.compile("^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$");

    /**
     * IP
     * 不可为null
     * 满足正则表达式
     */
    private String ip;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 域名
     */
    private String domainName;


    public ServerAddressConfig(String serverAddress) {
         if (PATTERN_IP_PORT.matcher(serverAddress).matches()) {
            //IP:PORT格式
            int colonIndex = serverAddress.indexOf(StrUtil.COLON);
            Assert.isTrue(colonIndex != -1, "serverAddresses中的地址{}格式不正确", serverAddress);
            this.ip = serverAddress.substring(0, colonIndex);
            try {
                this.port = Integer.parseInt(serverAddress.substring(colonIndex + 1));
            } catch (NumberFormatException e) {
                Assert.isTrue(false, "serverAddresses中的地址{}格式不正确", serverAddress);
            }
        } else if (PATTERN_DOMAIN_NAME.matcher(serverAddress).matches()) {
            //域名格式
            this.domainName = serverAddress;
        } else {
            Assert.isTrue(false, "serverAddresses中的地址{}格式不正确", serverAddress);
        }
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getDomainName() {
        return domainName;
    }

    public boolean isDomainName() {
        return null != domainName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ServerAddressConfig that = (ServerAddressConfig) object;
        if (this.isDomainName() != that.isDomainName()) {
            return false;
        }
        return this.isDomainName() ?
                Objects.equals(domainName, that.domainName) : Objects.equals(ip, that.ip) && Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return this.isDomainName() ? Objects.hash(domainName) : Objects.hash(ip, port);
    }

}
