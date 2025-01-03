package com.withfirst.crud.dao;

import java.util.List;

import com.withfirst.crud.vo.MemberVO;

public interface MemberDAO {
	
	public void create(MemberVO memberVO);

	public MemberVO read(String username);
	
	public List<MemberVO> allList();
	
	public void delete(Integer seq) throws Exception;
	
}
