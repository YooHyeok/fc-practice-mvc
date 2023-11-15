package org.example.mvc;

import org.example.mvc.controller.Controller;

public interface HandlerMapping {
    /**
     * Controller interface형태가 아닌 Annotation형태로 받기 위해 Object형태로 반환
     * @param handlerKey
     * @return
     */
    Object findHandler(HandlerKey handlerKey);
}
