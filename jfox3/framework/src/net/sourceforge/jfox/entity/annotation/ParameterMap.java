package net.sourceforge.jfox.entity.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:yang_y@sysnet.com.cn">Young Yang</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface ParameterMap {
    String name();
    String value();
}
