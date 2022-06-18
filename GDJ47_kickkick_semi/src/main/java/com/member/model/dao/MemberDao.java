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
			pstmt.setString(6, m.getGender());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getType());
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
	public int updateMember(Connection conn , String email, Date birth , String phone, String address, String gender) {
		PreparedStatement pstmt = null;
		int result=0;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("updateMember"));
			//pstmt.setDate(1, birth);
			pstmt.setString(1, phone);
			//pstmt.setString(2, address);
			//pstmt.setString(4, gender);
			pstmt.setString(2, email); //where절 에 있는 ? 에 값넣기
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
				m = Member.builder()
						.email(rs.getString("email"))
						.password(rs.getString("password"))
						.build();
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
	
	
}
