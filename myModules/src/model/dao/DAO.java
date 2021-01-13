package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;
import db.DbImplOracle;
import model.dto.DTO;

public class DAO {
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
	
	public int setInsert(DTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO tableName VALUES (seqName.NEXTVAL, "
					+ "?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, dto.getName());   // example
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<DTO> getSelectAll() {
		getConn();
		ArrayList<DTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM tableName ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DTO dto = new DTO();
				//dto.setNo(rs.getInt("no"));   // example
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public DTO getSelectOne(int no) {
		getConn();
		DTO dto = new DTO();
		try {
			String sql = "SELECT * FROM tableName WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//dto.setNo(rs.getInt("no"));   // example
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(DTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "UPDATE tableName SET "
					+ "columnName1 = ?, "
					+ "columnName2 = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, dto.getQuestion());   // example

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return result;
	}
	
	public int setDelete(DTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM tableName WHERE no = ? ";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, dto.getNo());   // example
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
}
