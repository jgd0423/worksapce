package survey.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;

public class SurveyDAO {
	// Method
	public int setInsertQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.setInsertQuestion", map);
		session.commit();
		session.close();
		
		return result;
	}

	public int getAllRowsCount(String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check) {
		if (search_date_start.length() > 0 && search_date_end.length() > 0) {
			search_date_start = search_date_start + " 00:00:00.0";
			search_date_end = search_date_end + " 23:59:59.999999";
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("list_gubun", list_gubun);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_start", search_date_start);
		map.put("search_date_end", search_date_end);
		map.put("search_date_check", search_date_check);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("survey.getAllRowsCount", map);
		session.close();
		return result;
	}

	public List<SurveyDTO> getPagingList(int startNum, int endNum, String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("list_gubun", list_gubun);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_start", search_date_start);
		map.put("search_date_end", search_date_end);
		map.put("search_date_check", search_date_check);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveyDTO> list = session.selectList("survey.getPagingList", map);
		session.close();
		return list;
	}
	
	public SurveyDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO result = session.selectOne("survey.getSelectOne", map);
		session.close();
		return result;
	}

	public int setInsertAnswer(SurveyAnswerDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.setInsertAnswer", map);
		session.commit();
		session.close();
		return result;
	}

//	public SurveyAnswerDTO getSelectOneResult(int no) {
//		conn = getConn();
//		SurveyAnswerDTO dto = new SurveyAnswerDTO();
//		try {
//			String sql = "SELECT * FROM survey_answer WHERE no = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, no);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				dto.setAnswer_no(rs.getInt("no"));
//				dto.setNo(rs.getInt("no"));
//				dto.setAnswer(rs.getInt("answer"));
//				dto.setRegi_date(rs.getTimestamp("regi_date"));
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			getConnClose(rs, pstmt, conn);
//		}
//		return dto;
//	}
	
	public Map<String, Object> getSurveyNoAnswers(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> answersMap = session.selectOne("survey.getSurveyNoAnswers", map);
		session.close();
		return answersMap;
	}

	public int setUpdateQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("survey.setUpdateQuestion", map);
		session.commit();
		session.close();
		
		return result;
	}
	
	public int setDeleteQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("survey.setDeleteQuestion", map);
		session.commit();
		session.close();
		
		return result;
	}
}
