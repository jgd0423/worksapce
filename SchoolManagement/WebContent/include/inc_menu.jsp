<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:set var="under_bar_index" value="${fn:indexOf(menu_gubun, '_')}"></c:set>
<c:set var="menu_name" value="${fn:substring(menu_gubun, 0, under_bar_index)}"></c:set>

<table align="center">
	<tr>
		<td id="home" style="padding: 0px 20px; ${menu_name == 'index' ? 'background-color: silver;' : ''}">
			<a href="${path }">HOME</a>
		</td>
		<td id="student" style="padding: 0px 20px; ${menu_name == 'student' ? 'background-color: silver;' : ''}">
			<a href="${path }/student_servlet/list.do">학생관리</a>
		</td>
		<td id="exam" style="padding: 0px 20px; ${menu_name == 'exam' ? 'background-color: silver;' : ''}">
			<a href="${path }/exam_servlet/list.do">시험관리</a>
		</td>
		<td id="search" style="padding: 0px 20px; ${menu_name == 'score' ? 'background-color: silver;' : ''}">
			<a href="${path }/score_servlet/list.do">성적관리(학생별 성적검색)</a>
		</td>
	</tr>
</table>