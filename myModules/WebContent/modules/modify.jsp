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
<title>수정</title>
</head>
<body>

<h1>수정</h1>
<!-- 물음표에 원하는 줄 수 넣고 emmet -->
form[name="modifyForm"]>input[type="hidden" name="no" value="<%=dto.getNo() %>"]+table[border="1" width="600"]>(tr>td+td>input[type="text" name="" value="<%=dto. %>"])*?^a[href="#" onclick="modifyInfo();"]{[수정하기]}

<script>
function modifyInfo() {
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "modifyProc.jsp";
		document.modifyForm.submit();		
	}
}
</script>

</body>
</html>