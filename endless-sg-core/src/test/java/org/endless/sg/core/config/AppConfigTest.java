package org.endless.sg.core.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.endless.sg.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("AppConfigTest")
public class AppConfigTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfigTest.class);

    @Test
    @DisplayName("testBuilder")
    public void testBuilder() {
        //创建合法AppConfig
        try {
            AppConfig appConfig = AppConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("endless")
                    .build();
            LOG.info(JSONUtil.toJsonStr(appConfig));
            Assertions.assertEquals("endless-sg", appConfig.getAppName());
            Assertions.assertEquals("endless", appConfig.getAppGroupName());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        try {
            AppConfig appConfig = AppConfig.builder()
                    .appName("endless_sg")
                    .build();
            LOG.info(JSONUtil.toJsonStr(appConfig));
            Assertions.assertEquals("endless_sg", appConfig.getAppName());
            Assertions.assertNull(appConfig.getAppGroupName());
        } catch (Exception e) {
            Assertions.fail(e);
        }
        //创建非法AppConfig
        //appName非法
        try {
            AppConfig.builder()
                    .appName(null)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName(StrUtil.EMPTY)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("endless-sg-endless-sg-endless-sg-endless-sg-endless-sg")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("   ")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("endless.sg")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //appGroupName非法
        try {
            AppConfig.builder()
                    .appName("endless-sg")
                    .appGroupName(StrUtil.EMPTY)
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("endless-endless-endless-endless-endless-endless-endless-")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("   ")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        try {
            AppConfig.builder()
                    .appName("endless-sg")
                    .appGroupName("endless#")
                    .build();
            Assertions.fail();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

}
