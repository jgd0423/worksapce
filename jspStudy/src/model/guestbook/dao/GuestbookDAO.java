package model.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.guestbook.dto.GuestbookDTO;

public class GuestbookDAO {
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
	
	public int setInsert(GuestbookDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO guestbook VALUES (seq_guestbook.NEXTVAL, "
					+ "?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPasswd());
			pstmt.setString(4, dto.getContent());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public ArrayList<GuestbookDTO> getSelectAll() {
		conn = getConn();
		ArrayList<GuestbookDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM guestbook ORDER BY no DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setEmail(rs.getString("email"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getDate("regiDate"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return arrayList;
	}

	public int getAllRowsCount() {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM guestbook WHERE no > 0";
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
	

	public ArrayList<GuestbookDTO> getPagingList(int startNum, int endNum) {
		conn = getConn();
		ArrayList<GuestbookDTO> list = new ArrayList<>();
		try {
			String basic_sql = ""; 
			basic_sql += "SELECT * FROM guestbook WHERE no > 0";	
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
				GuestbookDTO dto = new GuestbookDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getDate("regiDate"));
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
