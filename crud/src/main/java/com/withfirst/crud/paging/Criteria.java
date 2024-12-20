package com.withfirst.crud.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {
	private int pageNo; // 현재 페이지
	private int totalPageNo; // 총 페이지 개수

	public Criteria() {
		this.pageNo = 1;
		this.totalPageNo = 10;
	}

	// 시작 데이터(ex. DB의 1번 데이터는 0번을 뜻 함)
	public int startPage() {
		return (this.pageNo - 1) * totalPageNo;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo <= 0) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		if (totalPageNo <= 0 || totalPageNo > 100) {
			this.totalPageNo = 10;
		} else {
			this.totalPageNo = totalPageNo;
		}
	}

	@Override
	public String toString() {
		return "Criteria [pageNo=" + pageNo + ", totalPageNo=" + totalPageNo + "]";
	}

	// 파리미터를 통한 URI 쿼리 생성
	public String makerQuery() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", pageNo)
				.queryParam("totalPages", this.totalPageNo).build().encode();

		return uriComponents.toString();
	}

}
