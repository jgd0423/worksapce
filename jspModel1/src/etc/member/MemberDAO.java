package etc.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspModel1";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");					
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setInsert(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into member values ("
					+ "seq_member.nextval, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "sysdate, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getEmail());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getAddress());
			pstmt.setString(8, dto.getGender());
			pstmt.setString(9, dto.getJob());
			pstmt.setString(10, dto.getGrade());
			pstmt.setString(11, dto.getIp());
			pstmt.setInt(12, dto.getLoginFailCounter());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<MemberDTO> getListAll() {
		getConn();
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from member order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
				dto.setJob(rs.getString("job"));
				dto.setGrade(rs.getString("grade"));
				dto.setRegi_date(rs.getString("regi_date"));
				dto.setIp(rs.getString("ip"));
				dto.setLoginFailCounter(rs.getInt("loginfailcounter"));
				memberList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return memberList;
	}
	
	public MemberDTO getSelectOne(String id) {
		getConn();
		MemberDTO dto = new MemberDTO();
		try {
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
				dto.setJob(rs.getString("job"));
				dto.setGrade(rs.getString("grade"));
				dto.setRegi_date(rs.getString("regi_date"));
				dto.setIp(rs.getString("ip"));
				dto.setLoginFailCounter(rs.getInt("loginfailcounter"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public String login(MemberDTO dto) {
		getConn();
		String temp = "";
      
		try {
			String sql = "select passwd from member where id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
         
			rs = pstmt.executeQuery();
         
			if (rs.next()) {
				temp = rs.getString("passwd");
	            
				if (temp.equals(dto.getPasswd())) {
					temp = "success";
				} else {
					temp = "fail";
				}
			} else {
				temp = "notMember";
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return temp;
	}
	
	public void loginFailCounterPlus(String id) {
		getConn();
		try {
			String sql = "update member set loginfailcounter = (select loginfailcounter from member where id = ?) + 1 where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
	
	public void loginCounterSetZero(String id) {
		getConn();
		try {
			String sql = "update member set loginfailcounter = 0 where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose();
		}
	}
}
