package com.board.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	private int boardNum;
	private String boardWriter; //작성자이름
	private String boardWriterEmail; //작성자 이메일
	private String boardTitle; //제목
	private String boardContent; //내용
	private String boardImgPath; //이미지파일 경로
	private Date boardDate; //공지사항 등록날짜
	private String boardDeleteStatus; //게시판 삭제여부
}
