package com.board.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
	private int boardNum;
	private String boardWriter; //�ۼ����̸�
	private String boardWriterEmail; //�ۼ��� �̸���
	private String boardTitle; //����
	private String boardContent; //����
	private String boardImgPath; //�̹������� ���
	private Date boardDate; //�������� ��ϳ�¥
	private String boardDeleteStatus; //�Խ��� ��������
}
