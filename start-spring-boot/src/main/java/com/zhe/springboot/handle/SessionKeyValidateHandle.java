package com.zhe.springboot.handle;

import com.zhe.springboot.annotation.SessionKeyValidate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zhangzhe
 */
public class SessionKeyValidateHandle extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Method method = handlerMethod.getMethod();

            SessionKeyValidate sessionKeyValidate = method.getAnnotation(SessionKeyValidate.class);

            if(null != sessionKeyValidate) {

                System.out.println("需要校验");

            }
        }

        return Boolean.TRUE;
    }
}
