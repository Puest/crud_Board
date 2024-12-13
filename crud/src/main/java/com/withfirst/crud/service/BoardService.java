package com.withfirst.crud.service;

import java.util.List;

import com.withfirst.crud.vo.BoardVO;

public interface BoardService {
	
	public void create(BoardVO boardVO) throws Exception;
	
	public BoardVO read(Integer board_no) throws Exception;
	
	public void update(BoardVO boardVO) throws Exception;
	
	public void delete(Integer board_no) throws Exception;
	
	public List<BoardVO> allList() throws Exception;
}
