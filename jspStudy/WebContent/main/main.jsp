<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
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
				<c:when test="${menu_gubun == 'index1' }">
					<jsp:include page="./a.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'index2' }">
					<jsp:include page="./b.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="./c.jsp" />
				</c:otherwise>
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