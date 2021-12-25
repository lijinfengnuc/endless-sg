package org.endless.sg.core.config;

import cn.hutool.core.lang.Assert;

import java.util.regex.Pattern;

public class AppConfig {

    /**
     * appName/appGroupName最小长度
     */
    private static final int LENGTH_MIN_NAME = 3;

    /**
     * appName/appGroupName最大长度
     */
    private static final int LENGTH_MAX_NAME  = 50;

    /**
     * appName/appGroupName正则表达式
     */
    private static final Pattern PATTERN_NAME = Pattern.compile("^[A-Za-z0-9_-]{1,50}$");

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

    private AppConfig() {

    }

    public String getAppName() {
        return appName;
    }

    public String getAppGroupName() {
        return appGroupName;
    }

    public static AppConfigBuilder builder() {
        return new AppConfigBuilder();
    }

    public static class AppConfigBuilder {
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

        public AppConfigBuilder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public AppConfigBuilder appGroupName(String appGroupName) {
            this.appGroupName = appGroupName;
            return this;
        }

        public AppConfig build() {
            //校验参数
            validateAppName(this.appName);
            validateAppGroupName(this.appGroupName);
            //新建SgConfig
            AppConfig appConfig = new AppConfig();
            appConfig.appName = this.appName;
            appConfig.appGroupName = this.appGroupName;
            return appConfig;
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
    }

}
