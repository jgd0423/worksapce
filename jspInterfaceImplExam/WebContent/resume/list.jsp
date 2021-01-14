<%@page import="jspInterfaceImplExam.model.resume.ResumeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jspInterfaceImplExam.model.resume.ResumeExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String path = request.getContextPath();

ResumeExample dao = new ResumeExample();
ArrayList<ResumeDTO> resumeList = dao.getListAll();
final int ONE_LINE_SIZE = 3;

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이력서 목록</title>
</head>
<body>

<h2>이력서 목록</h2>

<table border="1">
	<% for (int i = 0; i < Math.ceil((double) resumeList.size() / ONE_LINE_SIZE); i++) { %>
		<tr>
		<% for (int j = (i * ONE_LINE_SIZE); j < (i * ONE_LINE_SIZE) + ONE_LINE_SIZE; j++) { %>
			<% if (j >= resumeList.size()) { 
				out.println("<td align=\"center\">없음</td>");
			} else { %>
			<% ResumeDTO dto = resumeList.get(j); %>
			<td>
				<table>
					<tr>
						<td>
							<a href="view.jsp?no=<%=dto.getNo()%>">
								<img src="<%=path %>/upload/img/<%=dto.getPic() %>" alt="<%=dto.getPic() %>" style="width:100px; height:100px;"/>
							</a>
						</td>
					</tr>
					<tr>
						<td><%=dto.getName() %></td>
					</tr>
					<tr>
						<td><%=dto.getPhone() %></td>
					</tr>
				</table>
			</td>
			<% } %>
		<% } %>
		</tr>
	<% } %>
</table>

</body>
</html>