package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target: 해당 어노테이션을 어디에 선언할것인가 <br/>
 * [TYPE]: 클래스,인터페이션,어노테이션,이넘 등에 선언가능 <br/>
 * @Retention: 런타임 기간까지 유지기간 설정
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
