package org.endless.sg.core.util;

import java.util.EnumSet;
import java.util.Objects;
import java.util.function.Function;

public class EnumUtils {

    /**
     * 根据枚举类属性值获取对应枚举类实例
     *
     * @param clazz 枚举类class
     * @param function 获取枚举类属性值的方法
     * @param t 枚举类属性值
     * @return R
     */
    public static<T, R extends Enum<R>> R getEnum(Class<R> clazz, Function<R, T> function, T t) {
        if (Objects.isNull(clazz) || Objects.isNull(function) || Objects.isNull(t)) {
            return null;
        }
        return EnumSet.allOf(clazz).stream()
                .filter(e -> t.equals(function.apply(e)))
                .findFirst()
                .orElse(null);
    }

}
