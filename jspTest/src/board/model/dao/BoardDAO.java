package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.model.dto.BoardDTO;
import db.DbExample;

public class BoardDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String BOARD = "board";
	
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
			String sql = "INSERT INTO " + BOARD + " VALUES (seq_board.NEXTVAL, "
					+ "?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
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
			String sql = "SELECT COUNT(*) FROM " + BOARD + " WHERE no > 0";
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
			basic_sql += "SELECT * FROM " + BOARD + " WHERE no > 0";	
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
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
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

	public BoardDTO getView(int no) {
		conn = getConn();
		BoardDTO dto = new BoardDTO();
		try {
			String sql = "SELECT * FROM " + BOARD + " WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setRegiDate(rs.getDate("regiDate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int setUpdate(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE " + BOARD + " SET "
					+ "writer = ?, "
					+ "subject = ?, "
					+ "content = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}

	public int setDelete(BoardDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM " + BOARD + " WHERE no = ? ";
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
	
	
}
