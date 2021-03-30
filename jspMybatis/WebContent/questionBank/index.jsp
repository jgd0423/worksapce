<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div style="display: none">
	<input type="text" name="a" style="display: block;" /><br />
	proc : <span id="span_proc"></span><br>
	pageNumber : <span id="span_pageNumber">${pageNum }</span><br />
	no : <span id="span_no">${no }</span><br />
	search_option : <span id="span_search_option">${search_option }</span><br />
	search_data : <span id="span_search_data">${search_data }</span><br />
	search_date_check : <span id="span_search_date_check">${search_date_check }</span><br />
	search_date_start : <span id="span_search_date_start">${search_date_start }</span><br />
	search_date_end : <span id="span_search_date_end">${search_date_end }</span><br />
	path : <span id="span_path">${path }</span><br>
	
	<%-- span_list_size : <span id="span_list_size" style="display: ;"></span><br>--%>
	span_answer_total : <span id="span_answer_total" style="display: ;"></span><br>
</div>

<div id="result"></div>

<script type="text/javascript" src="${path }/questionBank/_questionBank.js"></script>