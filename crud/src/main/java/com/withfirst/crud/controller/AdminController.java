package com.withfirst.crud.controller;

import java.util.Arrays;
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
	public void membersGET(Criteria ctr, Model model) throws Exception {
		logger.info("Admin-members GET...");

		List<MemberVO> members = loginService.memberList(ctr);
		model.addAttribute("memberList", members);

		PageMaker pageMaker = new PageMaker(ctr);

		int totalCount = loginService.totalCount(ctr);

		pageMaker.setTotalPostCnt(totalCount);
		model.addAttribute("pageMaker", pageMaker);
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
	// 게시글 내용 자르기 메서드
	private String getShortDescription(String description, int wordLimit) {
		if (description == null || description.isEmpty())
			return "";

		if (description.length() <= wordLimit) {
			return description; // 길이가 10자 이하인 경우 그대로 반환
		}

		return description.substring(0, wordLimit) + "...";
	}

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public void postsGET(Criteria ctr, Model model) throws Exception {
		logger.info("Admin-members-posts GET...");

		List<BoardVO> posts = boardService.pageList(ctr);

		// 게시글 내용 앞 10단어까지만 가져오도록 처리
		for (BoardVO post : posts) {
			post.setDescription(getShortDescription(post.getDescription(), 10));
		}

		model.addAttribute("postList", posts);

		PageMaker pageMaker = new PageMaker(ctr);

		int totalCount = boardService.totalCount(ctr);

		pageMaker.setTotalPostCnt(totalCount);
		model.addAttribute("pageMaker", pageMaker);
	} 

	// 게시글 삭제 처리 GET
	@RequestMapping(value = "/deletePosts", method = RequestMethod.GET)
	public String removePostsGET(@RequestParam("board_no") Integer board_no, RedirectAttributes redirectAttributes)
			throws Exception {
		logger.info("removePosts GET...");

		// 삭제 로직 호출
		boardService.delete(board_no);
		redirectAttributes.addFlashAttribute("success", "removeOK");

		return "redirect:/admin/posts";
	}

}
