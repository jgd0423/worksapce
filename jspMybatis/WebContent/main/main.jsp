<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<title>Mybatis Test</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="1" align="center">
	<tr>
		<td style="padding: 20px 20px;">
			<!-- header include action tag -->
			<jsp:include page="../include/inc_menu.jsp"></jsp:include>
			<!-- header -->
		</td>
	</tr>
	<tr>
		<td align="center" style="padding: 50px 50px;">
			<!-- section -->
			<c:choose>
				<c:when test="${menu_gubun == 'index' }">
					<jsp:include page="../main/main_sub.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_index' }">
					<jsp:include page="../member/index.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'memo_write' }">
					<jsp:include page="../memo/write.jsp" />
				</c:when>
			</c:choose>
			<!-- section -->
		</td>
	</tr>
	<tr>
		<td align="center" style="padding: 20px 20px;">
			<!-- footer include action tag -->
			<jsp:include page="../include/inc_bottom.jsp"></jsp:include>
			<!-- footer -->
		</td>
	</tr>
</table>

</body>
</html>