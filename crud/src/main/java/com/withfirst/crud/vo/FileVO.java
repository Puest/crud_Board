package com.withfirst.crud.vo;

import java.sql.Timestamp;

public class FileVO {
	private int file_id;
	private int board_no;
	private String org_file_name;
	private String save_file_name;
	private long file_size;
	private String crea_id;
	private Timestamp upload_date;

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getOrg_file_name() {
		return org_file_name;
	}

	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public String getCrea_id() {
		return crea_id;
	}

	public void setCrea_id(String crea_id) {
		this.crea_id = crea_id;
	}

	public Timestamp getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}

	@Override
	public String toString() {
		return "FileVO [file_id=" + file_id + ", board_no=" + board_no + ", org_file_name=" + org_file_name
				+ ", save_file_name=" + save_file_name + ", file_size=" + file_size + ", crea_id=" + crea_id
				+ ", upload_date=" + upload_date + "]";
	}
	
}
