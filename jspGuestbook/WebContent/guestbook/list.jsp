<%@page import="guestbook.model.dto.GuestbookDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="guestbook.model.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

// dao 생성
GuestbookDAO dao = new GuestbookDAO();

ArrayList<GuestbookDTO> list = dao.getSelectAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>

<h1>방명록</h1>

<form name="searchForm">
	<select name="searchField">
		<option value="">-선택-</option>
		<option value="writer">작성자</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="all">제목+내용</option>
	</select>

	<input type="text" name="searchData">
	<button type="button" onclick="search();">검색하기</button>
</form>
<br><br>

<button type="button" onclick="goToWrite();">글쓰기</button>
<p><%=list.size() %>개의 글이 있습니다.</p>
<% for (GuestbookDTO dto : list) { %>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><a href="#" onclick="view('<%=dto.getNo()%>');"><%=dto.getName() %></a></td>
			<td>날짜</td>
			<td><%=dto.getWrite_date() %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td colspan="3"><%=dto.getEmail() %></td>
		</tr>
		<tr>
			<td colspan="4"><%=dto.getContent() %></td>
		</tr>
	</table>
<% } %>

<script>
function view(no) {
	location.href='view.jsp?no=' + no;
}

function goToWrite() {
	location.href="write.jsp";
}
</script>

</body>
</html>