package club.xiaozeng.common.utils;


import java.lang.annotation.*;

/**
 * 需要SPI扩展的类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {
}
