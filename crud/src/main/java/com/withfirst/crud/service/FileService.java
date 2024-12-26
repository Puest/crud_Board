package com.withfirst.crud.service;

import java.util.List;

import com.withfirst.crud.vo.FileVO;

public interface FileService {
public void insertFile(FileVO file);
	
	public List<FileVO> selectFile(int board_no);
	
	public void deleteFile(int file_id);
}
