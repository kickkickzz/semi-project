package com.member.model.service;


import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;
import com.reservation.model.vo.Branch;
import com.reservation.model.vo.PayHistory;

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
	public int updateMember(String email, Date birth,String phone) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn,email,birth,phone);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//비밀번호 변경을 위한 회원정보 불러오기
	public Member loginCheck(String email, String pwd) {
		Connection conn = getConnection();
		Member m = dao.LoginMember(conn,email,pwd);
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
	public int deleteMember(String email) {
		Connection conn = getConnection();
		int result = dao.deleteMember(conn,email);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	//아이디에 해당하는 멤버 불러오기
	public Member selectMemberByEmail(String email) {
		Connection conn = getConnection();
		Member m = dao.selectMemberByEmail(conn, email);
		close(conn);
		return m;
	}

	//예약내역
	public List<PayHistory> selectpayhistory(String email){
		Connection conn = getConnection();
		List<PayHistory> result = dao.selectpayhistory(conn,email);
		close(conn);
		return result;
		
	}
	
	//주소 변경하기
	public int updateAddress(String email, String address) {
		Connection conn = getConnection();
		int result = dao.updateAddress(conn,email,address);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	
	
	
	//이메일 찾기
	public Member selectEmail(String name, String phone) {
		Connection conn = getConnection();
		Member result = dao.selectEmail(conn,name,phone);
		close(conn);
		return result;
	}
	

	//비밀번호 찾기
	public Member passwordForgot(String email, String name) {
		Connection conn = getConnection();
		Member result = dao.passwordForgot(conn,email, name);
		close(conn);
		return result;
	}
	
	//임시비밀번호로 업데이트
//	public int randomPassword(String email,String ranPassword) {
//		Connection conn = getConnection();
//		int result = dao.randomPassword(conn,email,ranPassword);
//		if(result>0) commit(conn);
//		else rollback(conn);
//		return result;
//	}
	
	
	
	
	//임시비밀번호
	public int extraPassword(String email, String AuthenticationKey) {
		Connection conn = getConnection();
		int result = dao.extraPassword(conn,email,AuthenticationKey);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	
	//비밀번호 인증받았을떄 확인할 이메일
	public Member emailCheck(String email) {
		Connection conn = getConnection();
		Member result = dao.emailCheck(conn,email);
		close(conn);
		return result;
	}
	//지점등록
	public int insertbranch(Branch b){
		Connection conn = getConnection();
		int result = dao.insertbranch(conn,b);
		if(result>0)commit(conn);
		else rollback(conn);
		return result;
	}
	
	
}
