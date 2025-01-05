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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.withfirst.crud.file.FileUtils;
import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.paging.PageMaker;
import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.service.FileService;
import com.withfirst.crud.vo.BoardVO;
import com.withfirst.crud.vo.FileVO;
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

	@Inject
	private FileService fileService;

	// 글 작성 페이지 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO boardVO, @ModelAttribute("ctr") Criteria ctr, Model model) throws Exception {
		logger.info("Register GET...");
	}

	// 글 작성 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO boardVO, Criteria ctr, FileUtils fileUtils,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			RedirectAttributes redirectAttributes) throws Exception {

		logger.info("register POST...");

		// 게시글 등록 로직 호출
		boardService.create(boardVO);

		logger.info("Generated Board_no: " + boardVO.getBoard_no());

		// FileUtils를 통해 파일 처리 및 정보를 가져옴
		List<FileVO> fileDetails = fileUtils.parseFileInfo(boardVO.board_no, boardVO.writer, files);

		// FileService를 통해 DB에 저장
		for (FileVO file : fileDetails) {
			fileService.insertFile(file);
		}

		// 게시글 등록 메시지 처리
		redirectAttributes.addFlashAttribute("result", "registerOK");

		redirectAttributes.addAttribute("pageNo", 1); // 등록 시 첫 페이지로 이동
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());

		return "redirect:/board/pageList";
	}

	// 글 조회 처리 GET
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("board_no") Integer board_no, @ModelAttribute("ctr") Criteria ctr, Model model)
			throws Exception {
		logger.info("Read GET...");

		// 게시글 정보 가져오기
		BoardVO boardVO = boardService.read(board_no);
		model.addAttribute(boardVO);

		// 파일 정보 가져오기
		List<FileVO> fileList = fileService.selectFile(board_no);
		model.addAttribute("fileList", fileList);
	}

	// 글 수정 처리 GET
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGET(@RequestParam("board_no") Integer board_no, @ModelAttribute("ctr") Criteria ctr,
			HttpSession session, Model model, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("update GET");

		// 게시글 정보 수정
		BoardVO boardVO = boardService.read(board_no);

		// 권한 확인: 로그인 사용자와 작성자가 다를 경우 접근 차단
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null
				|| (!boardVO.getWriter().equals(loginUser.getUsername()) && !loginUser.getRole().equals("admin"))) {
			redirectAttributes.addFlashAttribute("result", "updateNO");
			return "redirect:/board/pageList";
		}

		// 파일 정보 추가
		List<FileVO> fileList = fileService.selectFile(board_no);
		model.addAttribute("fileList", fileList);

		model.addAttribute(boardVO);
		return "/board/update";
	}

	// 글 수정 처리 POST
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO boardVO, Criteria ctr, FileUtils fileUtils,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "deleteFiles", required = false) List<Integer> deleteFiles,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.info("update POST...");

		// 게시글 정보 저장
		boardService.update(boardVO);
		redirectAttributes.addFlashAttribute("result", "updateOK");

		// 기존 파일 삭제
		if (deleteFiles != null && !deleteFiles.isEmpty()) {
			for (Integer file_id : deleteFiles) {
				fileService.deleteFile(file_id);
			}
		}

		// 새로운 파일 추가
		if (files != null && files.length > 0) {
			List<FileVO> fileDetails = fileUtils.parseFileInfo(boardVO.board_no, boardVO.writer, files);
			for (FileVO file : fileDetails) {
				fileService.insertFile(file);
			}
		}

		// 페이지 위치 유지를 위한 정보
		redirectAttributes.addAttribute("pageNo", ctr.getPageNo());
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());

		// 검색과 키워드 유지를 위한 정보
		redirectAttributes.addAttribute("search", ctr.getSearch());
		redirectAttributes.addAttribute("keyword", ctr.getKeyword());

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

		// 페이지 위치 유지를 위한 정보
		redirectAttributes.addAttribute("pageNo", ctr.getPageNo());
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());

		// 검색과 키워드 유지를 위한 정보
		redirectAttributes.addAttribute("search", ctr.getSearch());
		redirectAttributes.addAttribute("keyword", ctr.getKeyword());

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
