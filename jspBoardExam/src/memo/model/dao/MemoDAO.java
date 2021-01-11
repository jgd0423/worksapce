package memo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;
import db.DbImplOracle;
import memo.model.dto.MemoDTO;

public class MemoDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	
	public void getConn() {
		Db db = new DbImplOracle();
		conn = db.dbConn();
	}
	
	public void getConnClose() {
		Db db = new DbImplOracle();
		db.dbConnClose();
	}
	
	public int setInsert(MemoDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO memo VALUES (seq_memo.NEXTVAL, "
					+ "?, ?, ?, "
					+ "SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int getMaxValue(String columnName) {
		getConn();
		int result = 0;
		try {
			String sql = "SELECT NVL(MAX(" + columnName + "), 0) FROM memo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<MemoDTO> getSelectAll() {
		getConn();
		ArrayList<MemoDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM memo ORDER BY no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public MemoDTO getSelectOne(int no) {
		getConn();
		MemoDTO dto = new MemoDTO();
		try {
			String sql = "SELECT * FROM memo WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(MemoDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "UPDATE memo SET content = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(2, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return result;
	}
	
	public int setDelete(MemoDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM memo WHERE no = ? ";
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
