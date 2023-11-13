package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", List.of()); //users라는 이름으로 List데이터 요청 객체에 담아 view에 전달
        return "/user/list.jsp"; // webapps/user/ 디렉토리 하위경로
    }
}
