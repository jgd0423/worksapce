<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:set var="under_bar_index" value="${fn:indexOf(menu_gubun, '_')}"></c:set>
<c:set var="menu_name" value="${fn:substring(menu_gubun, 0, under_bar_index)}"></c:set>

<table border="0" align="center">
	<tr>
		<td colspan="15" align="right" style="padding: 5px 20px 10px;">
			<c:if test="${sessionScope.cookNo == null || sessionScope.cookNo == 0 }">
				<a href="${path }/member_servlet/login.do">로그인</a>
			</c:if>

			<c:if test="${sessionScope.cookNo != null && sessionScope.cookNo > 0 }">
				${sessionScope.cookName }님 환영합니다.
				<a href="${path }/member_servlet/modify.do?no=${sessionScope.cookNo}">[회원정보수정]</a>
				<a href="${path }/member_servlet/delete.do?no=${sessionScope.cookNo}">[회원탈퇴]</a>
				<a href="${path }/member_servlet/logout.do">[로그아웃]</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td id="home" style="padding: 0px 20px; ${menu_name == 'index' ? 'background-color: silver;' : ''}">
			<a href="${path }">HOME</a>
		</td>
		<td id="member" style="padding: 0px 20px; ${menu_name == 'member' ? 'background-color: silver;' : ''}">
			<a href="${path }/member_servlet/list.do">회원관리</a>
		</td>
		<td id="memo" style="padding: 0px 20px; ${menu_name == 'memo' ? 'background-color: silver;' : ''}">
			<a href="${path }/memo_servlet/write.do">메모장</a>
		</td>
		<td id="guestbook" style="padding: 0px 20px; ${menu_name == 'guestbook' ? 'background-color: silver;' : ''}">
			<a href="${path }/guestbook_servlet/list.do">방명록</a>
		</td>
		<td id="survey" style="padding: 0px 20px; ${menu_name == 'survey' ? 'background-color: silver;' : ''}">
			<a href="${path }/survey_servlet/index.do">설문조사(ajax)</a>
		</td>
		<td id="survey2" style="padding: 0px 20px; ${menu_name == 'survey2' ? 'background-color: silver;' : ''}">
			<a href="${path }/survey2_servlet/list.do">설문조사</a>
		</td>
		<td id="board" style="padding: 0px 20px; ${menu_name == 'board' ? 'background-color: silver;' : ''}">
			<a href="${path }/board_servlet/index.do">게시판(ajax)</a>
		</td>
		<td id="board2" style="padding: 0px 20px; ${menu_name == 'board2' ? 'background-color: silver;' : ''}">
			<a href="${path }/board2_servlet/list.do">게시판</a>
		</td>
		<td id="product" style="padding: 0px 20px; ${menu_name == 'product' ? 'background-color: silver;' : ''}">
			<a href="${path }/product_servlet/index.do">Mall(상품관리-ajax)</a>
		</td>
		<td id="mall" style="padding: 0px 20px; ${menu_name == 'mall' ? 'background-color: silver;' : ''}">
			<a href="${path }/mall_servlet/index.do">Mall(쇼핑몰-ajax)</a>
		</td>
		<td id="chart" style="padding: 0px 20px; ${menu_name == 'chart' ? 'background-color: silver;' : ''}">
			<a href="${path }/chart_servlet/index.do">Chart</a>
		</td>
		<td id="smtpEmail" style="padding: 0px 20px; ${menu_name == 'email' ? 'background-color: silver;' : ''}">
			<a href="${path }/email_servlet/index.do">Email</a>
		</td>
		<td style="padding: 0px 20px;">
			<a href="#">관리자</a>
		</td>
	</tr>
</table>


==> ${menu_gubun } / ${under_bar_index} / ${menu_name}<br>

<%-- <c:choose> --%>
<%-- 	<c:when test="${menu_name == 'index' }"> --%>
<!-- 		<script>$("#home").css("background-color", "silver");</script> -->
<%-- 	</c:when> --%>
<%-- 	<c:when test="${menu_name == 'member' }"> --%>
<!-- 		<script>$("#member").css("background-color", "silver");</script> -->
<%-- 	</c:when> --%>
<%-- 	<c:when test="${menu_name == 'memo' }"> --%>
<!-- 		<script>$("#memo").css("background-color", "silver");</script> -->
<%-- 	</c:when> --%>
<%-- </c:choose> --%>
