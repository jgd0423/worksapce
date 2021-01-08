<%@page import="java.util.ArrayList"%>
<%@page import="java.net.Inet4Address"%>
<%@page import="board.model.dto.BoardDTO"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String referer = request.getHeader("REFERER");
String ip = Inet4Address.getLocalHost().getHostAddress();

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
BoardDAO dao = new BoardDAO();
dao.setUpdateHit(no);
BoardDTO dto = dao.getSelectOne(no);

int[] backAndForthNoAndRnum = dao.getNearByCurrentPostAndRnum(dto);
BoardDTO preDto = dao.getSelectOne(backAndForthNoAndRnum[0]);
BoardDTO nextDto = dao.getSelectOne(backAndForthNoAndRnum[1]);

int currentPostRnum = dao.getCurrentPostRnum(dto);
int maxRnum = backAndForthNoAndRnum[2];

System.out.println("currentPostRnum : " + currentPostRnum);
System.out.println("preDtoRnum : " + dao.getCurrentPostRnum(preDto));
System.out.println("nextDtoRnum : " + dao.getCurrentPostRnum(nextDto));
System.out.println("maxRnum  : " + maxRnum);



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
</head>
<body>

<%=referer %><br>
클라이언트ip <%=request.getRemoteAddr() %><br>
요청URI <%=request.getRequestURI() %><br>
컨텍스트경로 <%=request.getContextPath() %><br>
서버이름 <%=request.getServerName() %><br>
서버포트 <%=request.getServerPort() %><br><br>
requested URL: <%=request.getRequestURL() %><br>
requested Info: <%=request.getRequestURI() %><br>

<h2>게시글 보기</h2>

<table border="1" width="600">
	<tr>
		<td width="100px">작성자</td>
		<td><%=dto.getWriter() %></td>
	</tr>
	<tr>
		<td>이메일 : </td>
		<td><%=dto.getEmail() %></td>
	</tr>
	<tr>
		<td>제목 : </td>
		<td><%=dto.getSubject() %></td>
	</tr>
	<tr>
		<td>내용 : </td>
		<td><%=dto.getContent() %></td>
	</tr>
	<tr>
		<td>조회수 : </td>
		<td><%=dto.getHit() %></td>
	</tr>
	<tr>
		<td>작성일 : </td>
		<td><%=dto.getRegi_date() %></td>
	</tr>
</table>

<a href="#" onclick="move('A', '<%=no%>');">[답변하기]</a>
&nbsp;&nbsp;
<a href="#" onclick="move('M', '<%=no%>');">[수정하기]</a>
&nbsp;&nbsp;<br>

<% if (currentPostRnum == 1) { %>
	<a href="view.jsp?no=<%=preDto.getNo()%>">다음글: <%=preDto.getSubject() %></a><br>
<% } else { %>
	<a href="view.jsp?no=<%=preDto.getNo()%>">이전글: <%=preDto.getSubject() %></a><br>
<% } %>

<% if (currentPostRnum == maxRnum) { %>
<% } else { %>
	<% if (currentPostRnum == 1) { %>
	<% } else { %>
	<a href="view.jsp?no=<%=nextDto.getNo()%>">다음글: <%=nextDto.getSubject() %></a><br>
	<% } %>
<% } %>


<%out.println("<br>isHaveChild : " + dao.isHaveChild(dto) + "<br>"); %>

<% if (!dao.isHaveChild(dto)) { %>
	<a href="#" onclick="move('D', '<%=no%>');">[삭제하기]</a>
<% } %>



<script>
function move(value1, value2) {
	if (value1 === 'A') {
		location.href='answer.jsp?no=' + value2;
	} else if (value1 === 'M') {
		location.href='modify.jsp?no=' + value2;
	} else if (value1 === 'D') {
		location.href='delete.jsp?no=' + value2;
	}
}
</script>

</body>
</html>