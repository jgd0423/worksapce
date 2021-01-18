package appTest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import appTest.model.dto.AppTestDTO;
import db.Db;
import db.DbImplOracle;

public class AppTestDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Method
	public void getConn() {
		Db db = new DbImplOracle();
		conn = db.dbConn();
	}
	
	public void getConnClose() {
		Db db = new DbImplOracle();
		db.dbConnClose();
	}
	
	public int setInsert(AppTestDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO join VALUES (seq_join.NEXTVAL, "
					+ "?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getBasicAddress());
			pstmt.setString(6, dto.getDetailAddress());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getLogin(AppTestDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "select no from join where id = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("no");
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public AppTestDTO getSelectOne(int no) {
		getConn();
		AppTestDTO dto = new AppTestDTO();
		try {
			String sql = "SELECT * FROM join WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setName(rs.getString("name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
}
