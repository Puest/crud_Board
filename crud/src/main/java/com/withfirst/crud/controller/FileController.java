package com.withfirst.crud.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.withfirst.crud.service.FileService;
import com.withfirst.crud.vo.FileVO;

@Controller
public class FileController {

	private Logger logger = LoggerFactory.getLogger(FileController.class);

	@Inject
	private String uploadPath;

	@Inject
	private FileService fileService;

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public void donwloadFile(@RequestParam("file_id") int file_id, HttpServletResponse response) throws Exception {
		logger.info("uploadPath: " + uploadPath);

		// 파일 정보 조회
		FileVO fileVO = fileService.downloadFile(file_id);
		if (fileVO == null) {
			throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
		}

		// 지정된 파일 이름과 원본 이름 파일 가져오기
		String orgFileName = fileVO.getOrg_file_name();
		String saveFileName = fileVO.getSave_file_name();

		// 다운로드 할 파일 경로 생성
		File downloadFile = new File(uploadPath + saveFileName);
		if (!downloadFile.exists()) {
			throw new RuntimeException("요청한 파일이 존재하지 않습니다.");
		}

		// 파일 내용을 바이트 배열로 읽어오기
		byte fileBytes[] = FileUtils.readFileToByteArray(downloadFile);

		// HTTP 응답 헤더 설정
		response.setContentType("application/octet-stream"); // 파일 다운로드 자바 응답 형식
		response.setContentLength(fileBytes.length); // 파일 사이즈 설정

		// Content-Disposition 설정 (파일명 인코딩)
		String encodedFileName = URLEncoder.encode(orgFileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary"); // 응답 형식에 따른 Binary 인코딩
		
		// 파일 전송
		response.getOutputStream().write(fileBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
