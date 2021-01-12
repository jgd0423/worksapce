package survey.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import answer.model.dto.AnswerDTO;
import db.Db;
import db.DbImplOracle;
import survey.model.dto.SurveyDTO;

public class SurveyDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConn() {
		Db db = new DbImplOracle();
		conn = db.dbConn();
	}
	
	public void getConnClose() {
		Db db = new DbImplOracle();
		db.dbConnClose();
	}
	
	public int setInsert(SurveyDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO survey VALUES (seq_survey.NEXTVAL, "
					+ "?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQuestion());
			pstmt.setString(2, dto.getSelect1());
			pstmt.setString(3, dto.getSelect2());
			pstmt.setString(4, dto.getSelect3());
			pstmt.setString(5, dto.getSelect4());
			pstmt.setString(6, dto.getStatus());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public int setInsertAnswer(AnswerDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO survey_answer VALUES (seq_answer.NEXTVAL, "
					+ "?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getSurvey_no());
			pstmt.setInt(2, dto.getSurvey_answer());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return result;
	}
	
	public ArrayList<SurveyDTO> getSelectAll() {
		getConn();
		ArrayList<SurveyDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM survey ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setSelect1(rs.getString("select1"));
				dto.setSelect2(rs.getString("select2"));
				dto.setSelect3(rs.getString("select3"));
				dto.setSelect4(rs.getString("select4"));
				dto.setStatus(rs.getString("status"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public ArrayList<SurveyDTO> getSelectStatusTrue() {
		getConn();
		ArrayList<SurveyDTO> arrayList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM survey WHERE status = '1' ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setSelect1(rs.getString("select1"));
				dto.setSelect2(rs.getString("select2"));
				dto.setSelect3(rs.getString("select3"));
				dto.setSelect4(rs.getString("select4"));
				dto.setStatus(rs.getString("status"));
				arrayList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return arrayList;
	}
	
	public SurveyDTO getSelectOne(int no) {
		getConn();
		SurveyDTO dto = new SurveyDTO();
		try {
			String sql = "SELECT * FROM survey WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setQuestion(rs.getString("question"));
				dto.setSelect1(rs.getString("select1"));
				dto.setSelect2(rs.getString("select2"));
				dto.setSelect3(rs.getString("select3"));
				dto.setSelect4(rs.getString("select4"));
				dto.setStatus(rs.getString("status"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return dto;
	}
	
	public int setUpdate(SurveyDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "UPDATE survey SET "
					+ "question = ?, "
					+ "select1 = ?, "
					+ "select2 = ?, "
					+ "select3 = ?, "
					+ "select4 = ?, "
					+ "status = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQuestion());
			pstmt.setString(2, dto.getSelect1());
			pstmt.setString(3, dto.getSelect2());
			pstmt.setString(4, dto.getSelect3());
			pstmt.setString(5, dto.getSelect4());
			pstmt.setString(6, dto.getStatus());
			pstmt.setInt(7, dto.getNo());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return result;
	}
	
	public int setDelete(SurveyDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "DELETE FROM survey WHERE no = ? ";
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
	
	public ArrayList<Integer> getSurveyNoAnswersArrayList(int survey_no) {
		getConn();
		ArrayList<Integer> answerList = new ArrayList<>();
		try {
			String sql = "SELECT "
					+ "survey_no, "
					+ "(SELECT COUNT(survey_answer) FROM survey_answer WHERE survey_no = ? AND survey_answer = '1') count_of_1, "
					+ "(SELECT COUNT(survey_answer) FROM survey_answer WHERE survey_no = ? AND survey_answer = '2') count_of_2, "
					+ "(SELECT COUNT(survey_answer) FROM survey_answer WHERE survey_no = ? AND survey_answer = '3') count_of_3, "
					+ "(SELECT COUNT(survey_answer) FROM survey_answer WHERE survey_no = ? AND survey_answer = '4') count_of_4 "
					+ "FROM survey_answer WHERE survey_no = ? GROUP BY survey_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, survey_no);
			pstmt.setInt(2, survey_no);
			pstmt.setInt(3, survey_no);
			pstmt.setInt(4, survey_no);
			pstmt.setInt(5, survey_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				answerList.add(rs.getInt("count_of_1"));
				answerList.add(rs.getInt("count_of_2"));
				answerList.add(rs.getInt("count_of_3"));
				answerList.add(rs.getInt("count_of_4"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		
		return answerList;
	}
	
	public ArrayList<Integer> getSurveyNos() {
		getConn();
		ArrayList<Integer> surveyNoList = new ArrayList<>();
		try {
			String sql = "SELECT distinct(survey_no) FROM survey_answer";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				surveyNoList.add(rs.getInt(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose();
		}
		return surveyNoList;
	}
}
