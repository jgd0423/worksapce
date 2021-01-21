package model.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;
import db.DbExample;
import db.DbImplOracle;
import model.member.dto.MemberDTO;

public class MemberDAO {
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
	
	public MemberDTO getLogin(MemberDTO dto) {
		conn = getConn();
		MemberDTO resultDto = new MemberDTO();
		try {
			String sql = "SELECT * FROM member WHERE id = ? AND passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				resultDto.setId(rs.getString("id"));
				resultDto.setPasswd(rs.getString("passwd"));
				resultDto.setChangeDate(rs.getDate("changeDate"));
			}
		} catch(Exception e) {
			System.out.println("-- 오라클 접속 실패 --");
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return resultDto;
	}

	public int setUpdate(MemberDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE member SET "
					+ "passwd = ? "
					+ "WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getId());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}
}
