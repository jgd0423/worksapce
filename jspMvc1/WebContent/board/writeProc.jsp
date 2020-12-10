<%@page import="board.BoardDAO"%>
<%@ page import="board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="board.BoardDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%
BoardDAO dao = new BoardDAO();

int num = dao.getMaxNum() + 1;
int refNo = num;
int stepNo = 0;
int levelNo = 0;
int hit = 0;

%>

<jsp:setProperty property="num" name="dto" value="<%=num %>"/>
<jsp:setProperty property="refNo" name="dto" value="<%=refNo %>"/>
<jsp:setProperty property="stepNo" name="dto" value="<%=stepNo %>"/>
<jsp:setProperty property="levelNo" name="dto" value="<%=levelNo %>"/>
<jsp:setProperty property="hit" name="dto" value="<%=hit %>"/>

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