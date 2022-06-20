package com.member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.member.model.vo.Member;

public class MemberDao {
	private static MemberDao dao;
	Properties prop = new Properties();
	
	private MemberDao() {
		try {
			prop.load(new FileReader(MemberDao.class.getResource("/sql/member.properties").getPath()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MemberDao getMemberDao() {
		if(dao==null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	
	//멤버 결과받아오기 공용메소드
	public static Member getMember(ResultSet rs) {
		Member m = null;
		try {
			m = Member.builder()
					.email(rs.getString("email"))
					.password(rs.getString("password"))
					.name(rs.getString("name"))
					.phone(rs.getString("phone"))
					.birthday(rs.getDate("birthday"))
					.address(rs.getString("address"))
					.gender(rs.getString("gender"))
					.build();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	//로그인하기
	public Member LoginMember(Connection conn, String email, String pwd) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("loginMember"));
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return m;
	}
	
	//회원가입하기
	public boolean EnrollMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		boolean flag = false;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("enrollMember"));
			pstmt.setString(1, m.getEmail());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getPhone());
			pstmt.setDate(5, m.getBirthday());
			//pstmt.setString(6, m.getGender());
			pstmt.setString(6, m.getAddress());
			result = pstmt.executeUpdate();
			if(result>0) flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return flag;
	}
	
	
	//회원정보 수정
	public int updateMember(Connection conn , String email, Date birth , String phone) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateMember"));
			pstmt.setString(1, phone);
			pstmt.setDate(2, birth);
			//pstmt.setString(3, address);
			//pstmt.setString(4, gender);
			pstmt.setString(3, email); //where절 에 있는 ? 에 값넣기
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//비밀번호 변경을 위한 회원정보 불러오기
	public Member loginCheck(Connection conn, String email, String oriPw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("loginCheck"));
			pstmt.setString(1, email);
			pstmt.setString(2, oriPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = getMember(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				close(rs);
				close(pstmt);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	//비밀번호 변경
	public int updatePassword(Connection conn, String email, String newPw) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updatePassword"));
			pstmt.setString(1, newPw);
			pstmt.setString(2, email); //where절에 넣는값
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	//회원탈퇴
	public int deleteMember(Connection conn, String email) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("deleteMember"));
			pstmt.setString(1, email);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	//아이디에 해당하는 멤버 불러오기
	public Member selectMemberByEmail(Connection conn, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberByEmail"));
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) m =getMember(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	//주소 변경하기
	public int updateAddress(Connection conn, String email, String address) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateAddress"));
			pstmt.setString(1, address);
			pstmt.setString(2, email); //where절에 넣는값
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//이메일 찾기
	public Member selectEmail(Connection conn, String name, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member m = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectEmail"));
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs=pstmt.executeQuery();
			if(rs.next()) m =getMember(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
}
