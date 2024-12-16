package com.withfirst.crud.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class BoardVO {
	public int board_no; // 게시글 ID
	public String writer; // 작성자
	public String title; // 제목
	public String description; // 내용
	public Timestamp created_at; // 등록일자
	public Timestamp updated_at; // 수정일자

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public String toString() {
		return "BoardVO [bno=" + board_no + ", writer=" + writer + ", title=" + title + ", content=" + description
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
}
