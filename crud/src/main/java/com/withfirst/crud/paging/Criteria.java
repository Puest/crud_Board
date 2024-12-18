package com.withfirst.crud.paging;

public class Criteria {
	private int currentPageNo;
	private int recordsPageNo;

	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPageNo = 10;
	}

	// 페이지 시작을 반환
	public int startPage() {
		return (this.currentPageNo - 1) * recordsPageNo;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if (currentPageNo <= 0) {
			this.currentPageNo = 1;
		} else {
			this.currentPageNo = currentPageNo;
		}
	}

	public int getRecordsPageNo() {
		return recordsPageNo;
	}

	public void setRecordsPageNo(int recordsPageNo) {
		if(recordsPageNo <= 0 || recordsPageNo > 100) {
			this.recordsPageNo = 10;
		} else {
			this.recordsPageNo = recordsPageNo;
		}
	}

	@Override
	public String toString() {
		return "Criteria [currentPageNo=" + currentPageNo + ", recordsPageNo=" + recordsPageNo + "]";
	}

}
