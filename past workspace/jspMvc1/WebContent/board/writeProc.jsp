<%@page import="board.BoardDAO"%>
<%@ page import="board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="board.BoardDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%
String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
BoardDAO dao = new BoardDAO();
int num;
int refNo;
int stepNo;
int levelNo;
int hit = 0;
String isDelete = "0";

if (no > 0) { // 답변글
	BoardDTO dbDto = dao.getSelectOne(no);
	num = dao.getMaxValue("num") + 1;
	refNo = dbDto.getRefNo();
	stepNo = dbDto.getStepNo() + 1;
	levelNo = dbDto.getLevelNo() + 1;
	dao.setUpdateReLevel(dbDto);
} else { // 새글
	num = dao.getMaxValue("num") + 1;
	refNo = dao.getMaxValue("refNo") + 1;
	stepNo = 1;
	levelNo = 1;
}


%>

<jsp:setProperty property="num" name="dto" value="<%=num %>"/>
<jsp:setProperty property="refNo" name="dto" value="<%=refNo %>"/>
<jsp:setProperty property="stepNo" name="dto" value="<%=stepNo %>"/>
<jsp:setProperty property="levelNo" name="dto" value="<%=levelNo %>"/>
<jsp:setProperty property="hit" name="dto" value="<%=hit %>"/>
<jsp:setProperty property="isDelete" name="dto" value="<%=isDelete %>"/>

<%


int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
}

%>