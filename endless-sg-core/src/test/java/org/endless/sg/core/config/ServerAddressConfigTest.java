package org.endless.sg.core.config;

import org.endless.sg.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("ServerAddressConfigTest")
public class ServerAddressConfigTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ServerAddressConfigTest.class);


    @Test
    @DisplayName("testServerAddressConfig")
    public void testServerAddressConfig() {
        //使用域名合法创建ServerAddressConfig
        try {
            ServerAddressConfig serverAddressConfig = new ServerAddressConfig("www.sg.endless.org");
            Assertions.assertTrue(serverAddressConfig.isDomainName());
            Assertions.assertEquals("www.sg.endless.org", serverAddressConfig.getDomainName());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        //使用域名非法创建ServerAddressConfig
        try {
            new ServerAddressConfig("error");
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //使用IP:PORT合法创建ServerAddressConfig
        try {
            ServerAddressConfig serverAddressConfig = new ServerAddressConfig("127.0.0.1:80");
            Assertions.assertFalse(serverAddressConfig.isDomainName());
            Assertions.assertEquals("127.0.0.1", serverAddressConfig.getIp());
            Assertions.assertEquals(80, serverAddressConfig.getPort());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        //使用IP:PORT非法创建ServerAddressConfig
        try {
            new ServerAddressConfig("127.0.0.1:65536");
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            new ServerAddressConfig("127.0.0.256:80");
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            new ServerAddressConfig("error");
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

}
