package com.mortgage.loans.api.configuration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mortgage.loans.api.utils.JWTUtil;


@Component
public class AuthenticateIntercepter extends HandlerInterceptorAdapter {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		log.info("Request Completed!");
	}
 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		log.info("Method executed");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("After request executed");
		if (handler instanceof HandlerMethod) {			
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
			
			if(loginRequired == null) {
				return true;
			}
			
			String token = request.getHeader("x-token");
			String info = request.getHeader("info");
			
			JWTUtil util = new JWTUtil();
			
			if(util.validateToken(token, info)) {
				return true;
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		} else {
			return false;
		}
		
	}

}
