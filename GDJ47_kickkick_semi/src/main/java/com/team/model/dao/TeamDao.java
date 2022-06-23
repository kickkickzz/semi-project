package com.team.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.match.model.vo.Match;
import com.member.model.vo.Member;
import com.team.model.vo.Team;
import com.team.model.vo.TeamMember;
import com.team.model.vo.TeamMemberInfo;

public class TeamDao {
	private Properties prop=new Properties();
	
	public TeamDao() {
		String path=TeamDao.class.getResource("/sql/team_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Team selectTeamByName(Connection conn, String team_name) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Team t=null;
		try {
			pstmt = conn.prepareStatement(prop.getProperty("selectTeamByName"));
			pstmt.setString(1, team_name);
			rs = pstmt.executeQuery();
			if(rs.next()) t = (TeamDao.getTeam(rs));
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return t;
	}
	

	public List<Team> searchTeamList(Connection conn, String type, String  keyword,
				int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> result=new ArrayList();
		String sql=prop.getProperty("searchTeamList");
		sql=sql.replace("$COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, type.equals("team_name")?"%"+keyword+"%":keyword);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(TeamDao.getTeam(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int searchTeamCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql=prop.getProperty("searchTeamCount").replace("$COL",type);
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, type.equals("team_name")?"%"+keyword+"%":keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	public List<Team> selectTeamList(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Team> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(TeamDao.getTeam(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public int selectTeamCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public Team selectTeam(Connection conn, String team_code) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Team t=null;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeam"));
			pstmt.setString(1, team_code);
			rs=pstmt.executeQuery();
			if(rs.next()) t=(TeamDao.getTeam(rs));
				
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return t;
	}
	
	public ArrayList<TeamMemberInfo> selectTeamMemberList(Connection conn,String team_code) {
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<TeamMemberInfo> teamMemberArr = null;
		TeamMemberInfo TeamMemberInfo = null;
		
		
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectTeamMemberList"));
			pstmt.setString(1, team_code);
			rs=pstmt.executeQuery();
			
			teamMemberArr=new ArrayList<TeamMemberInfo>();
			
			while(rs.next()) {
				TeamMemberInfo=TeamDao.getTeamMemberInfo(rs);
						
				
				teamMemberArr.add(TeamMemberInfo);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return teamMemberArr;
		
	}
	

	
	public int teamEnrollment(Connection conn, Team team) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("enrollTeam");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team.getTeam_code());
			pstmt.setString(2, team.getTeam_leader());
			pstmt.setString(3, team.getTeam_name());
			pstmt.setString(4, team.getTeam_gender());
			pstmt.setString(5, team.getTeam_age());
			pstmt.setString(6, team.getTeam_region());
			pstmt.setString(7, team.getTeam_mark_img());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}
	
	public int getTeamRegistCheck(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("teamRegistCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}
	
	public int getTeamCodeCheck(Connection conn, String team_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("teamCodeCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team_code);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}
	
	public int teamMemberRegistCheckNum(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("teamMemberRegistCheckNum");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;

	}
	
	public int getTeamMemberRegistCheck(Connection conn, String userId, String team_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("teamMemberRegistCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, team_code);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;

	}

	
	public TeamMember getTeamMemberApplicationInfo(Connection conn, String userId, String team_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TeamMember teamMember = null;

		String query = prop.getProperty("teamMemberApplicationInfo");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, team_code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				teamMember = teamMember.builder()
						.supporter_email(rs.getString("supporter_email"))
						.support_team(rs.getString("support_team"))
						.position(rs.getString("position"))
						.application_status(rs.getString("application_status"))
						.delete_status(rs.getString("delete_status"))
						.build();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return teamMember;
	}
	
	public int teamMemberReApplication(Connection conn, String userId, String team_code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("teamMemberReApplication");
		System.out.println(userId);
		System.out.println(team_code);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, team_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int teamMemberApplication(Connection conn, String userId, String team_code, String position) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("teamMemberApplication");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, team_code);
			pstmt.setString(3, position);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public int teamRegistNameCheck(Connection conn, String team_name) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("teamRegistNameCheck");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team_name);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}

	public int getTeamApplicationCount(Connection conn, String team_code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("getTeamApplicationCount");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team_code);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return result;
	}

	public ArrayList<TeamMemberInfo> selectTeamApplication(Connection conn, String team_code) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TeamMemberInfo> list = null;
		TeamMemberInfo t = null;

		String query = prop.getProperty("selectTeamApplication");

		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, team_code);
			
			rs = pstmt.executeQuery();
			list=new ArrayList<TeamMemberInfo>();
			while (rs.next()) {
				t = (TeamDao.getTeamMemberInfo(rs));
				
				

				list.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return list;
	}
	

	public int teamExpulsion(Connection conn, String supporter, String team_code){
		PreparedStatement pstmt=null;
		int result=0;
		String query=prop.getProperty("teamExpulsion");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, supporter);
			pstmt.setString(2, team_code);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	public int teamAccept(Connection conn, String supporter, String team_code) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=prop.getProperty("teamAccept");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, supporter);
			pstmt.setString(2, team_code);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int teamCancel(Connection conn, String supporter, String team_code) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=prop.getProperty("teamCancel");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, supporter);
			pstmt.setString(2, team_code);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int teamMatchStatusCheck(Connection conn, String match_regist_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String query = prop.getProperty("teamMatchStatusCheck");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, match_regist_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	
	public int teamMatchAcStatus(Connection conn, String match_regist_num, String team_code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("teamMatchAcStatus");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, match_regist_num);
			pstmt.setString(2, team_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	


	public int teamMatchAccept(Connection conn, String match_regist_num, String team_code, String winlose) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("teamMatchAccept");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, winlose);
			pstmt.setString(2, match_regist_num);
			pstmt.setString(3, team_code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	

	public int teamMatchCaStatus(Connection conn, String match_regist_num, String team_code) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = prop.getProperty("teamMatchCaStatus");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, match_regist_num);
			pstmt.setString(2, team_code);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int teamMatchCancel(Connection conn, String match_regist_num, String team_code) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = prop.getProperty("teamMatchCancel");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, match_regist_num);
			pstmt.setString(2, team_code);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public ArrayList<Match> selectMatch(Connection conn, String team_code) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Match> list=null;
		Match m = null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMatch"));
			pstmt.setString(1, team_code);
			rs=pstmt.executeQuery();
			list=new ArrayList<Match>();
			while (rs.next()) {
				
				m=Match.builder().team_code(rs.getString("match_team")).regist_num(rs.getInt("match_regist_num")).build();
				list.add(m);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	
	
	public static Match getMatch(ResultSet rs) {
		Match m=null;
		try {
			m=Match.builder()
					.regist_num(rs.getInt("regist_num"))
					.regist_team(rs.getString("regist_team"))
					.regist_reservation_code(rs.getString("regist_reservation_code"))
					.regist_status(rs.getString("regist_status"))
					.regist_branch_num(rs.getString("regist_branch_num"))
					.regist_stadium_num(rs.getInt("regist_stadium_num"))
					.team_code(rs.getString("team_code"))
					.team_leader(rs.getString("team_leader"))
					.team_num(rs.getInt("team_num"))
					.team_name(rs.getString("team_name"))
					.team_gender(rs.getString("team_gender"))
					.team_age(rs.getString("team_age"))
					.team_region(rs.getString("team_region"))		
					.team_point(rs.getInt("team_point"))																		
					.team_mark_img(rs.getString("team_mark_img"))
					.team_active_lastday(rs.getDate("team_active_lastday"))
					.team_delete_status(rs.getString("team_delete_status"))
					.reservation_code(rs.getString("reservation_code"))
					.reservation_email(rs.getString("reservation_email"))
					.reservation_branch_num(rs.getString("reservation_branch_num"))
					.reservation_stadium_num(rs.getInt("reservation_stadium_num"))
					.reservation_num(rs.getInt("reservation_num"))
					.reservation_price(rs.getInt("reservation_price"))
					.reservation_usage_start_time(rs.getInt("reservation_usage_start_time"))
					.reservation_usage_time(rs.getInt("reservation_usage_time"))
					.reservation_usage_end_time(rs.getInt("reservation_usage_end_time"))
					
					.reservation_status(rs.getString("reservation_status"))
					.branch_num(rs.getString("branch_num"))
					.branch_manager_email(rs.getString("branch_manager_email"))
					.branch_address(rs.getString("branch_address"))
					.branch_phone(rs.getString("branch_phone"))
					.branch_img(rs.getString("branch_img"))
					.branch_website(rs.getString("branch_website"))
					.branch_point(rs.getInt("branch_point"))
					.branch_option_shower(rs.getString("branch_option_shower"))
					.branch_option_park(rs.getString("branch_option_park"))
					.branch_option_uniform(rs.getString("branch_option_uniform"))
					.branch_option_shoes(rs.getString("branch_option_shoes"))
					.branch_option_ball(rs.getString("branch_option_ball"))
					.branch_option_inout(rs.getString("branch_option_inout"))
					.branch_delete_status(rs.getString("branch_delete_status"))
					.stadium_num(rs.getInt("stadium_num"))
					.stadium_branch_num(rs.getString("stadium_branch_num"))
					.stadium_name(rs.getString("stadium_name"))
					.stadium_match_member(rs.getString("stadium_match_member"))
					.stadium_reservation_start_time(rs.getInt("stadium_reservation_start_time"))
					.stadium_reservation_end_time(rs.getInt("stadium_reservation_end_time"))
					.stadium_delete_status(rs.getString("stadium_delete_status"))
					.build();		
																																									
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	
	public static TeamMember getTeamMember(ResultSet rs) {
		TeamMember t=null;
		try {
			t=TeamMember.builder()
						.supporter_email(rs.getString("supporter_email"))
						.support_team(rs.getString("support_team"))
						.position(rs.getString("position"))
						.application_status(rs.getString("application_status"))
						.delete_status(rs.getString("delete_status"))
						.build();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	
	
	public static Team getTeam(ResultSet rs) {
		Team t=null;
		try {
			t=Team.builder()
					.team_code(rs.getString("team_code"))
					.team_leader(rs.getString("team_leader"))
					.team_num(rs.getInt("team_num"))
					.team_name(rs.getString("team_name"))
					.team_gender(rs.getString("team_gender"))
					.team_age(rs.getString("team_age"))
					.team_mark_img(rs.getString("team_mark_img"))
					.team_point(rs.getInt("team_point"))
					.team_region(rs.getString("team_region"))
					.team_active_lastday(rs.getDate("team_active_lastday"))
					.team_delete_status(rs.getString("team_delete_status"))
					.build();

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	
	
	
	
	
	public static TeamMemberInfo getTeamMemberInfo(ResultSet rs) {
		TeamMemberInfo tmi=null;
		try {
			tmi=TeamMemberInfo.builder()
					.supporter_email(rs.getString("supporter_email"))
					.support_team(rs.getString("support_team"))
					.position(rs.getString("position"))
					.application_status(rs.getString("application_status"))
					.delete_status(rs.getString("delete_status"))
					.email(rs.getString("email"))
					.password(rs.getString("password"))
					.name(rs.getString("name"))
					.phone(rs.getString("phone"))
					.birthday(rs.getDate("birthday"))
					.gender(rs.getString("gender"))
					.type(rs.getString("type"))
					.delete_status(rs.getString("deletestatus"))
					.address(rs.getString("address"))
					.build();

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return tmi;
	}
	
	
	
	//메인화면 팀 정보
	public List<Team> fourTeam(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Team> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("fourTeam"));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Team t = getTeam(rs);
				list.add(t);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	//로그인한 유저가 가입한 팀 정보 보기
	public List<Team> joinTeam(Connection conn, String email){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Team> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(prop.getProperty("joinTeam"));
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Team t = getTeam(rs);
				list.add(t);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int deleteTeammember(Connection conn, String email) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String query = prop.getProperty("deleteTeammember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}
}
