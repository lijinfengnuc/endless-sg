package org.endless.sg.core.config;

import cn.hutool.json.JSONUtil;
import org.endless.sg.core.BaseTest;
import org.endless.sg.core.enums.ProtocolEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("SgConfigTest")
public class SgConfigTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(SgConfigTest.class);


    @Test
    @DisplayName("testBuilder")
    public void testBuilder() {
        //合法创建SgConfig
        try {
            SgConfig sgConfig = SgConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("endless")
                    .serverAddress("www.sg.endless.org")
                    .protocol(ProtocolEnum.HTTPS.getCode())
                    .token("endless")
                    .build();
            LOG.info(JSONUtil.toJsonStr(sgConfig));
            Assertions.assertEquals("endless-sg", sgConfig.getAppName());
            Assertions.assertEquals("endless", sgConfig.getAppGroupName());
            Assertions.assertEquals("www.sg.endless.org", sgConfig.getServerAddresses());
            Assertions.assertEquals(ProtocolEnum.HTTPS.getCode(), sgConfig.getProtocol());
            Assertions.assertEquals("endless", sgConfig.getToken());
            Assertions.assertEquals(1, sgConfig.getServerAddressConfigs().size());
            Assertions.assertEquals(1, sgConfig.getServerAddressConfigs().stream()
                    .filter(ServerAddressConfig::isDomainName)
                    .filter(serverAddressConfig -> "www.sg.endless.org".equals(serverAddressConfig.getDomainName()))
                    .filter(serverAddressConfig -> serverAddressConfig.getIp() == null)
                    .filter(serverAddressConfig -> serverAddressConfig.getPort() == null)
                    .count());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        try {
            SgConfig sgConfig = SgConfig.builder()
                    .appName("endless-sg")
                    .serverAddress("127.0.0.1:80")
                    .build();
            LOG.info(JSONUtil.toJsonStr(sgConfig));
            Assertions.assertEquals("endless-sg", sgConfig.getAppName());
            Assertions.assertEquals("127.0.0.1:80", sgConfig.getServerAddresses());
            Assertions.assertEquals(ProtocolEnum.HTTP.getCode(), sgConfig.getProtocol());
            Assertions.assertEquals(1, sgConfig.getServerAddressConfigs().size());
            Assertions.assertEquals(1, sgConfig.getServerAddressConfigs().stream()
                    .filter(serverAddressConfig -> !serverAddressConfig.isDomainName())
                    .filter(serverAddressConfig -> serverAddressConfig.getDomainName() == null)
                    .filter(serverAddressConfig -> "127.0.0.1".equals(serverAddressConfig.getIp()))
                    .filter(serverAddressConfig -> 80 == serverAddressConfig.getPort())
                    .count());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        //非法创建SgConfig
        try {
            SgConfig.builder()
                    .appName("endless.sg")
                    .serverAddress("127.0.0.1:80")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            SgConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("endless.")
                    .serverAddress("127.0.0.1:80")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            SgConfig.builder()
                    .appName("endless-sg")
                    .serverAddress("127.0.0.1:65536")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            SgConfig.builder()
                    .appName("endless-sg")
                    .serverAddress("127.0.0.1:80")
                    .protocol("error")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            SgConfig.builder()
                    .appName("endless-sg")
                    .serverAddress("127.0.0.1:80")
                    .token("end")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

}
