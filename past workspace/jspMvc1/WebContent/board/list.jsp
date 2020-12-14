<%@page import="board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

BoardDAO dao = new BoardDAO();
ArrayList<BoardDTO> list = dao.getSelectAll();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

<h2>게시글 목록</h2>

<form name="">
	<div style="height:300px;">
		<table border="1" width="600">
			<tr align="center">
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>등록일</td>
				<td>조회수</td>
			</tr>
			<% for (int i = 0; i < list.size(); i++) { %>
				<% BoardDTO dto = list.get(i); %>
				<tr>
					<td><%=dto.getNo() %></td>
					<% if (dto.getStepNo() > 1) { %>
						<% String padding = (20 * (dto.getStepNo() - 1)) + "px"; %>
						<% String re = ""; %>
						<% for (int j = 0; j < dto.getStepNo() - 1; j++) { %>
							<% re += "Re:"; %>
						<% } %>
						<td>
							<a href="#" onclick="view('<%=dto.getNo()%>');" style="padding-left:<%=padding%>">
								<%=re %> <%=dto.getSubject() %>
							</a>
						</td>
					<% } else { %>
						<td><a href="#" onclick="view('<%=dto.getNo()%>');"><%=dto.getSubject() %></a></td>
					<% } %>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getRegiDate() %></td>	
					<td><%=dto.getHit() %></td>
				</tr>
			<% } %>
			<% if (list.size() == 0) { %>
				<tr>
					<td colspan="5" height="250px" align="center">글이 없습니다.</td>
				</tr>
			<% } %>
		</table>
	<br>
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