package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.model.ModelAndView;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;

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
        /**
         * [SingletonList]
         * immutable 즉, 불변성 상수 느낌으로 사용한다.
         * Arrays.asList는 리스트의 변경이 가능하므로 메모리를 더 사용하게 된다.
         * 리스트의 요소가 단1개인 불변의 리스트를 생성할 경우에 사용한다.
         */
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter());
        viewResolvers = Collections.singletonList(new JspViewResolver()); //viewResolver 클래스 싱글톤 리스트로 초기화
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started.");

        try {
//            Controller handler = requestMappingHandlerMapping.findHandler(request.getRequestURI());// 현재 요청 uri 정보를 handler메소드에 위임
            Controller handler = requestMappingHandlerMapping.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI()));// 현재 요청 uri 정보를 handler메소드에 위임
//            String viewName = handler.handleRequest(request, response);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
//            requestDispatcher.forward(request, response);


            HandlerAdapter resturnHandlerAdapter = handlerAdapters.stream()
                    .filter(handlerAdapter -> handlerAdapter.supports(handler))
                    .findAny()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));
            ModelAndView modelAndView = resturnHandlerAdapter.handle(request, response, handler);

            /**
             * ViewResolver의 resolveView메소드를 통해 뷰 네임을 전달하면 적절한 뷰가 선택될 것이고 (Redirect/Forward)
             * forward 혹은 redirect를 통해 적절한 view를 출력해준다.
             */
            for (ViewResolver viewResolver : viewResolvers) {
//                View view = viewResolver.resolveView(viewName);
//                view.render(new HashMap<>(), request, response); // view에 넘길 값을 담을 map객체를 함께 넘긴다.
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response); // view에 넘길 값을 담을 map객체를 함께 넘긴다.
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }

}
