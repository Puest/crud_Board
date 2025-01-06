package com.withfirst.crud.service.Impl;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.withfirst.crud.dao.MemberDAO;
import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.LoginService;
import com.withfirst.crud.vo.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	MemberDAO memberDAO;
	
	@Transactional()
	@Override
	public void create(MemberVO memberVO) {
		memberDAO.create(memberVO);
	}
	
	@Transactional(readOnly = true)
	@Override
	public MemberVO read(String username) throws Exception {
		return memberDAO.read(username);
	}

	@Transactional(readOnly = true)
	@Override
	public List<MemberVO> allList() throws Exception {
		return memberDAO.allList();
	}
	
	@Transactional()
	@Override
	public void delete(Integer user_id) throws Exception {
		memberDAO.delete(user_id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<MemberVO> memberList(Criteria ctr) throws Exception {
		return memberDAO.memberList(ctr);
	}

	@Transactional(readOnly = true)
	@Override
	public int totalCount(Criteria ctr) throws Exception {
		return memberDAO.totalCount(ctr);
	}
	
}
