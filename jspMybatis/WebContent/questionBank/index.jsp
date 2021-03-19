<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<input type="text" name="a" style="display: block;" /><br />

proc : <span id="span_proc"></span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br />
no : <span id="span_no">${no }</span><br />
path : <span id="span_path">${path }</span><br>

<div id="result"></div>

<script type="text/javascript" src="${path }/questionBank/_questionBank.js"></script>