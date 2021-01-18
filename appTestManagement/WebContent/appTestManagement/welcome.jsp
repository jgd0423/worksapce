<%@page import="appTest.model.dto.AppTestDTO"%>
<%@page import="appTest.model.dao.AppTestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%

int cookNo = 0;
String cookName = null;

if (session.getAttribute("cookNo") != null) {
	cookNo = (int) session.getAttribute("cookNo");
	
	AppTestDAO sessionDAO = new AppTestDAO();
	AppTestDTO sessionDTO = sessionDAO.getSelectOne(cookNo);
	cookName = sessionDTO.getName();
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<body>

<h1>안녕하세요 <%=cookName %>님</h1>

</body>
</html>