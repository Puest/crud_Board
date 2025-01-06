package com.withfirst.crud.service;

import java.util.List;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.vo.MemberVO;

public interface LoginService {
	// 현재는 어드민 계정으로 인한 하드코딩 중.
	// 추후 회원가입으로 인해 DB로 저장이 되는 경우 DB에서 조회 후 처리가 필요.
	public void create(MemberVO memberVO) throws Exception;

	public MemberVO read(String username) throws Exception;

	public List<MemberVO> allList() throws Exception;

	public void delete(Integer user_id) throws Exception;
	
	public List<MemberVO> memberList(Criteria ctr) throws Exception;
	
	public int totalCount(Criteria ctr) throws Exception;
}
