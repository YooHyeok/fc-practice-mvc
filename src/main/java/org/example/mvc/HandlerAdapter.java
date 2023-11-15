package org.example.mvc;

import org.example.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {

    /**
     * 전달 받은 handler 객체를 지원하는지 여부 반환
     * @param handler
     * @return
     */
    boolean supports(Object handler);

    /**
     * handle 수행 메소드 <br/>
     * 결과값으로 ModelAndView를 반환받는다.
     * @param request
     * @param response
     * @throws Exception
     */
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
