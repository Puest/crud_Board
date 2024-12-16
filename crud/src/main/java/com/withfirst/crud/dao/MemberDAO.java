package com.withfirst.crud.dao;

import com.withfirst.crud.vo.MemberVO;

public interface MemberDAO {
	
	public void create(MemberVO memberVO);

	public MemberVO read(String username);
}
