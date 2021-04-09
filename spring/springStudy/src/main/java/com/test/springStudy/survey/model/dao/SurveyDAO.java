package com.test.springStudy.survey.model.dao;

import java.util.List;
import java.util.Map;

import com.test.springStudy.survey.model.dto.SurveyAnswerDTO;
import com.test.springStudy.survey.model.dto.SurveyDTO;

public interface SurveyDAO {
	// Method
	public int setInsertQuestion(SurveyDTO dto);
	public int getAllRowsCount(String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check);
	public List<SurveyDTO> getPagingList(int startNum, int endNum, String list_gubun, String search_option, String search_data, String search_date_start, String search_date_end, String search_date_check);
	public SurveyDTO getSelectOne(int no);
	public int setInsertAnswer(SurveyAnswerDTO dto);
	public Map<String, Object> getSurveyNoAnswers(int no);
	public int setUpdateQuestion(SurveyDTO dto);
	public int setDeleteQuestion(SurveyDTO dto);
}
