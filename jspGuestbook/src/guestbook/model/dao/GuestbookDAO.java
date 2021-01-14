package guestbook.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;
import db.DbImplOracle;
import guestbook.model.dto.GuestbookDTO;

public class GuestbookDAO {
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
	
	public int setInsert(GuestbookDTO dto) {
		getConn();
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
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<GuestbookDTO> getSelectAll() {
		getConn();
		ArrayList<GuestbookDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM guestbook ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestbookDTO dto = new GuestbookDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setContent(rs.getString("content"));
				dto.setWrite_date(rs.getDate("write_date"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public GuestbookDTO getSelectOne(int no) {
		getConn();
		GuestbookDTO dto = new GuestbookDTO();
		try {
			String sql = "SELECT * FROM guestbook WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setContent(rs.getString("content"));
				dto.setWrite_date(rs.getDate("write_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(GuestbookDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "UPDATE guestbook SET "
					+ "email = ?, "
					+ "passwd = ?, "
					+ "content = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return result;
	}
	
	public int setDelete(GuestbookDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM guestbook WHERE no = ? ";
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
