package org.example.mvc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HomeController implements Controller{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return "home.jsp";
        return "home"; // JspViewResolver에 의해 .jsp가 자동으로 붙는다
    }
}
