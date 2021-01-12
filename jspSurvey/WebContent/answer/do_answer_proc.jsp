<%@page import="answer.model.dto.AnswerDTO"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

SurveyDAO dao = new SurveyDAO();
String[] noList = request.getParameterValues("survey_no");
ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();

for (int i = 0; i < noList.length; i++) {
	AnswerDTO dto = new AnswerDTO();
	String answer_ = request.getParameter("survey_answer_" + noList[i]);
	int answer = Integer.parseInt(answer_);
	dto.setSurvey_no(Integer.parseInt(noList[i]));
	dto.setSurvey_answer(answer);
	answerDTOList.add(dto);
}

for (AnswerDTO dto : answerDTOList) {
	dao.setInsertAnswer(dto);
}

response.sendRedirect("do_answer.jsp");

%>