package org.endless.sg.core.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.endless.sg.core.enums.ProtocolEnum;
import org.endless.sg.core.util.EnumUtils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class SgConfig {

    /**
     * appName/appGroupName最小长度
     */
    private static final int LENGTH_MIN_NAME = 1;

    /**
     * appName/appGroupName最大长度
     */
    private static final int LENGTH_MAX_NAME  = 50;

    /**
     * appName/appGroupName正则表达式
     */
    private static final Pattern PATTERN_NAME = Pattern.compile("^[A-Za-z0-9_-]{1,50}$");

    /**
     * 服务端地址IP:PORT格式最大个数
     */
    private static final int SIZE_MAX_SERVER_ADDRESS = 10;

    /**
     * ProtocolEnum的名称集合
     */
    private static final Set<String> PROTOCOL_NAME_SET = EnumSet.allOf(ProtocolEnum.class)
            .stream()
            .map(ProtocolEnum::getName)
            .filter(StrUtil::isNotBlank)
            .collect(Collectors.toSet());

    /**
     * ProtocolEnum的名称列表，以逗号分隔
     */
    private static final String PROTOCOL_NAMES = CollectionUtil.join(PROTOCOL_NAME_SET, StrUtil.COMMA);

    /**
     * token最小长度
     */
    private static final int LENGTH_MIN_TOKEN  = 5;

    /**
     * token最小长度
     */
    private static final int LENGTH_MAX_TOKEN  = 1000;

    /**
     * token正则表达式
     */
    private static final Pattern PATTERN_TOKEN = Pattern.compile("^[A-Za-z0-9]{5,1000}$");

    /**
     * 应用名称
     * 不可为null
     * 1-50个字符，只能是数字、字母、—、_
     */
    private String appName;

    /**
     * 应用组名称
     * 可为null
     * 1-50个字符，只能是数字、字母、—、_
     */
    private String appGroupName;

    /**
     * 服务端地址集合
     * 不可为null
     * 格式1：IP1:PORT1,IP2:PORT2，最多支持10个地址
     * 格式2：域名
     */
    private String serverAddresses;

    /**
     * 客户端-服务端通信的协议
     * 可为null，默认http
     * @see org.endless.sg.core.enums.ProtocolEnum
     */
    private String protocol;

    /**
     * 客户端-服务端通信的token
     * 可为null
     * 5-1000个字符，只能是数字、字母
     */
    private String token;

    /**
     * 服务端地址集合
     */
    private Set<ServerAddressConfig> serverAddressConfigs;


    private SgConfig() {

    }

    public String getAppName() {
        return appName;
    }

    public String getAppGroupName() {
        return appGroupName;
    }

    public String getServerAddresses() {
        return serverAddresses;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getToken() {
        return token;
    }

    public Set<ServerAddressConfig> getServerAddressConfigs() {
        return serverAddressConfigs;
    }

    public static SgConfigBuilder builder() {
        return new SgConfigBuilder();
    }


    public static class SgConfigBuilder {

        /**
         * 应用名称
         * 不可为null
         * 1-50个字符，只能是数字、字母、—、_
         */
        private String appName;

        /**
         * 应用组名称
         * 可为null
         * 1-50个字符，只能是数字、字母、—、_
         */
        private String appGroupName;

        /**
         * 服务端地址集合
         * 不可为null
         * 格式1：IP1:PORT1,IP2:PORT2，最多支持10个地址
         * 格式2：域名
         */
        private String serverAddresses;

        /**
         * 客户端-服务端通信的协议
         * 可为null，默认http
         * @see org.endless.sg.core.enums.ProtocolEnum
         */
        private String protocol;

        /**
         * 客户端-服务端通信的token
         * 可为null
         * 5-1000个字符，只能是数字、字母
         */
        private String token;

        /**
         * 服务端地址集合
         */
        private Set<ServerAddressConfig> serverAddressConfigs;


        public SgConfigBuilder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public SgConfigBuilder appGroupName(String appGroupName) {
            this.appGroupName = appGroupName;
            return this;
        }

        public SgConfigBuilder serverAddress(String serverAddresses) {
            this.serverAddresses  = serverAddresses;
            return this;
        }

        public SgConfigBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public SgConfigBuilder token(String token) {
            this.token = token;
            return this;
        }

        public SgConfig build() {
            //校验参数
            validateAppName(this.appName);
            validateAppGroupName(this.appGroupName);
            validateServerAddresses(this.serverAddresses);
            validateProtocol(this.protocol);
            validateToken(this.token);
            //新建SgConfig
            SgConfig sgConfig = new SgConfig();
            sgConfig.appName = this.appName;
            sgConfig.appGroupName = this.appGroupName;
            sgConfig.serverAddresses = this.serverAddresses;
            sgConfig.protocol = this.protocol;
            sgConfig.token = this.token;
            sgConfig.serverAddressConfigs = this.serverAddressConfigs;
            return sgConfig;
        }

        private void validateAppName(String appName) {
            //校验非空
            Assert.notNull(appName, "appName不能为空");
            //校验长度
            Assert.isFalse(appName.length() < LENGTH_MIN_NAME, "appName不能小于{}个字符", LENGTH_MIN_NAME);
            Assert.isFalse(appName.length() > LENGTH_MAX_NAME, "appName不能大于{}个字符", LENGTH_MAX_NAME);
            //校验格式
            Assert.isTrue(PATTERN_NAME.matcher(appName).matches(), "appName只能包含数字、字母、中划线、下划线");
        }

        private void validateAppGroupName(String appGroupName) {
            if (appGroupName == null) {
                return;
            }
            //校验长度
            Assert.isFalse(appName.length() < LENGTH_MIN_NAME, "appGroupName不能小于{}个字符", LENGTH_MIN_NAME);
            Assert.isFalse(appName.length() > LENGTH_MAX_NAME, "appGroupName不能大于{}个字符", LENGTH_MAX_NAME);
            //校验格式
            Assert.isTrue(PATTERN_NAME.matcher(appGroupName).matches(), "appGroupName只能包含数字、字母、中划线、下划线");
        }

        private void validateServerAddresses(String serverAddresses) {
            //校验非空
            Assert.notNull(serverAddresses, "serverAddress不能为空");
            //分隔serverAddress
            List<String> serverAddressList = StrUtil.split(serverAddresses, StrUtil.COMMA);
            //校验地址个数
            Assert.isFalse(serverAddressList.size() > SIZE_MAX_SERVER_ADDRESS, "serverAddress的IP不能超过{}个", SIZE_MAX_SERVER_ADDRESS);
            //校验格式并构建ServerAddressConfig
            if (serverAddressList.size() == 1) {
                //serverAddresses只有一个地址，可以是域名也可以是IP:PORT
                this.serverAddressConfigs = CollectionUtil.newHashSet(new ServerAddressConfig(serverAddressList.get(0)));
            } else {
                //serverAddresses有多个地址，只能是IP:PORT
                this.serverAddressConfigs = serverAddressList.stream()
                        .map(ServerAddressConfig::new)
                        .collect(Collectors.toSet());
                Assert.isTrue(this.serverAddressConfigs.stream().noneMatch(ServerAddressConfig::isDomainName),
                        "serverAddresses有多个地址时不能使用域名");
            }
        }

        private void validateProtocol(String protocol) {
            if (protocol == null) {
                //protocol为null时设置默认值为http
                this.protocol = ProtocolEnum.HTTP.getCode();
            } else {
                //校验合法性
                ProtocolEnum protocolEnum = EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, protocol);
                Assert.notNull(protocolEnum, "protocol不合法，目前仅支持{}", PROTOCOL_NAMES);
            }
        }

        private void validateToken(String token) {
            if (token == null) {
                return;
            }
            //校验长度
            Assert.isFalse(token.length() < LENGTH_MIN_TOKEN, "token不能小于{}个字符", LENGTH_MIN_TOKEN);
            Assert.isFalse(token.length() > LENGTH_MAX_TOKEN, "token不能大于{}个字符", LENGTH_MAX_TOKEN);
            //校验格式
            Assert.isTrue(PATTERN_TOKEN.matcher(token).matches(), "token只能包含数字、字母");
        }

    }

}
