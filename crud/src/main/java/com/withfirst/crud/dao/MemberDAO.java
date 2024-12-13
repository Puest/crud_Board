package com.withfirst.crud.dao;

import com.withfirst.crud.vo.MemberVO;

public interface MemberDAO {
	
	public void insertMember(MemberVO memberVO);

	public MemberVO selectMember(String username);
}
