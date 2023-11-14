package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectView implements View {

    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:"; // 상수로 추출 : Ctrl + Alt + C
    private final String name;

    public RedirectView(String name) {
        this.name = name;
    }

    /**
     * redirect를 구현한 render() 메소드
     * @param model
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(name.substring(DEFAULT_REDIRECT_PREFIX.length())); //"redirect:" 길이 9 - 즉 9번 인덱스를 포함하여 잘라낸 값으로 redirect한다.
    }
}
