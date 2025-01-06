package com.withfirst.crud.dao;

import java.util.List;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.vo.MemberVO;

public interface MemberDAO {
	
	public void create(MemberVO memberVO);

	public MemberVO read(String username);
	
	public List<MemberVO> allList();
	
	public void delete(Integer user_id) throws Exception;
	
	public List<MemberVO> memberList(Criteria ctr) throws Exception;
	
	public int totalCount(Criteria ctr) throws Exception;
}
