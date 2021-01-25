package model.survey.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import model.member.dto.MemberDTO;
import model.survey.dto.SurveyDTO;

public class SurveyDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String tableName01 = "survey";
	String tableName02 = "survey_answer";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	public int setInsertQuestion(SurveyDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + tableName01
					+ " VALUES ((SELECT NVL(MAX(no), 0) + 1 FROM " + tableName01 + "), "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, TO_TIMESTAMP(?), CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQuestion());
			pstmt.setString(2, dto.getAns1());
			pstmt.setString(3, dto.getAns2());
			pstmt.setString(4, dto.getAns3());
			pstmt.setString(5, dto.getAns4());
			pstmt.setString(6, dto.getStatus());
			pstmt.setTimestamp(7, dto.getStart_date());
			pstmt.setTimestamp(8, dto.getLast_date());
			
//			TO_TIMESTAMP(?)로 sql문에 써줘야됨
//			pstmt.setString(7, "2021-01-01 00:00:00.0");
//			pstmt.setString(8, "2021-01-01 23:59:59.9");
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
			String sql = "SELECT COUNT(*) FROM survey WHERE no > 0";
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

	public ArrayList<SurveyDTO> getPagingList(int startNum, int endNum) {
		conn = getConn();
		ArrayList<SurveyDTO> list = new ArrayList<>();
		try {
			String basic_sql = ""; 
			basic_sql += "SELECT * FROM survey WHERE no > 0";	
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
				SurveyDTO dto = new SurveyDTO();
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setAns1(rs.getString("ans1"));
				dto.setAns2(rs.getString("ans2"));
				dto.setAns3(rs.getString("ans3"));
				dto.setAns4(rs.getString("ans4"));
				dto.setStatus(rs.getString("status"));
				dto.setStart_date(rs.getTimestamp("start_date"));
				dto.setLast_date(rs.getTimestamp("last_date"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
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
