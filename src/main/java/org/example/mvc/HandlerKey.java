package org.example.mvc;

import org.example.reflection.annotation.RequestMethod;

/**
 *
 * 요청메소드 방식과 경로에 대한 정보를 담는다.
 * HandlerMapping의 Map에서 Key로 사용된다.
 */
public class HandlerKey {
    private final RequestMethod requestMethod;
    private final String uriPath;

    public HandlerKey(RequestMethod requestMethod, String uriPath) {
        this.requestMethod = requestMethod;
        this.uriPath = uriPath;
    }
}
