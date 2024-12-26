package com.withfirst.crud.service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.withfirst.crud.dao.FileDAO;
import com.withfirst.crud.service.FileService;
import com.withfirst.crud.vo.FileVO;

@Service
public class FileServiceImpl implements FileService{
	
	@Inject
	private FileDAO fileDAO;
	
	@Override
	public void insertFile(FileVO file) {
		fileDAO.insertFile(file);
	}

	@Override
	public List<FileVO> selectFile(int board_no) {
		return selectFile(board_no);
	}

	@Override
	public void deleteFile(int file_id) {
		fileDAO.deleteFile(file_id);
	}

}
