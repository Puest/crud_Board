package com.withfirst.crud.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.LoginService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Inject
	LoginService loginService;

	// 로그인 페이지 GET
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(BoardVO boardVO, @ModelAttribute("ctr") Criteria ctr, Model model) throws Exception {
		logger.info("login GET...");
	}

	// 로그인 처리 POST
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO memberVO, Criteria ctr, HttpSession session, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("login POST...");

		// 이미 로그인된 경우
		if (session.getAttribute("loginUser") != null) {
			return "redirect:/board/pageList";
		}

		// 로그인 검증(DB 조회)
		MemberVO loginMember = loginService.read(memberVO.getUsername());
		if (loginMember != null && BCrypt.checkpw(memberVO.getPassword(), loginMember.getPassword())) {
			session.setAttribute("loginUser", loginMember); // 세션에 로그인 정보 저장
			logger.info("login Success: " + loginMember.getUsername());

			// 요청하려고 한 URL 확인(인터셉터에 설정한 값)
			String urlPriorLogin = (String) session.getAttribute("url_prior_login");

			if (urlPriorLogin != null) {
				session.removeAttribute("url_prior_login"); // 원래 페이지로 리다이렉트
			}
			
			redirectAttributes.addAttribute("pageNo", 1); // 등록 시 첫 페이지로 이동
			redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());
			
			return "redirect:/board/pageList"; // 기본 페이지로 이동
		} else {
			// 로그인 실패 메시지 전달
			logger.info("Login Failed...");
			redirectAttributes.addFlashAttribute("loginError", "Invalid Username or Password");
			return "redirect:/member/login";
		}
	}

	// 회원 가입 페이지 GET
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("sign up GET...");
	}

	// 회원가입 처리 POST
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String registerPOST(MemberVO memberVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("sign up POST...");

		// 회원가입 검증
		try {
			// 비밀번호 암호화
			String encryptedPassword = BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt());
			memberVO.setPassword(encryptedPassword); // 암호화된 비밀번호 설정

			// 회원가입 처리
			loginService.create(memberVO);
			logger.info("Sign Up Success: " + memberVO.getUsername());
			redirectAttributes.addFlashAttribute("registerSuccess", "Registration Successful. Please Log In.");

			return "redirect:/member/login";
		} catch (Exception e) {
			logger.error("Register Failed: ", e);
			redirectAttributes.addFlashAttribute("registerError", "Registration Failed. Try Again.");

			return "redirect:/member/join";
		}
	}

}
