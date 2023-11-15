package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    /**
     * 전달 받은 handler 객체의 타입이 Controller 인터페이스 타입인지의 체크한다. <br/>
     * 타입이 일치 여부에 따라 지원여부를 boolean값으로 반환한다.
     * @param handler
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    /**
     * handler는 Object타입이므로 Controller 인터페이스로 업캐스팅한 뒤 handleRequest메소드를 호출한다. <br/>
     * Controller 인터페이스를 구현한 구현체 ____Controller의 viewName을 리턴받을 수 있다. <br/>
     * 최종적으로는 viewName을 ModelAndView 객체로 감싸서 리턴한다. <br/>
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller) handler).handleRequest(request, response);
        return new ModelAndView(viewName);
    }
}
