<%@page import="java.sql.PreparedStatement"%>
<%@page import="db.DbImplOracle"%>
<%@page import="db.Db"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="board.model.dto.BoardDTO"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
Db db = new DbImplOracle();
conn = db.dbConn();

String boardNumber_ = request.getParameter("num");
int boardNumber;

if (boardNumber_ == null || boardNumber_.length() <= 0) {
	boardNumber = 0;
} else {
	boardNumber = Integer.parseInt(boardNumber_);
}

out.println("boardNumber : " + boardNumber + "<br>");

if (boardNumber >= 1000) {
	out.println("종료");
	return;
}

boardNumber += 1;

BoardDAO dao = new BoardDAO();
BoardDTO dto = new BoardDTO();

String writer = Integer.toString(boardNumber);
String subject = Integer.toString(boardNumber);
String content = Integer.toString(boardNumber);
String email = Integer.toString(boardNumber);
String passwd = Integer.toString(boardNumber);

int num;
int ref;
int re_step;
int re_level;
int hit = 0;

ref = boardNumber;
num = boardNumber;
re_step = 1;
re_level = 1;


dto.setNum(num);
dto.setWriter(writer);
dto.setSubject(subject);
dto.setContent(content);
dto.setEmail(email);
dto.setPasswd(passwd);
dto.setRef(ref);
dto.setRe_step(re_step);
dto.setRe_level(re_level);
dto.setHit(hit);
	

	String sql = "insert into board values (seq_board.nextval, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "sysdate)";
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, dto.getNum());
	pstmt.setString(2, dto.getWriter());
	pstmt.setString(3, dto.getSubject());
	pstmt.setString(4, dto.getContent());
	pstmt.setString(5, dto.getEmail());
	pstmt.setString(6, dto.getPasswd());
	pstmt.setInt(7, dto.getRef());
	pstmt.setInt(8, dto.getRe_step());
	pstmt.setInt(9, dto.getRe_level());
	pstmt.setInt(10, dto.getHit());
	pstmt.executeUpdate();

	if (pstmt != null) { pstmt.close(); }
	if (conn != null) { conn.close(); }


%>

<script>
setTimeout(function() {
	location.href = 'autoInput.jsp?num=<%=num%>';
}, 100);

</script>