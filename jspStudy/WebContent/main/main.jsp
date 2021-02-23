<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Not Quite My Tempo</title>
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
				<c:when test="${menu_gubun == 'member_chuga' }">
					<jsp:include page="../member/chuga.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_login' }">
					<jsp:include page="../member/login.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_list' }">
					<jsp:include page="../member/list.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_view' }">
					<jsp:include page="../member/view.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_modify' }">
					<jsp:include page="../member/modify.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'member_delete' }">
					<jsp:include page="../member/delete.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'memo_write' }">
					<jsp:include page="../memo/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'guestbook_write' }">
					<jsp:include page="../guestbook/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'guestbook_list' }">
					<jsp:include page="../guestbook/list.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey_index' }">
					<jsp:include page="../survey/index.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey2_list' }">
					<jsp:include page="../survey2/list.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey2_write' }">
					<jsp:include page="../survey2/write.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey2_view' }">
					<jsp:include page="../survey2/view.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey2_result' }">
					<jsp:include page="../survey2/result.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'survey2_detailedList' }">
					<jsp:include page="../survey2/detailedList.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'board_index' }">
					<jsp:include page="../board/index.jsp" />
				</c:when>
				<c:when test="${menu_gubun == 'product_index' }">
					<jsp:include page="../shop/product/index.jsp" />
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