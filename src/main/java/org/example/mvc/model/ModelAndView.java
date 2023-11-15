package org.example.mvc.model;

public class ModelAndView {

    private Object view;
    public ModelAndView(String viewName) {
        this.view = viewName;
    }
}
