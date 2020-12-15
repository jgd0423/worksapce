package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public MemberDAO() {}
	
	// Method
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspTest";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public int setInsert(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into joinTBL01 values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getCheckPw());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getEmail());
			pstmt.setInt(7, dto.getBirthYear());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<MemberDTO> getListAll() {
		getConn();
		ArrayList<MemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from joinTBL01 order by name asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthYear(rs.getInt("birthYear"));
				
				memberList.add(dto);
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public MemberDTO getMemberInfo(String id) {
		getConn();
		MemberDTO dto = new MemberDTO();
		try {
			String sql = "select * from joinTBL01 where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthYear(rs.getInt("birthYear"));
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int modifyMemberInfo(MemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update joinTBL01 set phone = ?, email = ?, birthyear = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getBirthYear());
			pstmt.setString(4, dto.getId());
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMemberInfo(MemberDTO dto) {
		getConn();
		String id = dto.getId();
		int result = 0;
		try {
			String sql = "delete from joinTBL01 where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}






















