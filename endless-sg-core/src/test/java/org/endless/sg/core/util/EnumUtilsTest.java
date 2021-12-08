package org.endless.sg.core.util;

import org.endless.sg.core.BaseTest;
import org.endless.sg.core.enums.ProtocolEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EnumUtils")
public class EnumUtilsTest extends BaseTest {

    @Test
    @DisplayName("getEnum")
    public void testGetEnum() {
        ProtocolEnum httpProtocolEnum =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, ProtocolEnum.HTTP.getCode());
        Assertions.assertEquals(ProtocolEnum.HTTP, httpProtocolEnum);
        ProtocolEnum httpsProtocolEnum =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getDesc, ProtocolEnum.HTTPS.getDesc());
        Assertions.assertEquals(ProtocolEnum.HTTPS, httpsProtocolEnum);
        ProtocolEnum nullProtocolEnum1 =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, null);
        Assertions.assertNull(nullProtocolEnum1);
        ProtocolEnum nullProtocolEnum2 =
                EnumUtils.getEnum(ProtocolEnum.class, ProtocolEnum::getCode, "");
        Assertions.assertNull(nullProtocolEnum2);
    }

}
