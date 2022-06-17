package com.member.model.vo;

import lombok.Data;

@Data
public class PageInfo {
	private int currentPage; //����������
	private int listCount; //����Ʈ�� ����� �Խñ� ����
	private int pageLimit; //�ѹ� �ѱ�µ� �����ִ� ������ ����
	private int boardLimit; //���������� ���̴� �Խù� ����
	private int maxPage;
	private int startPage; //����������
	private int endPage;  //�Ǹ�����������
}
