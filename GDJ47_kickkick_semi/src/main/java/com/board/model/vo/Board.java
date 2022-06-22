package com.board.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	private int boardNum;
	private String boardWriterEmail;
	private String boardTitle;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenamedFilename;
	private Date boardDate;
	private String boardDeleteStatus;
	private String boardWriter;
	private int boardReadCount;
	
	
	
}
