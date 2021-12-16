package org.endless.sg.core.util;

import cn.hutool.core.util.StrUtil;
import org.endless.sg.core.BaseTest;
import org.endless.sg.core.enums.ProtocolEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EnumUtilsTest")
public class EnumUtilsTest extends BaseTest {

    @Test
    @DisplayName("testGetEnum")
    public void testGetEnum() {
        //根据code获取ProtocolEnum
        ProtocolEnum httpProtocolEnum =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, ProtocolEnum.HTTP.getCode());
        Assertions.assertEquals(ProtocolEnum.HTTP, httpProtocolEnum);
        //根据name获取ProtocolEnum
        ProtocolEnum httpsProtocolEnum =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getName, ProtocolEnum.HTTPS.getName());
        Assertions.assertEquals(ProtocolEnum.HTTPS, httpsProtocolEnum);
        //根据null获取ProtocolEnum
        ProtocolEnum nullProtocolEnum1 =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, null);
        Assertions.assertNull(nullProtocolEnum1);
        //根据空字符串获取ProtocolEnum
        ProtocolEnum nullProtocolEnum2 =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, StrUtil.EMPTY);
        Assertions.assertNull(nullProtocolEnum2);
    }

}
