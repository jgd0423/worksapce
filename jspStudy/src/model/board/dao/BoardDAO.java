package model.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbExample;
import model.board.dto.BoardDTO;

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
			String sql = "INSERT INTO "+ tableName01 +" VALUES (seq_board.NEXTVAL, "
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
}
