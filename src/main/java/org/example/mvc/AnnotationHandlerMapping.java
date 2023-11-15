package org.example.mvc;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    /**
     * 리플렉션을 사용하여 URL과 Method를 추출한 뒤
     * 초기화한다.
     */
    public void initialize() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
        clazzWithControllerAnnotation.forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods())
                .forEach(declaredMethod-> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                    Arrays.stream(getRequestMethod(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                            ));
                }));
    }

    /**
     * requestMapping 어노테이션으로부터 method를 반환한다.
     * @param requestMapping
     * @return
     */
    private RequestMethod[] getRequestMethod(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
