<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:set var="under_bar_index" value="${fn:indexOf(menu_gubun, '_')}"></c:set>
<c:set var="menu_name" value="${fn:substring(menu_gubun, 0, under_bar_index)}"></c:set>
<c:set var="menu_name_sub" value="${fn:substring(menu_gubun_sub, 0, fn:length(menu_gubun_sub) - 5)}"></c:set>

<table border="0" align="center">
	<tr>
		<td colspan="15" align="right" style="padding: 5px 20px 10px;">
			<c:if test="${sessionScope.cookNo == null || sessionScope.cookNo == 0 }">
				<a href="${path }/member/goLogin.do">로그인</a>
			</c:if>

			<c:if test="${sessionScope.cookNo != null && sessionScope.cookNo > 0 }">
				${sessionScope.cookName }님 환영합니다.
				<a href="${path }/member/goModify.do?no=${sessionScope.cookNo}">[회원정보수정]</a>
				<a href="${path }/member/goDelete.do?no=${sessionScope.cookNo}">[회원탈퇴]</a>
				<a href="${path }/member/logout.do">[로그아웃]</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td id="home" style="padding: 0px 20px; ${menu_name == 'index' ? 'background-color: silver;' : ''}">
			<a href="${path }">HOME</a>
		</td>
		<td id="member" style="padding: 0px 20px; ${menu_name == 'member' ? 'background-color: silver;' : ''}">
			<a href="${path }/member/index.do">회원관리</a>
		</td>
		<td id="memo" style="padding: 0px 20px; ${menu_name == 'memo' ? 'background-color: silver;' : ''}">
			<a href="${path }/memo/index.do">메모장</a>
		</td>
		<td id="guestbook" style="padding: 0px 20px; ${menu_name == 'guestbook' ? 'background-color: silver;' : ''}">
			<a href="${path }/guestbook/index.do">방명록</a>
		</td>
		<td id="survey" style="padding: 0px 20px; ${menu_name == 'survey' ? 'background-color: silver;' : ''}">
			<a href="${path }/survey/index.do">설문조사</a>
		</td>
		<td id="survey" style="padding: 0px 20px; ${menu_name == 'questionBank' ? 'background-color: silver;' : ''}">
			<a href="${path }/questionBank/index.do">문제은행</a>
		</td>
		<td id="board" style="padding: 0px 20px; ${menu_name_sub == 'free' ? 'background-color: silver;' : ''}">
			<a href="${path }/board/index.do">자유게시판</a>
		</td>
		<td id="codingboard" style="padding: 0px 20px; ${menu_name_sub == 'coding' ? 'background-color: silver;' : ''}">
			<a href="${path }/board/index.do?tbl=codingboard">코딩게시판</a>
		</td>
		<td id="product" style="padding: 0px 20px; ${menu_name == 'product' ? 'background-color: silver;' : ''}">
			<a href="${path }/product/index.do">Mall(상품관리)</a>
		</td>
		<td id="mall" style="padding: 0px 20px; ${menu_name == 'mall' ? 'background-color: silver;' : ''}">
			<a href="${path }/mall/index.do">Mall(쇼핑몰)</a>
		</td>
		<td id="chart" style="padding: 0px 20px; ${menu_name == 'chart' ? 'background-color: silver;' : ''}">
			<a href="${path }/chart/index.do">Cart Chart</a>
		</td>
		<td id="smtpEmail" style="padding: 0px 20px; ${menu_name == 'email' ? 'background-color: silver;' : ''}">
			<a href="${path }/email/index.do">Email</a>
		</td>
	</tr>
</table>
