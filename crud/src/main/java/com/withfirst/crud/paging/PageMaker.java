package com.withfirst.crud.paging;

public class PageMaker {
	private int totalPostCnt; // 총 게시물 수
	private int startPage; // 현재 페이지를 기점으로 시작 페이지 번호
	private int endPage; // 현재 페이지를 기점으로 마지막 페이지 번호
	private boolean prev; // 이전 버튼 존재유무
	private boolean next; // 다음 버튼 존재유무
	private Criteria ctr; // 현재 페이지와 페이지당 보여지는 게시물 수를 가져옴

	public PageMaker(Criteria ctr) {
		this.ctr = ctr;
	}

	public int getTotalPostCnt() {
		return totalPostCnt;
	}

	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
		calcPost();
	}

	public void calcPost() {
		int currentPage = this.ctr.getCurrentPageNo();
		int recordsPerPage = this.ctr.getRecordsPageNo();

		// ceil(올림)을 통해 11 이상은 2페이지가 되고, 21 이상은 3페이지가 나옴
		this.endPage = (int) (Math.ceil(currentPage / (double) 10.0) * 10);
		
		// 현재 페이지 구현
		this.startPage = (this.endPage - 10) + 1;
		
		// 사용할 총 페이지 수(ex. page = 75개라면 → ceil(75 / 10) = 7.5 → 8 페이지)
		int totalEndPage = (int)(Math.ceil(totalPostCnt / (double) recordsPerPage));
		
		if(this.endPage > totalEndPage) {
			this.endPage = totalEndPage;
		}
		
		this.prev = (startPage != 1);
		this.next = (endPage * recordsPerPage < totalPostCnt);
		
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCtr() {
		return ctr;
	}

	public void setCtr(Criteria ctr) {
		this.ctr = ctr;
	}

}
