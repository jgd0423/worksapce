package member02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.Db;
import db.DbMysqlImpl;

public class Member02DAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public Member02DAO() {}
	
	// Method
	public void getConn() {
//		try {
//			String driver = "com.mysql.jdbc.Driver";
//			String dbUrl = "jdbc:mysql://localhost:3306/jspInterface";
//			String dbId = "jspInterface";
//			String dbPasswd = "1234";
//			
//			Class.forName(driver);
//			conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
//			System.out.println("-- MySQL 접속 성공 --");					
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("-- MySQL 접속 실패 --");
//		}
		Db d2 = new DbMysqlImpl();
		conn = d2.dbConn();
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
	
	public int setInsert(Member02DTO dto) {
		getConn();
		int result = 0;
		int maxNo = getMaxValue("no") + 1;
		Timestamp wdate = new Timestamp(System.currentTimeMillis());
		try {
			String sql = "insert into member values ("
						+ "?, ?, ?, ?, ?,"
						+ "?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxNo);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getSid());
			pstmt.setString(6, dto.getPhone());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getGender());
			pstmt.setInt(9, dto.getAge());
			pstmt.setTimestamp(10, wdate);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getMaxValue(String columnName) {
		int result = 0;
		try {
			String sql = "select nvl(max(" + columnName + "), 0) from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1); // 컬럼의 순서 입력해도 된다.
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Member02DTO> getListAll() {
		getConn();
		ArrayList<Member02DTO> memberList = new ArrayList<>();
		try {
			String sql = "select * from member order by no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member02DTO dto = new Member02DTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setSid(rs.getString("sid"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setwDate(rs.getTimestamp("wdate"));
				memberList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return memberList;
	}
	
	public Member02DTO getSelectOne(int no) {
		getConn();
		Member02DTO dto = new Member02DTO();
		try {
			String sql = "select * from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setSid(rs.getString("sid"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setwDate(rs.getTimestamp("wdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(Member02DTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update member set phone = ?, email = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getEmail());
			pstmt.setInt(3, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int setDelete(Member02DTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
}
