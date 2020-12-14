package test2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.Db;

public class GradesDAO {
	// Field
	Connection conn = Db.getConn();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Constructor
	public GradesDAO() {}
	
	// Method
	public void getConnClose() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) { pstmt.close(); }
			if (conn != null) { conn.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int setInsert(GradesDTO dto) {
		int result = 0;
		try {
			String sql = "insert into scores values " + 
					"(seq_scores.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getHakyun());
			pstmt.setString(2, dto.getExamType());
			pstmt.setInt(3, dto.getKor());
			pstmt.setInt(4, dto.getEng());
			pstmt.setInt(5, dto.getMat());
			pstmt.setInt(6, dto.getSci());
			pstmt.setInt(7, dto.getHis());
			pstmt.setInt(8, dto.getTot());
			pstmt.setDouble(9, dto.getAvg());
			pstmt.setString(10, dto.getSid());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<GradesDTO> getListAll() {
		ArrayList<GradesDTO> gradesList = new ArrayList<>();
		try {
			String sql = "select * from scores order by sid asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GradesDTO dto = new GradesDTO();
				dto.setHakyun(rs.getString("hakyun"));
				dto.setExamType(rs.getString("examType"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setSci(rs.getInt("sci"));
				dto.setHis(rs.getInt("his"));
				dto.setTot(rs.getInt("tot"));
				dto.setAvg(rs.getDouble("avg"));
				dto.setSid(rs.getString("sid"));
				gradesList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return gradesList;
	}
}
