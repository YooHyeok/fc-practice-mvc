package org.example.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View{

    private final String name;

    public JspView(String name) {
        this.name = name;
    }

    /**
     * forward를 구현한 render() 메소드
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /**
         * 메소드 참조 : 람다 표현식이 단 하나의 메소드만을 호출할 경우 사용 <br/>
         * 조건 : 매개변수의 갯수, 타입이 일치하면 아래와 같이 생략이 가능해진다. (이름만 참조하는 형태)
         */
        model.forEach(request::setAttribute); // model.forEach((name1, o) -> request.setAttribute(name1, o));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(name);
        requestDispatcher.forward(request, response);
    }
}
