<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String name = "aaa";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>18</title>
</head>
<body>

<table border="1" width="800" align="center">

	<tr height="100">
		<td colspan="2">
			<jsp:include page="18_top.jsp" flush="false">
				<jsp:param value="<%=name %>" name="name"/>
			</jsp:include>
		</td>
	</tr>
	<tr height="300">
		<td width="200">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr height="100">
		<td colspan="2">
			<%@ include file="18_bottom.jsp" %>
		</td>
	</tr>
</table>

</body>
</html>