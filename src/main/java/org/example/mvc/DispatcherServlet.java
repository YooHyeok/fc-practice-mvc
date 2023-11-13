package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 톰캣이 http Servlet을 싱글톤 객체 하나로 만든다.
     * 이때, init 메소드가 호출된다.
     * 해당 시점에 RequestMappingHandlerMapping의 초기화 메소드를 통해 map을 초기화한다.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        log.info("DispatcherServlet#init");
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started.");

        try {
//            Controller handler = requestMappingHandlerMapping.findHandler(request.getRequestURI());// 현재 요청 uri 정보를 handler메소드에 위임
            Controller handler = requestMappingHandlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));// 현재 요청 uri 정보를 handler메소드에 위임
            String viewName = handler.handleRequest(request, response);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }

}
