package survey.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;

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

	public int getAllRowsCount(String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check) {
		conn = getConn();
		int allRowsCount = 0;
		try {
			if (search_date_start.length() > 0 && search_date_end.length() > 0) {
				search_date_start = search_date_start + " 00:00:00.0";
				search_date_end = search_date_end + " 23:59:59.999999";
				// java.sql.Timestamp start_date = java.sql.Timestamp.valueOf(search_date_start);
				// java.sql.Timestamp last_date = java.sql.Timestamp.valueOf(search_date_end);
			}
			
			String sql = "SELECT COUNT(*) FROM " + tableName01 + " WHERE no > 0 ";
			
			if (list_gubun.equals("ing")) {
				sql += "AND (CURRENT_TIMESTAMP BETWEEN start_date AND last_date) ";
			} else if (list_gubun.equals("end")) {
				sql += "AND CURRENT_TIMESTAMP > last_date ";
			}
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				sql += "AND " + search_option + " LIKE ? ";
			}
			

			if (search_date_start.length() > 0 && search_date_end.length() > 0 && search_date_check.equals("O")) {
				sql += "AND (start_date >= TO_TIMESTAMP(?) AND last_date <= TO_TIMESTAMP(?)) ";
			}
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				pstmt.setString(++pstmtNum, "%" + search_data + "%");
			}

			if (search_date_start.length() > 0 && search_date_end.length() > 0 && search_date_check.equals("O")) {
				pstmt.setString(++pstmtNum, search_date_start);
				pstmt.setString(++pstmtNum, search_date_end);
			}
			
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

	public ArrayList<SurveyDTO> getPagingList(int startNum, int endNum, String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check) {
		conn = getConn();
		ArrayList<SurveyDTO> list = new ArrayList<>();
		try {
			String basic_sql = "";
			basic_sql += "SELECT t1.*, ";
			basic_sql += "(SELECT COUNT(*) FROM " + tableName02 + " t2 WHERE t2.no = t1.no) survey_counter ";
			basic_sql += "FROM " + tableName01 + " t1 WHERE no > 0 ";
			
			if (list_gubun.equals("ing")) {
				basic_sql += "AND (CURRENT_TIMESTAMP BETWEEN start_date AND last_date) ";
			} else if (list_gubun.equals("end")) {
				basic_sql += "AND CURRENT_TIMESTAMP > last_date ";
			}
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				basic_sql += "AND " + search_option + " LIKE ? ";
			}
			

			if (search_date_start.length() > 0 && search_date_end.length() > 0 && search_date_check.equals("O")) {
				basic_sql += "AND (start_date >= TO_TIMESTAMP(?) AND last_date <= TO_TIMESTAMP(?)) ";
			}				

			basic_sql += "ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM ";
			sql += "(SELECT ROWNUM Rnum, a.* FROM ";
			sql += "(" + basic_sql + ") a) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				pstmt.setString(++pstmtNum, "%" + search_data + "%");
			}
			

			if (search_date_start.length() > 0 && search_date_end.length() > 0 && search_date_check.equals("O")) {
				pstmt.setString(++pstmtNum, search_date_start);
				pstmt.setString(++pstmtNum, search_date_end);
			}				

			pstmt.setInt(++pstmtNum, startNum);
			pstmt.setInt(++pstmtNum, endNum);
			
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
				dto.setSurvey_counter(rs.getInt("survey_counter"));
				list.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public SurveyDTO getSelectOne(int no) {
		conn = getConn();
		SurveyDTO dto = new SurveyDTO();
		try {
			String sql = "SELECT * FROM survey WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int setInsertAnswer(SurveyAnswerDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + tableName02
					+ " VALUES (seq_survey_answer.NEXTVAL, "
					+ "?, ?, CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setInt(2, dto.getAnswer());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public SurveyAnswerDTO getSelectOneResult(int no) {
		conn = getConn();
		SurveyAnswerDTO dto = new SurveyAnswerDTO();
		try {
			String sql = "SELECT * FROM survey_answer WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setAnswer_no(rs.getInt("no"));
				dto.setNo(rs.getInt("no"));
				dto.setAnswer(rs.getInt("answer"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public ArrayList<Integer> getSurveyNoAnswers(int no) {
		getConn();
		ArrayList<Integer> answerList = new ArrayList<>();
		try {
			String sql = "SELECT no, "
					+ "(SELECT COUNT(answer) FROM survey_answer WHERE no = ? AND answer = '1') count_of_1, "
					+ "(SELECT COUNT(answer) FROM survey_answer WHERE no = ? AND answer = '2') count_of_2, "
					+ "(SELECT COUNT(answer) FROM survey_answer WHERE no = ? AND answer = '3') count_of_3, "
					+ "(SELECT COUNT(answer) FROM survey_answer WHERE no = ? AND answer = '4') count_of_4 "
					+ "FROM survey_answer WHERE no = ? GROUP BY no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, no);
			pstmt.setInt(3, no);
			pstmt.setInt(4, no);
			pstmt.setInt(5, no);
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
			getConnClose(rs, pstmt, conn);
		}
		
		return answerList;
	}

	public int setUpdateQuestion(SurveyDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "UPDATE " + tableName01 + " SET "
					+ "question = ?, "
					+ "ans1 = ?, "
					+ "ans2 = ?, "
					+ "ans3 = ?, "
					+ "ans4 = ?, "
					+ "status = ?, "
					+ "start_date = ?, "
					+ "last_date = ? "
					+ "WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQuestion());
			pstmt.setString(2, dto.getAns1());
			pstmt.setString(3, dto.getAns2());
			pstmt.setString(4, dto.getAns3());
			pstmt.setString(5, dto.getAns4());
			pstmt.setString(6, dto.getStatus());
			pstmt.setTimestamp(7, dto.getStart_date());
			pstmt.setTimestamp(8, dto.getLast_date());
			pstmt.setInt(9, dto.getNo());

			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		
		return result;
	}
}
