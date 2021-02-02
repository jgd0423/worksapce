package model.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.memo.dto.MemoDTO;

public class MemoDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	public int setInsert(MemoDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO memo VALUES (seq_memo.NEXTVAL, "
					+ "?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public ArrayList<MemoDTO> getSelectAll() {
		conn = getConn();
		ArrayList<MemoDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM memo ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return arrayList;
	}
	
	public int setDelete(MemoDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM memo WHERE no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int getAllRowsCount() {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM memo WHERE no > 0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				allRowsCount = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return allRowsCount;
	}

	public ArrayList<MemoDTO> getPagingList(int startNum, int endNum) {
		conn = getConn();
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String basic_sql = ""; 
			basic_sql += "SELECT * FROM memo WHERE no > 0";	
			basic_sql += " ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM ";
			sql += "(SELECT ROWNUM Rnum, a.* FROM ";
			sql += "(" + basic_sql + ") a) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}
}
