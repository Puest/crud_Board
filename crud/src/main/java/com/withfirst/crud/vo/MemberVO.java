package com.withfirst.crud.vo;

import java.sql.Date;
import java.sql.Timestamp;

// VO(Value Object): 쿼리의 결과 등 데이터를 담거나 요청 기능
public class MemberVO {
	private int seq; 			// 회원 가입 번호
	private String name;		// 회원 이름
	private String username; 	// 회원 ID
	private String password; 	// 회원 PW 
	private String email;  		// 회원 이메일
	private int post_cnt;		// 게시글 수
	private int comment_cnt;	// 댓글 수
	private String role;		// 회원 권한
	private Timestamp created_at;	// 회원 등록 날짜
	private Timestamp updated_at;	// 회원 수정 날짜

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPost_cnt() {
		return post_cnt;
	}

	public void setPost_cnt(int post_cnt) {
		this.post_cnt = post_cnt;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}
