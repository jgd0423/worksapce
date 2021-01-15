package memo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;
import db.DbImplOracle;
import memo.model.dto.MemoDTO;

public class MemoDAO {
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
	
	public int setInsert(MemoDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO memo VALUES (seq_memo.NEXTVAL, "
					+ "?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
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
			String sql = "SELECT * FROM memo ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegi_date(rs.getDate("regi_date"));
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
	
	public int setUpdate(MemoDTO dto) {
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
	
	public int setDelete(MemoDTO dto) {
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
