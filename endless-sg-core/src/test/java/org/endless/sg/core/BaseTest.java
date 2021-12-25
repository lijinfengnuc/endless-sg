package org.endless.sg.core;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Optional;

public abstract class BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    /**
     * 测试用例日志之间的分隔符
     */
    private static final String DELIMITER = "-------";

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        LOG.info("start test {}:{}{}", getClassName(testInfo), getMethodName(testInfo), DELIMITER);
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        LOG.info("end test {}:{}{}", getClassName(testInfo), getMethodName(testInfo), DELIMITER);
    }

    private String getClassName(TestInfo testInfo) {
        return Optional.ofNullable(testInfo).map(TestInfo::getTestClass)
                .flatMap(opt -> opt)
                .map(Class::getSimpleName)
                .orElse(Strings.EMPTY);
    }

    private String getMethodName(TestInfo testInfo) {
        return Optional.ofNullable(testInfo).map(TestInfo::getTestMethod)
                .flatMap(opt -> opt)
                .map(Method::getName)
                .orElse(Strings.EMPTY);
    }

}
