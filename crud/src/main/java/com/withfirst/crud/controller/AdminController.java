package com.withfirst.crud.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.paging.PageMaker;
import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.service.LoginService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.FileVO;
import com.withfirst.crud.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private LoginService loginService;

	@Inject
	private BoardService boardService;
	
//	회원 관리 페이지
	// 회원 관리 처리 GET
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public void membersGET(Model model) throws Exception {
		logger.info("Admin-members GET...");

		List<MemberVO> members = loginService.allList();
		model.addAttribute("memberList", members);
	}

	// 회원 삭제 처리 GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String removeGET(@RequestParam("user_id") Integer user_id, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("memberRemove GET...");

		// 삭제 로직 호출
		loginService.delete(user_id);
		redirectAttributes.addFlashAttribute("success", "removeOK");

		return "redirect:/admin/members";
	}

	
// 회원 게시글 관리 페이지
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
    public void managePosts(Criteria ctr, Model model) throws Exception {
		logger.info("Admin-members-posts GET...");
		
		List<BoardVO> posts = boardService.pageList(ctr);
		model.addAttribute("postList", posts);
		
		PageMaker pageMaker = new PageMaker(ctr);

		int totalCount = boardService.totalCount(ctr);

		pageMaker.setTotalPostCnt(totalCount);
		model.addAttribute("pageMaker", pageMaker);
    }
	
	
}
