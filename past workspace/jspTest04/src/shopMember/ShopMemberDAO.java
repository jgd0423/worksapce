package shopMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import shopMember.ShopMemberDTO;

public class ShopMemberDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public ShopMemberDAO() {}
	
	// Method
	public void getConn() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String dbId = "jspSample";
			String dbPasswd = "1234";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
			System.out.println("-- 오라클 접속 성공 --");
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		}
	}
	
	public int insertMemberInfo(ShopMemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into member values (seq_mem.nextval, ?, ?, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, dto.getBirthYear());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<ShopMemberDTO> getListByState(String state) {
		getConn();
		ArrayList<ShopMemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "";
			if (state.equals("1")) {
				sql = "select * from member order by name asc";
			} else {
				sql = "select * from member where state = " + "'" + state + "'" + " order by name asc";
				System.out.println(sql);
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ShopMemberDTO dto = new ShopMemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setwDate(rs.getString("wDate"));
				
				memberList.add(dto);
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public ArrayList<ShopMemberDTO> getJoinedMemberList() {
		getConn();
		ArrayList<ShopMemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from member where state = 'a'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ShopMemberDTO dto = new ShopMemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setwDate(rs.getString("wDate"));
				
				memberList.add(dto);
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public ShopMemberDTO getMemberInfo(String no, String id) {
		getConn();
		ShopMemberDTO dto = new ShopMemberDTO();
		try {
			String sql = "select * from member where (no = ? and id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setwDate(rs.getString("wDate"));
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public int modifyMemberInfo(ShopMemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update member set phone = ?, email = ? where (no = ? and id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getId());
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int setMemberStateDelete(ShopMemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update member set memberState = '2' where (no = ? and id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getId());
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
