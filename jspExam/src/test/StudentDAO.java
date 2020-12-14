package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;

public class StudentDAO {
	// Field
	Connection conn = Db.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public StudentDAO() {}
	
	// Method
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setInsert(StudentDTO dto) {
		int result = 0;
		try {
			String sql = "insert into student values (?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSid());
			pstmt.setString(2, dto.getSname());
			pstmt.setString(3, dto.getSphone());
			pstmt.setString(4, dto.getPname());
			pstmt.setString(5, dto.getPphone());
			pstmt.setString(6, dto.getAddr());
			pstmt.setString(7, dto.getHakyun());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<StudentDTO> getListAll() {
		ArrayList<StudentDTO> studentsList = new ArrayList<>();
		try {
			String sql = "select * from student order by sname asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setSid(rs.getString("sid"));
				dto.setSname(rs.getString("sname"));
				dto.setSphone(rs.getString("sphone"));
				dto.setPname(rs.getString("pname"));
				dto.setPphone(rs.getString("pphone"));
				dto.setAddr(rs.getString("addr"));
				dto.setHakyun(rs.getString("hakyun"));
				studentsList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return studentsList;
	}
}
