package org.example;

import org.example.annotation.Controller;
import org.example.annotation.RequestMapping;
import org.example.annotation.RequestMethod;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 리플랙션 : 힙 영역에 로드돼 있는 클래스타입의 객체를 통해 필드/ 메소드/ 생성자를 접근제어자와 상관없이 사용할 수 있도록 지원하는 API <br/>
 * Reflection을 통해 @Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);


    /**
     * Reflectsions 인스턴스의 prefix로 지정한 org.example패키지 하위에 해당하는 클래스들에 대해서
     */
    @Test
    void controllerScan() {
        Reflections reflections = new Reflections("org.example"); //패키지 하위의 모든 클래스 대상으로 리플렉션을 사용한다.
        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class)); // Controller 클래스 어노테이션이 선언된 클래스들을 찾은뒤 HashSet에 모두 저장
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));
        logger.debug("beans: [{}]", beans);
    }
}
