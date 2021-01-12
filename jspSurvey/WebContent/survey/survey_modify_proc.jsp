<%@page import="survey.model.dto.SurveyDTO"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

SurveyDAO dao = new SurveyDAO();
SurveyDTO dto = new SurveyDTO();

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);

String question = request.getParameter("question");
String select1 = request.getParameter("select1");
String select2 = request.getParameter("select2");
String select3 = request.getParameter("select3");
String select4 = request.getParameter("select4");
String status = request.getParameter("status");

dto.setNo(no);
dto.setQuestion(question);
dto.setSelect1(select1);
dto.setSelect2(select2);
dto.setSelect3(select3);
dto.setSelect4(select4);
dto.setStatus(status);

int result = dao.setUpdate(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 수정되었습니다.')");
	out.println("location.href='survey_list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("history.back();");
	out.println("</script>");
}

%>