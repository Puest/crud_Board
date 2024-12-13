package com.withfirst.crud.dao.impl;

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
	private static String InsertMember = NameSpace + ".insertMember";
	private static String SelectMember = NameSpace + ".selectMember";

	@Override
	public void insertMember(MemberVO memberVO) {
		sqlSession.insert(InsertMember, memberVO);
	}

	@Override
	public MemberVO selectMember(String username) {
		return (MemberVO) sqlSession.selectOne(SelectMember, username);
	}
}
