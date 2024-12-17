package com.withfirst.crud.service.Impl;


import java.util.List;

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
	public void create(MemberVO memberVO) {
		memberDAO.create(memberVO);
	}
	
	@Override
	public MemberVO read(String username) throws Exception {
		return memberDAO.read(username);
	}

	
	@Override
	public List<MemberVO> allList() throws Exception {
		return memberDAO.allList();
	}

	@Override
	public void delete(Integer seq) throws Exception {
		memberDAO.delete(seq);
	}
}
