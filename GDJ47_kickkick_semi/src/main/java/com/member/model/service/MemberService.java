package com.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	MemberDao dao = MemberDao.getMemberDao();
	
	//로그인하기
	public Member LoginMember(String email, String pwd) {
		Connection conn = getConnection();
		Member m = dao.LoginMember(conn,email,pwd);
		close(conn);
		return m;
	}
}
