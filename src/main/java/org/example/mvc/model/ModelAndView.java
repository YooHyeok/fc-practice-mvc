package org.example.mvc.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private Object view;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public Map<String,Object> getModel() {
        return Collections.unmodifiableMap(model); //불변 map 객체로 model객체를 리턴한다.
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }
}
