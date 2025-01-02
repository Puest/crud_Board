package com.withfirst.crud.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.withfirst.crud.vo.FileVO;

public class FileUtils {
	private Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private String uploadPath = "D:\\upload\\";

	public List<FileVO> parseFileInfo(int board_no, String username, MultipartFile[] files) throws Exception {
		logger.info("uploadPath: " + uploadPath);
		
		List<FileVO> fileList = new ArrayList<>();
		
		File target = new File(uploadPath);
		if (!target.exists()) {
			target.mkdirs();
		}

		for (MultipartFile file : files) {
			String orgFileName = file.getOriginalFilename();
			String orgFileExtension = orgFileName.substring(orgFileName.lastIndexOf("."));
			String saveFileName = UUID.randomUUID().toString().replace("-", "") + orgFileExtension;
			Long saveFileSize = file.getSize() / 1024;

			// 파일 로그
			logger.info("============ File Test ============");
			logger.info("파일 이름: " + file.getName());
			logger.info("파일 실제 이름: " + file.getOriginalFilename());
			logger.info("파일 크기: " + file.getSize());
			logger.info("Content Type: " + file.getContentType());
			logger.info("============ File END ============");

			// 파일 전송
			target = new File(uploadPath, saveFileName);
			file.transferTo(target);

			// FileVO 객체에 저장
			FileVO fileVO = new FileVO();
			fileVO.setBoard_no(board_no);
			fileVO.setCrea_id(username);
			fileVO.setOrg_file_name(orgFileName);
			fileVO.setSave_file_name(saveFileName);
			fileVO.setFile_size(saveFileSize);

			fileList.add(fileVO);
		}

		return fileList;
	}
}
