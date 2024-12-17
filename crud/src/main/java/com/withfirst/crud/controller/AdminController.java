package com.withfirst.crud.controller;

import java.util.List;

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
import com.withfirst.crud.service.LoginService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private LoginService loginService;
	
	@Inject
	private BoardService boardService;
	
	// 회원 관리 처리 GET
	@RequestMapping(value = "members", method = RequestMethod.GET)
	public void membersGET(Model model) throws Exception {
		logger.info("Admin-members GET...");
		List<MemberVO> members = loginService.allList();
		model.addAttribute("memberList", members);
	}

	// 회원 삭제 처리 GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String removeGET(@RequestParam("seq") Integer seq, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("memberRemove GET...");

		// 삭제 로직 호출
		loginService.delete(seq);
		redirectAttributes.addFlashAttribute("success", "removeOK");

		return "redirect:/admin/members";
	}
	
	// 회원 게시글 조회 GET
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public void postGET() throws Exception {
		logger.info("userPost GET...");
	}
}
