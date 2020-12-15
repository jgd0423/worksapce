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
	
	public int insertMemberInfo(ShopMemberDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "insert into joinTBL02 values (seq_tbl02.nextval, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, dto.getAge());
			pstmt.setString(7, "1");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<ShopMemberDTO> getListAll() {
		getConn();
		ArrayList<ShopMemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from joinTBL02 order by name asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ShopMemberDTO dto = new ShopMemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAge(rs.getInt("age"));
				dto.setJoinDate(rs.getString("joinDate"));
				
				memberList.add(dto);
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public ArrayList<ShopMemberDTO> getMemberList() {
		getConn();
		ArrayList<ShopMemberDTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from jointbl02 where ismember = '1' order by name asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ShopMemberDTO dto = new ShopMemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAge(rs.getInt("age"));
				dto.setJoinDate(rs.getString("joinDate"));
				
				memberList.add(dto);
			}
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public ShopMemberDTO getMemberInfo(String no) {
		getConn();
		ShopMemberDTO dto = new ShopMemberDTO();
		try {
			String sql = "select * from joinTBL02 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAge(rs.getInt("age"));
				dto.setJoinDate(rs.getString("joinDate"));
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
			String sql = "update joinTBL02 set phone = ?, email = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getNo());
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMemberInfo(ShopMemberDTO dto) {
		getConn();
		int no = dto.getNo();
		int result = 0;
		try {
			String sql = "update joinTBL02 set isMember = '0' where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			if (pstmt != null) { pstmt.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
