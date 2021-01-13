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
<title>삭제</title>
</head>
<body>

<h1>삭제</h1>
<!-- 물음표에 원하는 줄 수 넣고 emmet -->
form[name="deleteForm"]>input[type="hidden" name="no" value="<%=dto.getNo() %>"]+table[border="1" width="600"]>(tr>td+td{<%=dto. %>})*?^a[href="#" onclick="deleteInfo();"]{[삭제하기]}

<script>
function deleteInfo() {
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = "post";
		document.deleteForm.action = "deleteProc.jsp";
		document.deleteForm.submit();		
	}
}
</script>

</body>
</html>