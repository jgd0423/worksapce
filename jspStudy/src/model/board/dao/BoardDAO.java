package model.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.board.dto.BoardDTO;
import model.guestbook.dto.GuestbookDTO;

public class BoardDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String tableName01 = "board";
	String tableName02 = "board_comment";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	
	public int setInsert(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + tableName01 + " VALUES (seq_board.NEXTVAL, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getTbl());
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getPasswd());
			pstmt.setInt(8, dto.getRefNo());
			pstmt.setInt(9, dto.getStepNo());
			pstmt.setInt(10, dto.getLevelNo());
			pstmt.setInt(11, dto.getParentNo());
			pstmt.setInt(12, dto.getHit());
			pstmt.setString(13, dto.getIp());
			pstmt.setInt(14, dto.getMemberNo());
			pstmt.setInt(15, dto.getNoticeNo());
			pstmt.setString(16, dto.getSecretGubun());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getMaxNum() {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(num), 0) FROM " + tableName01;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int getMaxRefNo() {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(refNo), 0) FROM " + tableName01;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getMaxNoticeNo(String tbl) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "SELECT NVL(MAX(noticeNo), 0) FROM " + tableName01 + " WHERE tbl = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tbl);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
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
			String sql = "SELECT COUNT(*) FROM " + tableName01 + " WHERE no > 0 ";
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
	
	public ArrayList<BoardDTO> getPagingList(int startNum, int endNum) {
		conn = getConn();
		ArrayList<BoardDTO> list = new ArrayList<>();
		try {
			String basic_sql = ""; 
			basic_sql += "SELECT * FROM " + tableName01 + " WHERE no > 0";	
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
				BoardDTO dto = new BoardDTO();
				dto.setSubject(rs.getString("subject"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegiDate(rs.getDate("regiDate"));
				dto.setHit(rs.getInt("hit"));
				dto.setIp(rs.getString("ip"));
				dto.setNum(rs.getInt("num"));
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setSecretGubun(rs.getString("secretGubun"));
				dto.setParentNo(rs.getInt("parentNo"));
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
