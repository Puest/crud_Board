package com.withfirst.crud.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView registerPOST(BoardVO boardVO, Criteria ctr, @RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/board/pageList");

		logger.info("register POST...");

		try {
			// 게시글 등록 로직 호출
			boardService.create(boardVO);

			logger.info("Generated Board_no: " + boardVO.getBoard_no());

			// 업로드 파일 처리
			for (int i = 0; i < files.length; i++) {
				logger.info("============ File Test ============");
				logger.info("파일 이름: "+ files[i].getName());
				logger.info("파일 실제 이름: "+ files[i].getOriginalFilename());
				logger.info("파일 크기: "+ files[i].getSize());
				logger.info("Content Type: "+ files[i].getContentType());
				logger.info("============ File END ============");
			}	
//			if (files != null && files.length > 0) {
//				for (MultipartFile file : files) {
//					if (!file.isEmpty()) {
//						// 파일 저장 경로 설정
//						String uploadFolder = "D:/upload/";
//						File saveFile = new File(uploadFolder, file.getOriginalFilename());
//
//						// 파일 저장
//						file.transferTo(saveFile);
//
//						// 파일 정보를 DB에 저장
//						FileVO fileVO = new FileVO();
//						fileVO.setBoard_no(boardVO.getBoard_no());
//						fileVO.setFilename(file.getOriginalFilename());
//						fileVO.setFile_size((int) (file.getSize() / 1024.0));
//						fileVO.setFile_path(saveFile.getAbsolutePath());
//
//						fileService.insertFile(fileVO);
//					}
//				}
//			}

			redirectAttributes.addFlashAttribute("result", "registerOK");
		} catch (Exception e) {
			logger.info("Error POST: ", e);
		}

		redirectAttributes.addAttribute("pageNo", 1); // 등록 시 첫 페이지로 이동
		redirectAttributes.addAttribute("totalPageNo", ctr.getTotalPageNo());

		return mav;

	}

	// 게시글 전체 페이지 GET
	/*
	 * @RequestMapping(value = "/allList", method = RequestMethod.GET) public void
	 * listAll(Model model) throws Exception { logger.info("show all list");
	 * 
	 * List<BoardVO> members = boardService.allList();
	 * model.addAttribute("memberList", members); }
	 */

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

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void donwloadFile(@RequestParam("file_id") int file_id, HttpServletResponse response) throws Exception {

		// 파일 정보 가져오기
		FileVO fileVO = fileService.downloadFile(file_id);

		// 파일 경로 검증 및 객체 생성
		File file = new File(fileVO.getFile_path());

		// 파일 확장자 MIME 타입 설정
		String mimeType = Files.probeContentType(file.toPath());
		logger.info("mimeType: ", mimeType);
		response.setContentType(mimeType != null ? mimeType : "application/octet-stream");

		// Content-Disposition 설정 (파일명 인코딩)
		String encodedFileName = URLEncoder.encode(fileVO.getFilename(), "UTF-8").replace("+", "%20");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");

		// 파일 전송
		Files.copy(file.toPath(), response.getOutputStream());
		response.getOutputStream().flush();
	}

	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.info("Logout...");
		session.invalidate();

		return "redirect:/member/login";
	}
}
