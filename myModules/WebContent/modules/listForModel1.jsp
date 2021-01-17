<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

// dao 생성

// ArrayList<DTO> list = dao.getSelectAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>

<h1>목록</h1>

<table border="1">
	<tr>
		<!-- column 숫자만큼 td 복사 -->
		<td></td>
	</tr>
	<% //for (DTO dto : list) { %>
		<tr>
			<td><%= %></td>
		</tr>
	<% //} %>
</table>

<script>
function view(no) {
	location.href='view.jsp?no=' + no;
}
</script>

</body>
</html>