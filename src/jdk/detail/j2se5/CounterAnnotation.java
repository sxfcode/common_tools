package jdk.detail.j2se5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface CounterAnnotation.
 * 这是一个计数器注解,每当方法被执行时，方法执行次数加1.
 * @date 2013-8-2 17:12:29
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CounterAnnotation {
}
