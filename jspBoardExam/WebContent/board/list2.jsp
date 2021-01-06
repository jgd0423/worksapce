<%@page import="java.util.Optional"%>
<%@page import="java.net.Inet4Address"%>
<%@page import="board.model.dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
String referer = request.getHeader("REFERER");
String ip = Inet4Address.getLocalHost().getHostAddress();
BoardDAO dao = new BoardDAO();
String pageNum_ = Optional.ofNullable(request.getParameter("page")).orElse("1");
final int ONE_PAGE_ROWS = 10;
final int MAX_PAGING_WIDTH = 10;
int allRowsCount = dao.getAllRowsCount();
int maxPagesCount = (int) Math.ceil((double) allRowsCount / ONE_PAGE_ROWS);
int pageNum = Integer.parseInt(pageNum_);
int pagingLoopNum = (int) Math.ceil((double)pageNum / MAX_PAGING_WIDTH) - 1;
int pagingStartNum = pagingLoopNum * MAX_PAGING_WIDTH + 1;
int pagingEndNum = pagingStartNum + MAX_PAGING_WIDTH - 1;
if (pagingEndNum > maxPagesCount) {
	pagingEndNum = maxPagesCount;
}
ArrayList<BoardDTO> boardList = dao.getPagingList(ONE_PAGE_ROWS, pageNum);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
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

<h2>게시글 목록</h2>

<form name="">
	<div style="height:300px;">
		<table border="1" width="1500" align="center">
			<tr align="center">
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>등록일</td>
				<td>조회수</td>
			</tr>
			<% for (int i = 0; i < boardList.size(); i++) { %>
				<% BoardDTO dto = boardList.get(i); %>
				<tr>
					<td><%=dto.getNo() %></td>
					<% if (dto.getRe_step() > 1) { %>
						<% String padding = (20 * (dto.getRe_step() - 1)) + "px"; %>
						<% String re = ""; %>
						<% for (int j = 0; j < dto.getRe_step() - 1; j++) { %>
							<% re += "Re:"; %>
						<% } %>
						<td>
							<a href="#" onclick="view('<%=dto.getNo()%>');" style="padding-left:<%=padding%>">
								<%=re %> <%=dto.getSubject() %>
							</a>
						</td>
					<% } else { %>
						<td>
							<a href="#" onclick="view('<%=dto.getNo()%>');"><%=dto.getSubject() %></a>
						</td>
					<% } %>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getRegi_date() %></td>	
					<td><%=dto.getHit() %></td>
				</tr>
			<% } %>
			<% if (boardList.size() == 0) { %>
				<tr>
					<td colspan="5" height="250px" align="center">글이 없습니다.</td>
				</tr>
			<% } %>
		</table>
	<br>
	<br>
		<div align="center">
			<a href="list2.jsp?page=1"><<</a>
			<% if (pageNum - 1 <= 0) { %>
				<a href="list2.jsp?page=<%=pageNum%>"><</a>
			<% } else { %>
				<a href="list2.jsp?page=<%=pageNum - 1%>"><</a>
			<% } %>
			<% for (int i = pagingStartNum ; i <= pagingEndNum; i++) { %>
				<% if (pageNum == i) { %>
					<%=i %>
				<% } else { %>
					<a href="list2.jsp?page=<%=i %>"><%=i %></a>
				<% } %>
			<% } %>
			<% if (pageNum + 1 >= maxPagesCount) { %>
				<a href="list2.jsp?page=<%=maxPagesCount%>">></a>
			<% } else { %>
				<a href="list2.jsp?page=<%=pageNum + 1%>">></a>
			<% } %>
			<a href="list2.jsp?page=<%=maxPagesCount%>">>></a>
		</div>
	<button type="button" onclick="goToWrite();">글쓰기</button>
	</div>
</form>

<script>
function view(no) {
	location.href='view.jsp?no=' + no;
}
function goToWrite() {
	location.href='write.jsp';
}
</script>

</body>
</html>