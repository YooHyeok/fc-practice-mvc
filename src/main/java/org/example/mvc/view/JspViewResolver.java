package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver{
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) { // viewName이 Redirect로 시작한다면
            return new RedirectView(viewName); // redirect view
        }
        return new JspView(viewName + ".jsp"); //JSP view
    }
}
