package com.withfirst.crud.service.Impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.withfirst.crud.dao.MemberDAO;
import com.withfirst.crud.service.LoginService;
import com.withfirst.crud.vo.MemberVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Inject
	MemberDAO memberDAO;
	
	@Override
	public MemberVO read(String username) throws Exception {
		return memberDAO.read(username);
	}

	@Override
	public void create(MemberVO memberVO) {
		memberDAO.create(memberVO);
	}
	
}
