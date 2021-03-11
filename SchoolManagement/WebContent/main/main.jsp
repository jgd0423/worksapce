<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학사관리</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="1" align="center" width="90%">
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
					<jsp:include page="../school/student/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'student_write' }">
					<jsp:include page="../school/student/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'student_list' }">
					<jsp:include page="../school/student/list.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'exam_write' }">
					<jsp:include page="../school/exam/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'exam_list' }">
					<jsp:include page="../school/exam/list.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'score_write' }">
					<jsp:include page="../school/score/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'score_list' }">
					<jsp:include page="../school/score/list.jsp" />
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