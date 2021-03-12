package school.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import school.model.dto.ExamDTO;
import school.model.dto.ScoreDTO;
import school.model.dto.StudentDTO;

public class ScoreDAO {
	// Field
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String SCORE = "score";
	final String STUDENT = "student";
	final String EXAM = "exam";
	
	// Method
	public Connection getConn() {
		conn = DbExample.getConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.getConnClose(rs, pstmt, conn);
	}
	
	public int setInsert(ScoreDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "INSERT INTO " + SCORE + " VALUES (seq_score.NEXTVAL, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getStudentId());
			pstmt.setInt(2, dto.getExamId());
			pstmt.setInt(3, dto.getKorean());
			pstmt.setInt(4, dto.getEnglish());
			pstmt.setInt(5, dto.getMath());
			pstmt.setInt(6, dto.getScience());
			pstmt.setInt(7, dto.getHistory());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getAllRowsCount(String search_option, String search_data) {
		conn = getConn();
		int allRowsCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM scoreView WHERE no > 0 ";
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("studentName") || search_option.equals("examName")) {
					sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("studentName_examName")) {
					sql += " and (studentName LIKE ? OR examName LIKE ?) ";
				}
			}
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("studentName") || search_option.equals("examName")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("studentName_examName")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
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

	public ArrayList<ScoreDTO> getPagingList(int startNum, int endNum, String search_option, String search_data) {
		conn = getConn();
		ArrayList<ScoreDTO> list = new ArrayList<>();
		try {
			String basic_sql = "SELECT * FROM scoreView WHERE no > 0 ";	
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("studentName") || search_option.equals("examName")) {
					basic_sql += " AND " + search_option + " LIKE ? ";
				} else if (search_option.equals("studentName_examName")) {
					basic_sql += " and (studentName LIKE ? OR examName LIKE ?) ";
				}
			}
			
			basic_sql += " ORDER BY no DESC";
			
			String sql = "";
			sql += "SELECT * FROM (SELECT A.*, Rownum Rnum FROM (" + basic_sql + ") A) ";
			sql += "WHERE Rnum >= ? AND Rnum <= ?";
			
			int pstmtNum = 0;
			pstmt = conn.prepareStatement(sql);		
			
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("studentName") || search_option.equals("examName")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				} else if (search_option.equals("studentName_examName")) {
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
					pstmt.setString(++pstmtNum, '%' + search_data + '%');
				}
			}
			
			pstmt.setInt(++pstmtNum, startNum);
			pstmt.setInt(++pstmtNum, endNum);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();
				dto.setNo(rs.getInt("no"));
				dto.setStudentId(rs.getString("studentId"));
				dto.setStudentName(rs.getString("studentName"));
				dto.setGrade(rs.getString("grade"));
				dto.setClasses(rs.getString("classes"));
				dto.setExamId(rs.getInt("examId"));
				dto.setExamName(rs.getString("examName"));
				dto.setKorean(rs.getInt("korean"));
				dto.setEnglish(rs.getInt("english"));
				dto.setMath(rs.getInt("math"));
				dto.setScience(rs.getInt("science"));
				dto.setHistory(rs.getInt("history"));
				dto.setTotalScore(rs.getInt("totalScore"));
				dto.setAverageScore(rs.getDouble("averageScore"));
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

	public ArrayList<String> getClassesList(String grade) {
		conn = getConn();
		ArrayList<String> classesList = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT(classes) FROM " + STUDENT + " WHERE grade = ? ORDER BY classes ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				classesList.add(rs.getString(1));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return classesList;
	}

	public ArrayList<StudentDTO> getIdNameList(String grade, String classes) {
		conn = getConn();
		ArrayList<StudentDTO> idNameList = new ArrayList<>();
		try {
			String sql = "SELECT id, name FROM " + STUDENT + " WHERE grade = ? AND classes = ? ORDER BY name ASC";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, classes);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				idNameList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return idNameList;
	}

	public ArrayList<ExamDTO> getExamList() {
		conn = getConn();
		ArrayList<ExamDTO> examList = new ArrayList<>();
		try {
			String sql = "SELECT no, name FROM " + EXAM + " ORDER BY no ASC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ExamDTO dto = new ExamDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				examList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			getConnClose(rs, pstmt, conn);
		}
		return examList;
	}
}
