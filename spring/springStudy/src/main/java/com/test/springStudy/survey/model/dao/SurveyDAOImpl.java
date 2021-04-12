package com.test.springStudy.survey.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.springStudy.survey.model.dto.SurveyAnswerDTO;
import com.test.springStudy.survey.model.dto.SurveyDTO;

@Repository
public class SurveyDAOImpl implements SurveyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public int setInsertQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("survey.setInsertQuestion", map);
		return result;
	}

	@Override
	public int getAllRowsCount(String list_gubun, String search_option, String search_data, String search_date_start,
			String search_date_end, String search_date_check) {
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
		
		int result = sqlSession.selectOne("survey.getAllRowsCount", map);
		return result;
	}

	@Override
	public List<SurveyDTO> getPagingList(int startNum, int endNum, String list_gubun, String search_option,
			String search_data, String search_date_start, String search_date_end, String search_date_check) {
		Map<String, String> map = new HashMap<>();
		map.put("startNum", startNum + "");
		map.put("endNum", endNum + "");
		map.put("list_gubun", list_gubun);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_start", search_date_start);
		map.put("search_date_end", search_date_end);
		map.put("search_date_check", search_date_check);
		
		List<SurveyDTO> list = sqlSession.selectList("survey.getPagingList", map);
		return list;
	}

	@Override
	public SurveyDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		SurveyDTO result = sqlSession.selectOne("survey.getSelectOne", map);
		return result;
	}

	@Override
	public int setInsertAnswer(SurveyAnswerDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.insert("survey.setInsertAnswer", map);
		return result;
	}

	@Override
	public Map<String, Object> getSurveyNoAnswers(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		
		Map<String, Object> answersMap = sqlSession.selectOne("survey.getSurveyNoAnswers", map);
		return answersMap;
	}

	@Override
	public int setUpdateQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		int result = sqlSession.update("survey.setUpdateQuestion", map);
		return result;
	}

	@Override
	public int setDeleteQuestion(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		int result = sqlSession.delete("survey.setDeleteQuestion", map);
		return result;
	}
}
