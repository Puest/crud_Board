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

	private static final String ADMIN_ID = "admin";
	private static final String ADMIN_PW = "as1234";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "username", required = false) String username, Model model) {
		if (error != null) {
			if ("missingPassword".equals(error)) {
				model.addAttribute("errorMessage", "비밀번호를 입력해 주세요.");
			} else if ("missingUsername".equals(error)) {
				model.addAttribute("errorMessage", "아이디를 입력해 주세요.");
			} else if ("invalidCredentials".equals(error)) {
				model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
			}
		}
		
		if (username != null) {
			model.addAttribute("username", username);
		}
		
		return "login"; // login.jsp를 렌더링
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, String password, HttpSession session) {
		String returnURL = "";

		// 기존에 로그인 세션정보가 있는 상태라면 제거 (중복 로그인 방지)
		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}

		if (ADMIN_ID.equals(id) && ADMIN_PW.equals(password)) {
			session.setAttribute("id", id);
			String priorURL = (String) session.getAttribute("url_prior_login");

			if (priorURL != null) {
				returnURL = "redirect:" + priorURL; // 이것도 한번 더 확인 관리자가 아닌 사람이 여기서 관리자 페이지로 넘어갈 수 있으니
			} else {
				returnURL = "redirect:/home"; // 추후 어드민 홈과 유저 홈으로 이동되도록 변경
			}
		} else {
			returnURL = "redirect:/login"; // 에러 페이지를 나타내는 것보단 오류가 발생되도록
		}

		return returnURL;
	}
}
