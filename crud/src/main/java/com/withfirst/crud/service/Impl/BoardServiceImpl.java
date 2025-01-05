package com.withfirst.crud.service.Impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.withfirst.crud.dao.BoardDAO;
import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;

	@Transactional()
	@Override
	public void create(BoardVO boardVO) throws Exception {
		boardDAO.create(boardVO);
	}

	@Override
	public BoardVO read(Integer board_no) throws Exception {
		return boardDAO.read(board_no);
	}

	@Transactional()
	@Override
	public void update(BoardVO boardVO) throws Exception {
		boardDAO.update(boardVO);
	}

	@Transactional()
	@Override
	public void delete(Integer board_no) throws Exception {
		boardDAO.delete(board_no);
	}

	@Override
	public List<BoardVO> allList() throws Exception {
		return boardDAO.allList();
	}

	@Override
	public List<BoardVO> pageList(Criteria ctr) throws Exception {
		return boardDAO.pageList(ctr);
	}

	@Override
	public int totalCount(Criteria ctr) throws Exception {
		return boardDAO.totalCount(ctr);
	}
}
