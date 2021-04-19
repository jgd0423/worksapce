<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="-1"/><!-- 0 -->
<meta http-equiv="Pragma" content="no-cache"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${path }<br>
파일이 업로드 되었습니다.<br>
originalFileName : ${map.originalFileName}<br>
newFileName : ${map.newFileName}<br>
newFileSize : ${map.newFileSize}<br>
newFileType : ${map.newFileType}<br>
mimeType : ${map.mimeType}<br>
failCounter : ${map.failCounter}<br>

<img src="" id="imsi">

<script>
	function toggle_img() {
		var imgName = "/spring01/attach/test_img/${map.newFileName}";
		//alert(imgName);
		document.getElementById("imsi").src = imgName;
	}
	
	//toggle_img();
</script>


<c:choose>
	<c:when test="${map.mimeType == 'image/jpeg' || dto.mimeType == 'image/png' || dto.mimeType == 'image/gif'}">
		<img src="${path }/attach/test_img/${map.newFileName}" width="100px" height="100px">
	</c:when>
	<c:otherwise>
		이미지아님!!!
	</c:otherwise>
</c:choose>	
			
			
			
			
			
</body>
</html>

