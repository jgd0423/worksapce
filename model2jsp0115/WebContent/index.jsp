<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

String path = request.getContextPath();
// out.println(path); // /model2jsp0115

%>


<script>
location.href='<%=path%>/memo/index.jsp';
</script>

</body>
</html>