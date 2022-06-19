package com.member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;

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
	
	
	//회원정보수정하기
	public int updateMember(String email, Date birth,String phone,String address) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn,email,birth,phone,address);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//비밀번호 변경을 위한 회원정보 불러오기
	public Member loginCheck(String email, String oriPw) {
		Connection conn = getConnection();
		Member m = dao.loginCheck(conn,email,oriPw);
		close(conn);
		return m;
	}
	//비밀번호 변경 하기
	public int updatePassword(String email, String newPw) {
		Connection conn = getConnection();
		int result = dao.updatePassword(conn,email,newPw);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//회원탈퇴
}
