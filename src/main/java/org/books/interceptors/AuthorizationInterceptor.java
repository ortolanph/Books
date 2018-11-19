package org.books.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = Logger.getLogger(AuthorizationInterceptor.class.getName());

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        LOGGER.info("Gotcha!");

        return true;
    }
}
