package org.example.mvc.controller;



import org.example.mvc.RequestMethod;
import org.example.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//public class HomeController implements Controller{
@org.example.mvc.annotation.Controller
public class HomeController {

//    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return "home.jsp";
        return "home"; // JspViewResolver에 의해 .jsp가 자동으로 붙는다
    }
}
