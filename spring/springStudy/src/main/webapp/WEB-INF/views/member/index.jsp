<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div style="display: ">
	proc : <span id="span_proc"></span><br>
	pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
	no : <span id="span_no">${no }</span><br>
	search_option : <span id="span_search_option">${search_option }</span><br>
	search_data : <span id="span_search_data">${search_data }</span><br>
	path : <span id="span_path">${path }</span><br>
	menu_gubun : <span id="span_menu_gubun">${menu_gubun }</span><br>
	arg01 : <span id="span_arg01">${arg01 }</span><br>
	
	<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->
</div>
<div id="result" style="height: 100%; position: relative;"></div>

<script src='<c:url value="/resources/js/_member.js"/>'></script>