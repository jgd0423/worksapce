<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력</title>
</head>
<body>

<h1>입력</h1>

<!-- 물음표에 원하는 줄 수 넣고 emmet -->
form[name="writeForm"]>table[border="1"]>(tr>td+td>input[type="text" name=""])*?^a[href="#" onclick="inputInfo();"]{[등록하기]}


<script>
function inputInfo() {
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = 'post';
		document.writeForm.action = 'inputProc.jsp';
		document.writeForm.submit();		
	}
}
</script>

</body>
</html>