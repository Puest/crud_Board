package com.withfirst.crud.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {
	private int pageNo; // 현재 페이지
	private int totalPageNo; // 총 페이지 개수
	private String search;
	private String keyword;

	public Criteria() {
		this.pageNo = 1;
		this.totalPageNo = 10;
		this.search = null;
		this.keyword = null;
	}

	// 시작 데이터(ex. DB의 1번 데이터는 0번을 뜻 함)
	public int getStartPage() {
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	// 파리미터를 통한 URI 쿼리 생성
	public String makerQuery() {
		UriComponentsBuilder uriComponents = UriComponentsBuilder.newInstance().queryParam("pageNo", pageNo)
				.queryParam("totalPagesNo", this.totalPageNo);

		if (search != null) {
			uriComponents.queryParam("search", this.search).queryParam("keyworkd", this.keyword);
		}

		return uriComponents.build().encode().toString();
	}

	@Override
	public String toString() {
		return "Criteria [pageNo=" + pageNo + ", totalPageNo=" + totalPageNo + ", search=" + search + ", keyword="
				+ keyword + "]";
	}

}
