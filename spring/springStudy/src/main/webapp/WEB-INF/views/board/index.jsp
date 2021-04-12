<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div style="display: none">
	proc : <span id="span_proc"></span><br>
	menu_gubun : ${menu_gubun }<br>
	menu_gubun_sub : ${menu_gubun_sub }<br>
	pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
	no : <span id="span_no">${no }</span><br>
	yearMonthDayMap : ${yearMonthDayMap }<br>
	search_option : <span id="span_search_option">${search_option }</span><br>
	search_data : <span id="span_search_data">${search_data }</span><br>
	ip : ${ip }<br>
	tbl : <span id="span_tbl">${tbl }</span><br>
	path : <span id="span_path">${path }</span><br>
	isUsingTable : <span id="span_isUsingTable">${isUsingTable }</span><br>
<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->
</div>

<div id="result" style="height: 100%;"></div>

<script src='<c:url value="/resources/js/_board.js"/>'></script>
