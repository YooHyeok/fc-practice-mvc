package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    /**
     * Url Path를 입력받을 수 있는 속성 <br/>
     * default값 빈문자열
     * @return String : URL 경로
     */
    String value() default "";

    /**
     * GET요청 혹은 POST요청 등을 입력받는 속성 <br/>
     * default값 빈 배열 {자바에서는 배열 요소를 중괄호로 감싼다}
     * @return RequestMethod[] : Enum타입 ex) RequestMethod.GET/RequestMethod.POST 등
     */
    RequestMethod[] method() default {};
}
