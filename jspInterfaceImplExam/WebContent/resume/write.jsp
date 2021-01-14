<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
// 개발 디렉토리
//String upload_path = "C:\\Users\\hkit\\Desktop\\jgd\\workspace\\jspInterfaceImplExam\\WebContent\\upolad\\img";


// 배포 디렉토리
String upload_path = application.getRealPath("/upload/img/");
out.println(upload_path);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이력서 등록</title>
</head>
<body>

<h2>이력서 등록</h2>

<form name="writeForm" method="post" encType="multipart/form-data">
사진 : <input type="file" name="pic" /><br>
이름 : <input type="text" name="name" /><br>

<hr>
기간1 : <input type="text" name="gigan" /><br>
학교명1 : <input type="text" name="school" /><br>
전공1 : <input type="text" name="jeongong" /><br>
<hr>
<br><br>
<input type="button" value="저장하기" onclick="save();" />
</form>

<script>
function save() {
	if (confirm('저장하시겠습니까?')) {
		writeForm.method = "post";
		writeForm.action = "writeProc.jsp";
		writeForm.submit();
	}
}

</script>

</body>
</html>