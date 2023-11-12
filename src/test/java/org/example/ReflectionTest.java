package org.example;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 리플랙션 : 힙 영역에 로드돼 있는 클래스타입의 객체를 통해 필드/ 메소드/ 생성자를 접근제어자와 상관없이 사용할 수 있도록 지원하는 API <br/>
 * Reflection을 통해 @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Reflections reflections = new Reflections("org.example"); //패키지 하위의 모든 클래스 대상으로 리플렉션을 사용한다.
        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class)); // Controller 클래스 어노테이션이 선언된 클래스들을 찾은뒤 HashSet에 모두 저장
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        logger.debug("beans: [{}]", beans);
    }


    @Test
    void controllerScanRefector() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
        logger.debug("beans: [{}]", beans);
    }

    /**
     *
     * @param annotations
     * Class<? extends Annotaion > : Annotation 타입의 클래스를 담는 리스트를 뜻한다. <br/>
     * 각 항목은 어노테이션을 나타내는 클래스의 Class객체이다.
     * ? extends Annotation은 해당 클래스가 어노테이션 타입이거나 어노테이션 타입의 서브타입임을 의미한다.
     * @return
     */
    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("org.example"); //패키지 하위의 모든 클래스 대상으로 리플렉션을 사용한다.
        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
