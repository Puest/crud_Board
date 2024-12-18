package com.withfirst.crud.dao;

import java.util.List;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.vo.BoardVO;

public interface BoardDAO {

	public void create(BoardVO boardVO) throws Exception;

	public BoardVO read(Integer board_no) throws Exception;

	public void update(BoardVO boardVO) throws Exception;

	public void delete(Integer board_no) throws Exception;

	public List<BoardVO> allList() throws Exception;

	public void increase(String writer) throws Exception;

	public void decrease(String writer) throws Exception;

	public List<BoardVO> postList(String writer) throws Exception;

	public List<BoardVO> pageList(Criteria ctr) throws Exception;
}
