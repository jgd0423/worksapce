<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
DAO dao = new DAO();
DTO dto = dao.getSelectOne(no);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>

<h1>상세보기</h1>
<!-- 물음표에 원하는 줄 수 넣고 emmet -->
table[border="1"]>(tr>td+td{<%=dto. %>})*?

<a href="#" onclick="move('M', '<%=no%>');">[수정하기]</a>
&nbsp;&nbsp;
<a href="#" onclick="move('D', '<%=no%>');">[삭제하기]</a>

<script>
function move(whereToGo, no) {
	if (whereToGo === 'M') {
		location.href='modify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='delete.jsp?no=' + no;
	}
}
</script>

</body>
</html>