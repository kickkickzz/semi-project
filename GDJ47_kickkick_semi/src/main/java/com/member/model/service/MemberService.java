package com.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

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
	
	//회원가입하기
	public boolean EnrollMember(Member m) {
		Connection conn = getConnection();
		boolean flag = dao.EnrollMember(conn,m);
		if(flag) commit(conn);
		else rollback(conn);
		close(conn);
		return flag;
	}
}
