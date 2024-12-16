package com.withfirst.crud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.withfirst.crud.controller.BoardController;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// 지정된 컨트롤러의 동작 이전에 수행할 동작 (사전 제어)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 세션 객체 생성
		HttpSession session = request.getSession();
		
		// 세션에 로그인 정보가 없는 경우
		if(session.getAttribute("loginUser") == null) {
			// 원래 요청 URL과 파라미터를 저장 (쿼리 문자열이 null일 경우 처리)
			String queryString = request.getQueryString();
			String urlPrior = request.getRequestURL().toString();
			
			if(queryString != null) {
				urlPrior += "?" + request.getQueryString();
			}
			
			// 요청하려고 한 URL 세션에 저장
		    session.setAttribute("url_prior_login", urlPrior);
			
		    // 로그인 페이지로 리다이렉트
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false; //컨트롤러 실행 차단
		}
		
		// 세션에 로그인 정보가 있으면 컨트롤러 실행
		return true;
	}

	// 지정된 컨트롤러의 동작 이후에 처리할 동작 (사후 제어)
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// 캐시 무효화 설정
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0); 

	}

	// Dispatcher Servlet의 화면 처리가 완료된 이후 처리할 동작.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
