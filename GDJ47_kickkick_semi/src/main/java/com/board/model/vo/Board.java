package com.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNum;
	private String boardWriter;
	private String boardWriterEmail;
	private String boardTitle;
	private String boardContent;
	private String boardImgPath;
	private Date boardDate;
	private String boardDeleteStatus;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int boardNum, String boardWriter, String boardWriterEmail, String boardTitle, String boardContent,
			String boardImgPath, Date boardDate, String boardDeleteStatus) {
		super();
		this.boardNum = boardNum;
		this.boardWriter = boardWriter;
		this.boardWriterEmail = boardWriterEmail;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardImgPath = boardImgPath;
		this.boardDate = boardDate;
		this.boardDeleteStatus = boardDeleteStatus;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardWriterEmail() {
		return boardWriterEmail;
	}

	public void setBoardWriterEmail(String boardWriterEmail) {
		this.boardWriterEmail = boardWriterEmail;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardImgPath() {
		return boardImgPath;
	}

	public void setBoardImgPath(String boardImgPath) {
		this.boardImgPath = boardImgPath;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardDeleteStatus() {
		return boardDeleteStatus;
	}

	public void setBoardDeleteStatus(String boardDeleteStatus) {
		this.boardDeleteStatus = boardDeleteStatus;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", boardWriter=" + boardWriter + ", boardWriterEmail=" + boardWriterEmail
				+ ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardImgPath=" + boardImgPath
				+ ", boardDate=" + boardDate + ", boardDeleteStatus=" + boardDeleteStatus + "]";
	}
	
	
}
