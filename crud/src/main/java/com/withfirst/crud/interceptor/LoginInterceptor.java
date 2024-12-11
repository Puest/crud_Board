package com.withfirst.crud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	// 지정된 컨트롤러의 동작 이전에 수행할 동작 (사전 제어)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("user");
		
		System.out.println("[preHandle]" + user);
		
		if(user == null) {
			String urlPrior = request.getRequestURL().toString() + "?" + request.getQueryString();
		    request.getSession().setAttribute("url_prior_login", urlPrior);
			
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		// 컨트롤러 실행
		return true;
	}

	// 지정된 컨트롤러의 동작 이후에 처리할 동작 (사후 제어)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	// Dispatcher Servlet의 화면 처리가 완료된 이후 처리할 동작.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
