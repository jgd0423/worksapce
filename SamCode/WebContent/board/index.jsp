<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp" %> 

<!-- 
menu_gubun : ${menu_gubun}<br>
naljaMap : ${naljaMap}<br>
ip : ${ip}<br>
-->

proc : <span id="span_proc"></span><br>
tbl : <span id="span_tbl">${tbl }</span><br>
pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>
${path }


passwd : <span id="span_passwd"></span><br>


<input type="text" name="a" style="display: ;"><br><!-- ajax 테스트위한 것 -->


<div id="result" style="border: 1px solid red; position: relative;"></div>

<script type="text/javascript" src="${path }/board/_board.js"></script>
