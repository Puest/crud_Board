package com.withfirst.crud.service;

public interface LoginService {
	// 현재는 어드민 계정으로 인한 하드코딩 중.
	// 추후 회원가입으로 인해 DB로 저장이 되는 경우 DB에서 조회 후 처리가 필요.
	public boolean vaildUser(String username, String password);
}
