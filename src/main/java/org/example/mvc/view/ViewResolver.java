package org.example.mvc.view;

/**
 * 뷰를 결정해준다.
 */
public interface ViewResolver {

    /**
     * 뷰를 결정하는 추상 메소드 <br/>
     * public abstract 생략 가능.
     * @param viewName
     * @return
     */
    View resolveView(String viewName);
}
