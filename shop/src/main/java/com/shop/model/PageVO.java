package com.shop.model;

import lombok.Data;

@Data
public class PageVO {
	//페이지 시작 번호
	private int pageStart;
	
	//페이지 끝 번호
	private int pageEnd;
	
	//이전, 다음 버튼의 존재 유무
	private boolean next, prev;
	
	//행 전체 갯수
	private int total;
	
	//현재 페이지 번호(pageNum), 행 표시 수(amount), 검색 키워드(keyword), 검색 종류(type)
	private Criteria cri;
	
	//생성자(클래스 호출시 각 변수 값 초기화
	public PageVO(Criteria cri, int total) {
		//초기화
		this.total = total;
		this.cri = cri;
		
		//페이지 끝 번호
		this.pageEnd = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		
		//페이지 시작 번호
		this.pageStart = this.pageEnd - 9;
		
		//전체 마지막 페이지 번호
		int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
		
		//페이지 끝 번호 유효성 체크
		if(realEnd < pageEnd) {
			this.pageEnd = realEnd;
		}
		
		//이전 버튼 값 초기화
		this.prev = this.pageStart>1;
		
		//다음 버튼 값 초기화
		this.next = this.pageEnd<realEnd;
	}
	
	
	
}
