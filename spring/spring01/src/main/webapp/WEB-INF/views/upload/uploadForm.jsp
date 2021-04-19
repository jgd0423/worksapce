<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT"> 
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache"> 
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 
<jsp:include page="../include/inc_menu.jsp" />
--%>
<%@include file="../include/inc_menu.jsp"%>

<!-- 
enctype="multipart/form-data" 파일업로드 필수 옵션
enctype="application/x-www-form-urlencoded" 기본 옵션
-->
<!-- target="iframe의 name" => iframe으로 전송 -->

<!--  
실제 주소 입력해보면 잘 나오는데, iframe에서는 잘안나오네..
<img src="/spring01/attach/test_img/sss.jpeg"><hr>
<img src="/spring01/attach/test_img/ttt.jpeg"><hr>
<img src="/spring01/attach/test_img/uuu.png"><hr>
-->


${path }<br>
<form id="form1" action="${path }/upload/uploadForm" method="post" enctype="multipart/form-data" target="iframe01">
<input type="file" name="file"><!-- 여기 name="file"과 Controller 의 MultipartFile file 이 부분의 이름이 같아야 한다. 여기서는 file 로 사용한다. -->
<input type="submit" value="업로드">
</form>

<iframe src="about:blank" name="iframe01" style="width: 500px; height: 300px; border: 1px solid black;"></iframe>

</body>
</html>