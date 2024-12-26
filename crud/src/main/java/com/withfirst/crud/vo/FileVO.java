package com.withfirst.crud.vo;

import java.sql.Timestamp;

public class FileVO {
	private int file_id;
	private int board_no;
	private String filename;
	private String file_path;
	private long file_size;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public Timestamp getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Timestamp upload_date) {
		this.upload_date = upload_date;
	}

	@Override
	public String toString() {
		return "FileVO [file_id=" + file_id + ", board_no=" + board_no + ", filename=" + filename + ", file_path="
				+ file_path + ", file_size=" + file_size + ", upload_date=" + upload_date + "]";
	}

}
