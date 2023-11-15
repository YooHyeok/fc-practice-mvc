package org.example.mvc;

import org.example.mvc.controller.*;
import org.example.mvc.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * URL에 매핑되는 클래스를 관리하기 위한 클래스 <br/>
 * Key:URL, Value:클래스
 */
public class RequestMappingHandlerMapping implements HandlerMapping{
    /**
     * String : URL PATH
     * Controller: 컨트롤러
     */

//    private Map<String, Controller> mappings = new HashMap<>();
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    /**
     * default 매핑주소 / 요청이 들어오면 HomeController 클래스를 매핑시켜준다.
     */
    void init() {
//        mappings.put("/", new HomeController());
//        mappings.put("/users", new UserListController());
//        mappings.put("/users", new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST,"/users"), new UserCreateController());
//        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"), new ForwardController("/user/form.jsp"));
        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"), new ForwardController("/user/form")); // JspViewResolver에 의해 .jsp가 자동으로 붙는다
    }

    /**
     * urlPath와 일치하는 컨트롤러 클레스를 return해주는 메소드
     * @param urlPath
     * @return
     */
    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }
    /*public Controller findHandler(String urlPath) {
        return mappings.get(urlPath);
    }*/
}
