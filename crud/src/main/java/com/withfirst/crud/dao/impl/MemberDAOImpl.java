package com.withfirst.crud.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.withfirst.crud.dao.MemberDAO;
import com.withfirst.crud.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;

	private static String NameSpace = "MemberMapper";
	private static String InsertMember = NameSpace + ".create";
	private static String SelectMember = NameSpace + ".read";
	private static String SelectAllMember = NameSpace + ".readAll";
	private static String deleteMember = NameSpace + ".delete";

	@Override
	public void create(MemberVO memberVO) {
		sqlSession.insert(InsertMember, memberVO);
	}

	@Override
	public MemberVO read(String username) {
		return (MemberVO) sqlSession.selectOne(SelectMember, username);
	}

	@Override
	public List<MemberVO> allList() {
		return sqlSession.selectList(SelectAllMember);
	}

	@Override
	public void delete(Integer seq) throws Exception {
		sqlSession.delete(deleteMember, seq);
	}
}
