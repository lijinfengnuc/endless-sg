package org.endless.sg.core.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.endless.sg.core.BaseTest;
import org.endless.sg.core.enums.ProtocolEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("ServerConfigTest")
public class ServerConfigTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerConfigTest.class);

    @Test
    @DisplayName("testBuilder")
    public void testBuilder() {
        //创建合法ServerConfig
        try {
            ServerConfig serverConfig = ServerConfig.builder()
                    .serverAddress("www.sg.endless.org")
                    .protocol(ProtocolEnum.HTTPS.getCode())
                    .token("endless")
                    .build();
            LOG.info(JSONUtil.toJsonStr(serverConfig));
            Assertions.assertEquals("www.sg.endless.org", serverConfig.getServerAddresses());
            Assertions.assertEquals(ProtocolEnum.HTTPS.getCode(), serverConfig.getProtocol());
            Assertions.assertEquals("endless", serverConfig.getToken());
            Assertions.assertEquals(1, serverConfig.getServerAddressConfigs().size());
            Assertions.assertEquals(1, serverConfig.getServerAddressConfigs().stream()
                    .filter(ServerAddressConfig::isDomainName)
                    .filter(serverAddressConfig -> "www.sg.endless.org".equals(serverAddressConfig.getDomainName()))
                    .filter(serverAddressConfig -> serverAddressConfig.getIp() == null)
                    .filter(serverAddressConfig -> serverAddressConfig.getPort() == null)
                    .count());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        try {
            ServerConfig serverConfig = ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .build();
            LOG.info(JSONUtil.toJsonStr(serverConfig));
            Assertions.assertEquals("127.0.0.1:80", serverConfig.getServerAddresses());
            Assertions.assertEquals(ProtocolEnum.HTTP.getCode(), serverConfig.getProtocol());
            Assertions.assertEquals(1, serverConfig.getServerAddressConfigs().size());
            Assertions.assertEquals(1, serverConfig.getServerAddressConfigs().stream()
                    .filter(serverAddressConfig -> !serverAddressConfig.isDomainName())
                    .filter(serverAddressConfig -> serverAddressConfig.getDomainName() == null)
                    .filter(serverAddressConfig -> "127.0.0.1".equals(serverAddressConfig.getIp()))
                    .filter(serverAddressConfig -> 80 == serverAddressConfig.getPort())
                    .count());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        //创建非法ServerConfig
        //serverAddress非法
        try {
            ServerConfig.builder()
                    .serverAddress(null)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress(StrUtil.EMPTY)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80,127.0.0.1:80,127.0.0.1:80,127.0.0.1:80,127.0.0.1:80," +
                            "127.0.0.1:80,127.0.0.1:80,127.0.0.1:80,127.0.0.1:80,127.0.0.1:80,127.0.0.1:80")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress("www.sg.endless.org,www.sg.endless.org")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //protocol非法
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .protocol("error")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //token非法
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .token(StrUtil.EMPTY)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .token("err")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .token("error".repeat(501))
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            ServerConfig.builder()
                    .serverAddress("127.0.0.1:80")
                    .token("error.")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

}
