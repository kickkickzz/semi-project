package com.board.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

public class Board {
	private int boardNum;
	private String boardWriterEmail;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardDeleteStatus;
	private String boardWriter;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int boardNum, String boardWriterEmail, String boardTitle, String boardContent, Date boardDate,
			String boardDeleteStatus, String boardWriter) {
		super();
		this.boardNum = boardNum;
		this.boardWriterEmail = boardWriterEmail;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardDeleteStatus = boardDeleteStatus;
		this.boardWriter = boardWriter;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", boardWriterEmail=" + boardWriterEmail + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardDate=" + boardDate + ", boardDeleteStatus="
				+ boardDeleteStatus + ", boardWriter=" + boardWriter + "]";
	}
	
	
	
}
