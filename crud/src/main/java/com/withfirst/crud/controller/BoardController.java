package com.withfirst.crud.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.paging.PageMaker;
import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService boardService;

	// 글 작성 페이지 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, @ModelAttribute("ctr") Criteria ctr, Model model) throws Exception {
		logger.info("Register GET...");
	}

	// 글 작성 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, Criteria ctr, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("register POST...");
		logger.info(boardVO.toString());
		
		boardService.create(boardVO);
		redirectAttributes.addFlashAttribute("result", "registerOK");

		redirectAttributes.addAttribute("pageNo", 1); // 등록 시 첫 페이지로 이동
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());
		
		return "redirect:/board/pageList";
	}

	// 게시글 전체 페이지 GET
	@RequestMapping(value = "/allList", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list");
		
		List<BoardVO> members = boardService.allList();
		model.addAttribute("memberList", members);
	}

	// 글 조회 처리 GET
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("board_no") Integer board_no, @ModelAttribute("ctr") Criteria ctr, Model model)
			throws Exception {
		logger.info("Read GET...");
		
		BoardVO boardVO = boardService.read(board_no);
		model.addAttribute(boardVO);
	}

	// 글 수정 처리 GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGET(@RequestParam("board_no") Integer board_no, @ModelAttribute("ctr") Criteria ctr,
			HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("update GET");
		BoardVO boardVO = boardService.read(board_no);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// 권한 확인: 로그인 사용자와 작성자가 다를 경우 접근 차단
		if (loginUser == null
				|| (!boardVO.getWriter().equals(loginUser.getUsername()) && !loginUser.getRole().equals("admin"))) {
			redirectAttributes.addFlashAttribute("result", "updateNO");
			return "redirect:/board/pageList";
		}

		model.addAttribute(boardVO);
		return "/board/update";
	}

	// 글 수정 처리 POST
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO boardVO, Criteria ctr, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("update POST...");
		boardService.update(boardVO);
		redirectAttributes.addFlashAttribute("result", "updateOK");

		redirectAttributes.addAttribute("pageNo", ctr.getPageNo());
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());
		redirectAttributes.addAttribute("board_no", boardVO.board_no);

		return "redirect:/board/read";
	}

	// 글 삭제 처리 GET
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String removeGET(@RequestParam Integer board_no, Criteria ctr, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("remove GET...");
		BoardVO boardVO = boardService.read(board_no);
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		// 권한 확인: 로그인 사용자와 작성자가 다를 경우 접근 차단
		if (loginUser == null
				|| (!boardVO.getWriter().equals(loginUser.getUsername()) && !loginUser.getRole().equals("admin"))) {
			redirectAttributes.addFlashAttribute("result", "removeNO");
			return "redirect:/board/pageList";
		}

		// 삭제 로직 호출
		boardService.delete(board_no);
		redirectAttributes.addFlashAttribute("result", "removeOK");

		redirectAttributes.addAttribute("pageNo", ctr.getPageNo());
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());

		return "redirect:/board/pageList";
	}

	// 페이징 처리 GET
	@RequestMapping(value = "/pageList", method = RequestMethod.GET)
	public void pageList(Criteria ctr, Model model) throws Exception {
		logger.info("pageList GET...");

		List<BoardVO> boardVO = boardService.pageList(ctr);
		model.addAttribute("list", boardVO);

		PageMaker pageMaker = new PageMaker(ctr);

		int totalCount = boardService.totalCount(ctr);

		pageMaker.setTotalPostCnt(totalCount);
		model.addAttribute("pageMaker", pageMaker);
	}

	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.info("Logout...");
		session.invalidate();

		return "redirect:/member/login";
	}
}
