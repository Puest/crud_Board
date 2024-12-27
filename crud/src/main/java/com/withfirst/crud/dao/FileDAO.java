package com.withfirst.crud.dao;

import java.util.List;

import com.withfirst.crud.vo.FileVO;

public interface FileDAO {
	public void insertFile(FileVO file);
	
	public List<FileVO> selectFile(int board_no);
	
	public void deleteFile(int file_id);
	
	public FileVO downloadFile(int file_id);
}
