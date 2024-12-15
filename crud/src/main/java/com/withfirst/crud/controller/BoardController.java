package com.withfirst.crud.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService boardService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("Register GET...");
	}
		
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("register POST...");
		logger.info("board.toString()");
		
		boardService.create(boardVO);
		
		redirectAttributes.addFlashAttribute("result", "registerOK");
		
		return "redirect:/board/success";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public void success(Model model) throws Exception{
		logger.info("register Success");
	}
	
	@RequestMapping(value = "/allList", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show all list");
		List<BoardVO> members = boardService.allList();
		model.addAttribute("memberList", members);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("board_no")Integer board_no, Model model) throws Exception {
		logger.info("Read GET...");
		BoardVO boardVO = boardService.read(board_no);
		model.addAttribute(boardVO);
	}
	
//	private static final String ADMIN_ID = "admin";
//	private static final String ADMIN_PW = "as1234";
//
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String loginForm() {
//		return "login"; // login.jsp를 렌더링
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value = "username", required = false) String id,
//			@RequestParam(value = "password", required = false) String password, HttpSession session,
//			RedirectAttributes redirectAttributes) {
//		String returnURL = "";
//
//		// 기존에 로그인 세션정보가 있는 상태라면 제거 (중복 로그인 방지)
//		if (session.getAttribute("id") != null) {
//			session.removeAttribute("id");
//		}
//	
//
//        
//		if (ADMIN_ID.equals(id) && ADMIN_PW.equals(password)) {
//			session.setAttribute("id", id);
//			String priorURL = (String) session.getAttribute("url_prior_login");
//
//			if (priorURL != null) {
//				returnURL = "redirect:" + priorURL; // 이것도 한번 더 확인 관리자가 아닌 사람이 여기서 관리자 페이지로 넘어갈 수 있으니
//			} else {
//				returnURL = "redirect:/home"; // 추후 어드민 홈과 유저 홈으로 이동되도록 변경
//			}
//		} else {
//			returnURL = "redirect:/login"; // 에러 페이지를 나타내는 것보단 오류가 발생되도록
//		}
//
//		return returnURL;
//	}
}
