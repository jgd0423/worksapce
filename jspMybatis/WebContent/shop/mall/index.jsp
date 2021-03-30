<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<div style="display: none">
	proc : <span id="span_proc"></span><br>
	pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
	no : <span id="span_no">${no }</span><br>
	search_option : <span id="span_search_option">${search_option }</span><br>
	search_data : <span id="span_search_data">${search_data }</span><br>
	jumun_su : <span id="span_jumun_su"></span><br>
	path : <span id="span_path">${path }</span><br>
	sessionId : <span id="span_sessionId">${sessionId }</span><br>
	<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->
</div>

<div id="result" style="height: 100%;"></div>

<script type="text/javascript" src="${path }/shop/mall/_mall.js"></script>
