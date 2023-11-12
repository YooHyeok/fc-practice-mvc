package org.example;

import org.example.reflection.annotation.Controller;
import org.example.reflection.annotation.Service;
import org.example.reflection.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
     * 힙 영역에 로드되어 있는 Class타입의 객체를 가지고 오는 3가지 방법
     */
    @Test
    void load() throws ClassNotFoundException {
        /* 1 */
        Class<User> clazz1 = User.class; // 클래스.class
        /* 2 */
        Class<? extends User> clazz2 = new User("YooHyeokSchool", "유재혁").getClass();// <instance>.getClass() 메소드 호출
        /* 3 */
        Class<?> clazz3 = Class.forName("org.example.reflection.model.User"); // forName() 메소드의 매개변수에 클래스들이 존재하는 풀 패키지명을 지정

        logger.debug("clazz: [{}]", clazz1);
        logger.debug("clazz: [{}]", clazz2);
        logger.debug("clazz: [{}]", clazz3);
        assertThat(clazz1 == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz1 == clazz3).isTrue();
    }

    /**
     * Class타입 객체 조회 및 선언된 필드, 생성자, 메소드 조회
     */
    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList())); // 클래스에 선언된 필드들 출력
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList())); // 클래스에 선언된 생성자들 출력
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList())); // 클래스에 선언된 메소드들 출력
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
