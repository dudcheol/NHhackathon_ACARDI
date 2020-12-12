package com.nh.saerok.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
//    	HttpSession httpSession = request.getSession();
//    	String sessionItem = (String)httpSession.getAttribute(Sessions.SESSION_ID);
//    	
    	
		return true;
    }
}
