package com.member.model.vo;

import lombok.Data;

@Data
public class PageInfo {
	private int currentPage; //현재페이지
	private int listCount; //리스트에 저장된 게시글 개수
	private int pageLimit; //한번 넘기는데 보여주는 페이지 개수
	private int boardLimit; //한페이지당 보이는 게시물 개수
	private int maxPage;
	private int startPage; //시작페이지
	private int endPage;  //맨마지막페이지
}
