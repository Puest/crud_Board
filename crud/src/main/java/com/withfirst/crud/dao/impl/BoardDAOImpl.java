package com.withfirst.crud.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.withfirst.crud.dao.BoardDAO;
import com.withfirst.crud.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static String nameSpace = "BoardMapper";
	private static String CREATE = nameSpace + ".create";
	private static String READ = nameSpace + ".read";
	private static String UPDATE = nameSpace + ".update";
	private static String DELETE = nameSpace + ".delete";
	private static String ALLLIST = nameSpace + ".allList";
	
	@Override
	public void create(BoardVO boardVO) throws Exception {
		sqlSession.insert(CREATE, boardVO);
	}

	@Override
	public BoardVO read(Integer board_no) throws Exception {
		return sqlSession.selectOne(READ, board_no);
	}

	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update(UPDATE, boardVO);
	}

	@Override
	public void delete(Integer board_no) throws Exception {
		sqlSession.delete(DELETE, board_no);
	}

	@Override
	public List<BoardVO> allList() throws Exception {
		return sqlSession.selectList(ALLLIST);
	}

}