package com.withfirst.crud;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login"; // login.jsp를 렌더링
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, @RequestParam String password, HttpSession session) {
		String returnURL = "";
		
		// 기존에 로그인 세션정보가 있는 상태라면 제거
		if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		
		/*
		 * if ("admin".equals(username) && "as1234".equals(password)) {
		 * session.setAttribute("user", username); return "redirect:/login/admin"; }
		 */
		return "redirect:/login?error";
	}
}
