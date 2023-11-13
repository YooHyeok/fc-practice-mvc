package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;
import org.example.mvc.controller.UserCreateController;
import org.example.mvc.controller.UserListController;

import java.util.HashMap;
import java.util.Map;

/**
 * URL에 매핑되는 클래스를 관리하기 위한 클래스 <br/>
 * Key:URL, Value:클래스
 */
public class RequestMappingHandlerMapping {
    /**
     * String : URL PATH
     * Controller: 컨트롤러
     */
    private Map<String, Controller> mappings = new HashMap<>();

    /**
     * default 매핑주소 / 요청이 들어오면 HomeController 클래스를 매핑시켜준다.
     */
    void init() {
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController());
        mappings.put("/users", new UserCreateController());
    }

    /**
     * urlPath와 일치하는 컨트롤러 클레스를 return해주는 메소드
     * @param urlPath
     * @return
     */
    public Controller findHandler(String urlPath) {
        return mappings.get(urlPath);
    }
}
